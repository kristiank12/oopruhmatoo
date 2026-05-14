// Ajalugu klass tehete kirjutamiseks ja lugemiseks failist
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class Ajalugu {
    private String failiNimi = "ajalugu.txt";

    // Tehte salvestamine peale tehte tegemist
    public boolean salvesta(String tehe) {
        try (FileWriter kirjutaja = new FileWriter(failiNimi, true)) {
            kirjutaja.write(tehe + "\n");
            return true;
        } catch (IOException e) {
            return false;
        }
    }

    // Kõikide tehete info
    public String kuvaAjalugu() {

        File fail = new File(failiNimi);

        if (!fail.exists()) {
            return "";
        }

        StringBuilder ajaluguTekst = new StringBuilder();

        try (Scanner lugeja = new Scanner(fail)) {
            while (lugeja.hasNextLine()) {
                ajaluguTekst.append(lugeja.nextLine()).append("\n");
            }

            return ajaluguTekst.toString();

        } catch (IOException e) {
            return "viga";
        }
    }

    // Ajaloo puhastamine
    public boolean puhasta() {
        try (FileWriter kirjutaja = new FileWriter(failiNimi, false)) {
            kirjutaja.write("");
            return true;
        } catch (IOException e) {
            return false;
        }
    }
}