import java.util.Locale;
import java.util.Scanner;

public class kalkulaator {
    public static void main(String[] args) {
        System.out.println("Kalkulaator - lahutamine (-), liitmine (+), korrutamine (*), jagamine (*), sulgemine (q)");
        Scanner kasutajaSisend = new Scanner(System.in);
        kasutajaSisend.useLocale(Locale.ENGLISH); // Loeb murdarve punktiga, mitte komaga
        sisendiHaldur sisendiHaldur = new sisendiHaldur(kasutajaSisend, "Puudub");

        int arvePraegu = 0;
        // Kasutaja kalkulaatori loop
        do {
            if (sisendiHaldur.getSisendiTehe().equals("Puudub")) {
                System.out.println("Sisesta tehe: ");
                sisendiHaldur.setSisendiTehe("Arv");
            } else {
                System.out.println(sisendiHaldur.määraTüüp());
                String tüüp = sisendiHaldur.määraTüüp();
                String sisend = kasutajaSisend.nextLine();
                double arv = 0;
                if (tüüp.equals("String")) {
                    if (sisend.equals("q")) {
                        kasutajaSisend.close();
                        break;
                    }
                } else if (tüüp.equals("Int")) {
                    arv = (double) Integer.parseInt(sisend);
                } else if (tüüp.equals("Double")) {
                    arv = Double.parseDouble(sisend);
                }
                System.out.println(arv);
                if (arvePraegu == 0) {
                    sisendiHaldur.setEsimeneArv(arv);
                } else if (arvePraegu == 1) {
                    sisendiHaldur.setTeineArv(arv);
                } else {
                    // Kutsu tehte tegemine ja vastus
                    arvePraegu = 0;
                }
            }
        } while (kasutajaSisend.hasNext());
    }

}
