// Kalkulaatori GUI
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.*;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;

import java.time.LocalDateTime; // Logide jaoks
import java.time.format.DateTimeFormatter;

public class KalkGui extends Application {

    private TextField ekraan;
    private MyNumber esimeneArv = null;
    private String tehe = "";
    private boolean ootabUutArvu = true;

    // Tehted ja Ajalugu
    Tehted tehted = new Tehted();
    Ajalugu ajalugu = new Ajalugu();

    DateTimeFormatter formaat = DateTimeFormatter.ofPattern("dd.MM.yyyy HH:mm:ss"); // Puhtam aja formaat

    @Override
    public void start(Stage peaLava) {
        naitaInfot(); // Kalkulaatori juhend

        VBox juur = new VBox(15);
        juur.setPadding(new Insets(20));
        juur.setAlignment(Pos.CENTER);
        juur.setStyle("-fx-background-color: #f4f4f4;");

        ekraan = new TextField("0");
        ekraan.setFont(Font.font("Arial", FontWeight.BOLD, 32));
        ekraan.setAlignment(Pos.CENTER_RIGHT);
        ekraan.setEditable(false);
        ekraan.setStyle("-fx-background-color: #e8e8e8; -fx-text-fill: #333333; -fx-padding: 10px; -fx-background-radius: 5px; -fx-border-color: #cccccc; -fx-border-radius: 5px;");

        GridPane nupud = new GridPane();
        nupud.setHgap(8);
        nupud.setVgap(8);
        VBox.setVgrow(nupud, Priority.ALWAYS);

        // Nuppude loomine graafiliselt
        String[][] nupuTekstid = {
                {"7", "8", "9", "/"},
                {"4", "5", "6", "*"},
                {"1", "2", "3", "-"},
                {"C", "0", "=", "+"},
                {"H", "R", "CH", ""}
        };

        for (int rida = 0; rida < 5; rida++) {
            RowConstraints rc = new RowConstraints();
            rc.setPercentHeight(20);
            nupud.getRowConstraints().add(rc);

            for (int veerg = 0; veerg < 4; veerg++) {
                if (rida == 0) {
                    ColumnConstraints cc = new ColumnConstraints();
                    cc.setPercentWidth(25);
                    nupud.getColumnConstraints().add(cc);
                }

                String tekst = nupuTekstid[rida][veerg];
                if (!tekst.isEmpty()) {
                    Button nupp = new Button(tekst);
                    nupp.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
                    nupp.setFont(Font.font("Arial", FontWeight.BOLD, 18));

                    if (tekst.matches("[/*\\-+=]")) {
                        nupp.setStyle("-fx-base: #ff9800; -fx-text-fill: white; -fx-background-radius: 5px;");
                    } else if (tekst.equals("C") || tekst.equals("H") || tekst.equals("R") || tekst.equals("CH")) {
                        nupp.setStyle("-fx-base: #607d8b; -fx-text-fill: white; -fx-background-radius: 5px;");
                    } else {
                        nupp.setStyle("-fx-base: #ffffff; -fx-text-fill: black; -fx-background-radius: 5px;");
                    }

                    nupp.setOnAction(e -> kasitleSisendit(tekst));
                    nupud.add(nupp, veerg, rida);
                }
            }
        }

        juur.getChildren().addAll(ekraan, nupud);
        Scene stseen = new Scene(juur, 320, 480);

        // Klaviatuuri vajutuste kontrollimine
        stseen.setOnKeyPressed(e -> {
            KeyCode kood = e.getCode();
            if (kood.isDigitKey()) {
                kasitleSisendit(e.getText());
            } else if (kood == KeyCode.ENTER || kood == KeyCode.EQUALS) {
                kasitleSisendit("=");
            } else if (kood == KeyCode.BACK_SPACE || kood == KeyCode.ESCAPE) {
                kasitleSisendit("C");
            } else if (kood == KeyCode.PLUS || kood == KeyCode.ADD) {
                kasitleSisendit("+");
            } else if (kood == KeyCode.MINUS || kood == KeyCode.SUBTRACT) {
                kasitleSisendit("-");
            } else if (kood == KeyCode.MULTIPLY) {
                kasitleSisendit("*");
            } else if (kood == KeyCode.DIVIDE) {
                kasitleSisendit("/");
            }
        });

        peaLava.setTitle("JavaFX Kalkulaator");
        peaLava.setScene(stseen);
        peaLava.setMinWidth(280);
        peaLava.setMinHeight(400);
        peaLava.show();
    }

