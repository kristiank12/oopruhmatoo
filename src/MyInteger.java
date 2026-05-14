// MyInteger mähisklass int tüüpi arvude jaoks
import java.util.Random;

public class MyInteger implements MyNumber {

    private int vaartus;

    public MyInteger(int vaartus) {
        this.vaartus = vaartus;
    }

    public double getVaartus() {
        return this.vaartus;
    }

    public void setVaartus(int uusVaartus) {
        this.vaartus = uusVaartus;
    }

    public MyDouble liida(MyNumber teine) {
        return new MyDouble(this.vaartus + teine.getVaartus());
    }

    public MyDouble lahuta(MyNumber teine) {
        return new MyDouble(this.vaartus - teine.getVaartus());
    }

    public MyDouble korruta(MyNumber teine) {
        return new MyDouble(this.vaartus * teine.getVaartus());
    }

    public MyDouble jaga(MyNumber teine) {
        if (teine.getVaartus() == 0) {
            throw new IllegalArgumentException("ERROR: nulliga jagamine");
        }
        return new MyDouble(this.vaartus / teine.getVaartus());
    }


    // Vordlemise meetodid

    public boolean onSuurem(MyInteger teine) {
        return this.vaartus > teine.getVaartus();
    }

    public boolean onVaiksem(MyInteger teine) {
        return this.vaartus < teine.getVaartus();
    }

    public boolean onVordne(MyInteger teine) {
        return this.vaartus == teine.getVaartus();
    }

    public boolean onSuuremVoiVordne(MyInteger teine) {
        return this.vaartus >= teine.getVaartus();
    }

    public boolean onVaiksemVoiVordne(MyInteger teine) {
        return this.vaartus <= teine.getVaartus();
    }

    // juhusliku suuruse meetod
    public static MyInteger suvaline(int min, int max) {
        Random r = new Random();
        int juhuslik = r.nextInt((max - min) + 1) + min;
        return new MyInteger(juhuslik);

    }
}