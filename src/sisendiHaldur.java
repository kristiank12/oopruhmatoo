import java.util.Scanner;

public class sisendiHaldur {
    private Scanner sisend;
    private String sisendiTehe;
    private double esimeneArv;
    private double teineArv;

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

    public sisendiHaldur(Scanner sisend, String sisendiTehe) {
        this.sisend = sisend;
        this.sisendiTehe = sisendiTehe;
    }

    public String getSisendiTehe() {
        return sisendiTehe;
    }

    public void setSisendiTehe(String sisendiTehe) {
        this.sisendiTehe = sisendiTehe;
    }

    public double getEsimeneArv() {
        return esimeneArv;
    }

    public void setEsimeneArv(double esimeneArv) {
        this.esimeneArv = esimeneArv;
    }

    public double getTeineArv() {
        return teineArv;
    }

    public void setTeineArv(double teineArv) {
        this.teineArv = teineArv;
    }
}