    private void kasitleSisendit(String sisend) {
        if (sisend.matches("[0-9]")) {
            if (ootabUutArvu) {
                ekraan.setText(sisend);
                ootabUutArvu = false;
            } else {
                ekraan.setText(ekraan.getText() + sisend);
            }
        } else if (sisend.equals("C")) {

            ekraan.setText("0");
            esimeneArv = null;
            tehe = "";
            ootabUutArvu = true;

        } else if (sisend.equals("H")) {
            // Ajaloo lugemine failist ning kontrollimine, et ajalugu on olemas ja lugemine õnnestus
            String ajaluguTekst = ajalugu.kuvaAjalugu();
            if (ajaluguTekst.isEmpty()) naitaInfot("Ajalugu on tühi!");
            else if (ajaluguTekst.equals("viga")) naitaViga("Failist lugemine ebaõnnestus");
            else {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Tehete ajalugu");
                alert.setHeaderText("Salvestatud tehed:");

                TextArea texala = new TextArea(ajaluguTekst);
                texala.setEditable(false);
                alert.getDialogPane().setContent(texala);
                alert.showAndWait();
            }
        } else if (sisend.equals("R")) {
            int suvaline = tehted.suvaline();
            ekraan.setText(String.valueOf(suvaline));
            ootabUutArvu = true;
        } else if (sisend.equals("CH")) {
            if (!(ajalugu.puhasta())) naitaViga("Ajaloo puhastamine ebaõnnestus!");
            else naitaInfot("Ajalugu on puhastatud");
        } else if (sisend.equals("=")) {
            arvutaTulemus();
        } else {
            try {
                esimeneArv = new MyDouble(Double.parseDouble(ekraan.getText()));
                tehe = sisend;
                ootabUutArvu = true;
            } catch (NumberFormatException ex) {
                naitaViga("Vigane arv ekraanil!");
            }
        }
    }

    private void arvutaTulemus() {
        if (tehe.isEmpty()) return;

        try {
            MyNumber teineArv = new MyDouble(Double.parseDouble(ekraan.getText()));
            MyNumber tulemus = tehted.arvuta(tehe, esimeneArv, teineArv);

            double tulemuseVaartus = tulemus.getVaartus();

            ekraan.setText(String.valueOf(tulemuseVaartus));

            // Salvesta tehe ajalukku, kui salvestamine ebaõnnestub (tagastatakse false), siis näidatakse viga
            if (!(ajalugu.salvesta(LocalDateTime.now().format(formaat) + " - " + esimeneArv.getVaartus() + " " + tehe + " " + teineArv.getVaartus() + " = " + tulemuseVaartus))) {
                naitaViga("Ajaloo salvestamine ebaõnnestus.");
            }

            tehe = "";
            ootabUutArvu = true;

        } catch (NumberFormatException e) {
            naitaViga("Viga sisendis! Palun sisesta numbreid.");
        } catch (ArithmeticException e) {
            naitaViga("Nulliga ei saa jagada!");
            ekraan.setText("0");
            ootabUutArvu = true;
        }
    }

    private void naitaInfot() {
        Alert info = new Alert(Alert.AlertType.INFORMATION);
        info.setTitle("Kalkulaatori juhend");
        info.setHeaderText("Tere tulemast!");
        info.setContentText("Kalkulaatorit saab kasutada nii hiire kui klaviatuuriga.\nNupp C puhastab ekraani klaviatuuril Backspace.\nNupp H avab tehete ajaloo.\nNupp R genereerib suvalise arvu.\nNupp CH puhastab ajaloo.");
        info.showAndWait();
    }

    private void naitaInfot(String tekst) {
        Alert info = new Alert(Alert.AlertType.INFORMATION);
        info.setTitle("Info");
        info.setHeaderText(null);
        info.setContentText(tekst);
        info.showAndWait();
    }

    private void naitaViga(String veateade) {
        Alert viga = new Alert(Alert.AlertType.ERROR);
        viga.setTitle("Viga");
        viga.setHeaderText(null);
        viga.setContentText(veateade);
        viga.showAndWait();
    }

    public static void main(String[] args) {
        launch(args);
    }
}