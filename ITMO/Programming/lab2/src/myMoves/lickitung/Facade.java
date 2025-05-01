package myMoves.lickitung;

import ru.ifmo.se.pokemon.*;

public class Facade extends PhysicalMove {

    public Facade(double pow, double acc) {
        super(Type.NORMAL, pow, acc);
    }


    @Override
    protected void applySelfEffects(Pokemon p) {
        super.applySelfEffects(p);

        if (p.getCondition() == Status.BURN || p.getCondition() == Status.POISON || p.getCondition() == Status.PARALYZE) {
            this.power = this.power*2;
        }


    }

    @Override
    protected String describe() {
        String[] pieces = this.getClass().toString().split("\\.");
        return "does " + pieces[pieces.length-1];
    }
}
