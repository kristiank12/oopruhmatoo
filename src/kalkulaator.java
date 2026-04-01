import java.util.InputMismatchException;
import java.util.Locale;
import java.util.Scanner;

public class kalkulaator {
    public static void main(String[] args) {
        System.out.println("Kalkulaatori tegevused: lahutamine (-), liitmine (+), korrutamine (*), jagamine (*), suvalise arvu tagastamine lõigust (r), sulgemine (q)");
        Scanner kasutajaSisend = new Scanner(System.in);
        kasutajaSisend.useLocale(Locale.ENGLISH); // Loeb murdarve punktiga, mitte komaga
        sisendiHaldur sisendiHaldur = new sisendiHaldur(kasutajaSisend);

        // Kasutaja kalkulaatori loop
        do {
                System.out.println("Sisesta arv või tehte märk: ");
                String tüüp = sisendiHaldur.määraTüüp();
                String sisend = kasutajaSisend.nextLine();

                if (tüüp.equals("String")) {
                    if (sisend.equals("q")) {
                        kasutajaSisend.close();
                        break;
                    // Märgi kontrollimine
                    } else if (sisend.equals("+")) {
                        if (sisendiHaldur.arvePraegu() > 1) {
                            System.out.println("Tehte vastus on " + sisendiHaldur.arvuta("+").getVaartus());
                            sisendiHaldur.eemaldaArvud();
                        } else {
                            System.out.println("Pole antud piisavalt arve.");
                        }
                    } else if (sisend.equals("-")) {
                        if (sisendiHaldur.arvePraegu() > 1) {
                            System.out.println("Tehte vastus on " + sisendiHaldur.arvuta("-").getVaartus());
                            sisendiHaldur.eemaldaArvud();
                        } else {
                            System.out.println("Pole antud piisavalt arve.");
                        }
                    } else if (sisend.equals("*")) {
                        if (sisendiHaldur.arvePraegu() > 1) {
                            System.out.println("Tehte vastus on " + sisendiHaldur.arvuta("*").getVaartus());
                            sisendiHaldur.eemaldaArvud();
                        } else {
                            System.out.println("Pole antud piisavalt arve.");
                        }
                    } else if (sisend.equals("/")) {
                        if (sisendiHaldur.arvePraegu() > 1) {
                                System.out.println("Tehte vastus on " + sisendiHaldur.arvuta("/").getVaartus());
                                sisendiHaldur.eemaldaArvud();
                        } else {
                            System.out.println("Pole antud piisavalt arve.");
                        }
                    } else if (sisend.equals("r")) {
                        if (sisendiHaldur.arvePraegu() > 1) {
                            System.out.println("Suvaline arv on " + sisendiHaldur.arvuta("r").getVaartus());
                            sisendiHaldur.eemaldaArvud();
                        } else {
                            System.out.println("Pole antud piisavalt arve.");
                        }
                    } else {
                        System.out.println("Pole õige sisestus.");
                    }
                } else if (tüüp.equals("Int")) {
                    sisendiHaldur.lisaArv(new MyInteger(Integer.parseInt(sisend)));
                } else if (tüüp.equals("Double")) {
                    sisendiHaldur.lisaArv(new MyDouble(Double.parseDouble(sisend)));
                }

        } while (true);
    }

}
