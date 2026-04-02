import java.util.Scanner;
import java.util.ArrayList;

public class sisendiHaldur {
    private Scanner sisend;
    private ArrayList<MyNumber> arvud;

    public String määraTüüp() {
        if (sisend.hasNextInt()) {
            return "Int";
        } else if (sisend.hasNextDouble()) {
            return "Double";
        } else if (sisend.hasNextLine()) {
            return "String";
        } else {
            return "Vale";
        }
    }

    public MyNumber arvuta(String märk) {
        if (märk.equals("+")) {
            MyNumber puhver = new MyDouble(0);
            for (int i = 1; i < arvud.size(); i++) {
                puhver = arvud.get(i-1).liida(arvud.get(i));
                arvud.set(i, puhver);
            }
            return arvud.get(arvud.size() - 1);
        } else if (märk.equals("-")) {
            MyNumber puhver = new MyDouble(0);
            for (int i = 1; i < arvud.size(); i++) {
                puhver = arvud.get(i-1).lahuta(arvud.get(i));
                arvud.set(i, puhver);
            }
            return arvud.get(arvud.size() - 1);
        } else if (märk.equals("*")) {
            MyNumber puhver = new MyDouble(0);
            for (int i = 1; i < arvud.size(); i++) {
                puhver = arvud.get(i-1).korruta(arvud.get(i));
                arvud.set(i, puhver);
            }
            return arvud.get(arvud.size() - 1);
        } else if (märk.equals("/")) {
            MyNumber puhver = new MyDouble(0);
            for (int i = 1; i < arvud.size(); i++) {
                puhver = arvud.get(i-1).jaga(arvud.get(i));
                arvud.set(i, puhver);
            }
            return arvud.get(arvud.size() - 1);
        } else if (märk.equals("r")) {
            // Kontrollib kas üks otsadest on double, kui on siis kasutada mydouble klassi, vastasel juhul saab kasutada myintegeri
            if (arvud.getFirst().getClass().getName().equals("MyDouble")) {
                return MyDouble.suvaline(arvud.getFirst().getVaartus(), arvud.getLast().getVaartus());
            } else if (arvud.getLast().getClass().getName().equals("MyDouble")) {
                return MyDouble.suvaline(arvud.getFirst().getVaartus(), arvud.getLast().getVaartus());
            } else {
                return MyInteger.suvaline((int) arvud.getFirst().getVaartus(), (int) arvud.getLast().getVaartus());
            }
        }
        return null;
    }

    public sisendiHaldur(Scanner sisend) {
        this.sisend = sisend;
        this.arvud = new ArrayList<>();
    }

    public void lisaArv(MyNumber arv) {
        arvud.add(arv);
    }

    public void eemaldaArvud() {
        arvud.clear();
    }

    public int arvePraegu() {
        return arvud.size();
    }

}