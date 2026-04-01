import java.util.Random;

public class MyInteger {


    private int vaartus;

    public MyInteger(int vaartus) {
        this.vaartus = vaartus;
    }

    public int getVaartus() {
        return this.vaartus;
    }

    public void setVaartus(int uusVaartus) {
        this.vaartus = uusVaartus;
    }



    public MyInteger liida(MyInteger teine) {
        return new MyInteger(this.vaartus + teine.getVaartus());
    }

    public MyInteger lahuta(MyInteger teine) {
        return new MyInteger(this.vaartus - teine.getVaartus());
    }

    public MyInteger korruta(MyInteger teine) {
        return new MyInteger(this.vaartus * teine.getVaartus());
    }

    public MyInteger jaga(MyInteger teine) {
        if (teine.getVaartus() == 0) {
            throw new IllegalArgumentException("ERROR: nulliga jagamine");
        }
        return new MyInteger(this.vaartus / teine.getVaartus());
    }

    // 0-ga jagamine / jäägi leidmine
    public MyInteger jaak(MyInteger teine) {
        if (teine.getVaartus() == 0) {
            throw new IllegalArgumentException("ERROR: nulliga jagamine");
        }
        return new MyInteger(this.vaartus % teine.getVaartus());
    }

    // vordlemise meetodid

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

    // juhusliku suuruse meetod (juhendis oli kirjas)
    public static MyInteger suvaline(int min, int max) {
        Random r = new Random();
        int juhuslik = r.nextInt((max - min) + 1) + min;
        return new MyInteger(juhuslik);
    }
}