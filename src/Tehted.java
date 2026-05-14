// Klass tehete teostamise jaoks
public class Tehted {

    public MyNumber arvuta(String märk, MyNumber esimeneArv, MyNumber teineArv) {
        if (märk.equals("+")) {
            return esimeneArv.liida(teineArv);
        } else if (märk.equals("-")) {
            return esimeneArv.lahuta(teineArv);
        } else if (märk.equals("*")) {
            return esimeneArv.korruta(teineArv);
        } else if (märk.equals("/")) {
            return esimeneArv.jaga(teineArv);
        }
        return null;
    }

    public int suvaline() {
        return (int) (Math.random() * 100) + 1;
    }


}