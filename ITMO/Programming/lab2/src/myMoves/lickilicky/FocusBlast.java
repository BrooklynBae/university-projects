package myMoves.lickilicky;

import lab2.Program;
import ru.ifmo.se.pokemon.*;

public class FocusBlast extends SpecialMove {

    public FocusBlast(double pow, double acc) {
        super(Type.FIGHTING, pow, acc);
    }

    @Override
    protected void applyOppEffects(Pokemon p) {

        super.applyOppEffects(p);

        if (Program.chance(0.2)) {
            Effect e = new Effect().stat(Stat.SPECIAL_DEFENSE, -1);
            p.addEffect(e);
        }

    }


    @Override
    protected String describe() {
        String[] pieces = this.getClass().toString().split("\\.");
        return "does " + pieces[pieces.length-1];
    }


}
