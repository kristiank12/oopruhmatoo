import java.util.ArrayList;

public class Ajalugu {
    //tehted ja tulemused eraldi arraylistidena
    private ArrayList<String> tehted;
    private ArrayList<Double> tulemused;

    public Ajalugu() {
        this.tehted = new ArrayList<>();
        this.tulemused = new ArrayList<>();
    }

    // tehete salvestamine
    public void salvesta(String tehe, double tulemus) {
        this.tehted.add(tehe);
        this.tulemused.add(tulemus);
    }

    // annab ainult viimase tehte
    public String viimaneTehe() {
        if (tehted.isEmpty()) return "tyhi";
        return tehted.get(tehted.size() - 1);
    }

    // annab ainult viimase vastuse
    public double viimaneTulemus() {
        if (tulemused.isEmpty()) return 0.0;
        return tulemused.get(tulemused.size() - 1);
    }

    //kõik tehted korraga (ehk nii tulemus kui tehe ja vastus=
    public String koik() {
        if (tehted.isEmpty()) return "Pole midagi tagastada";
        String vastus = "";
        for (int i = 0; i < tehted.size(); i++) {
            vastus += tehted.get(i) + " = " + tulemused.get(i) + "\n";
        }
        return vastus;
    }
}