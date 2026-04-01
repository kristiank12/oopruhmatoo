import java.util.Random;

public class MyDouble implements MyNumber{

    private double vaartus;

    public MyDouble(double vaartus) {
        this.vaartus = vaartus;
    }

    // on vaja double mitte int
    public double getVaartus() {
        return this.vaartus;
    }

    // nõutakse eraldi meetodeid, sp pole try-catch

    public MyDouble liida(MyNumber teine) {
        return new MyDouble(this.vaartus + teine.getVaartus());
    }

    public MyDouble lahuta(MyNumber teine) {
        return new MyDouble(this.vaartus - teine.getVaartus());
    }

    public MyDouble korruta(MyNumber teine) {
        return new MyDouble(this.vaartus * teine.getVaartus());
    }

    // nulliga jagamise kontroll (doubles ei saa nkn jääki tekkida)
    public MyDouble jaga(MyNumber teine) {
        if (teine.getVaartus() == 0.0) {
            throw new IllegalArgumentException("ERROR: nulliga jagamine");
        }
        return new MyDouble(this.vaartus / teine.getVaartus());
    }

    // vordlused

    public boolean onSuurem(MyDouble teine) {
        return this.vaartus > teine.getVaartus();
    }

    public boolean onVaiksem(MyDouble teine) {
        return this.vaartus < teine.getVaartus();
    }

    public boolean onVordne(MyDouble teine) {
        return this.vaartus == teine.getVaartus();
    }
    // juhendis noutud juhusliku suuruse kasutamine
    public static MyDouble suvaline(double min, double max) {
        Random r = new Random();
        double suva = min + (max - min) * r.nextDouble();
        return new MyDouble(suva);
    }
}