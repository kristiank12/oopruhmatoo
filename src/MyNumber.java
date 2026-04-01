// Interface mähisklasside MyInteger ja MyDouble jaoks
public interface MyNumber {
    MyDouble liida(MyNumber teine);
    MyDouble lahuta(MyNumber teine);
    MyDouble korruta(MyNumber teine);
    MyDouble jaga(MyNumber teine);

    double getVaartus();

}
