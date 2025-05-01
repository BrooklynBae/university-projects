package myMoves.castform;

import lab2.Program;
import ru.ifmo.se.pokemon.*;

public class Blizzard extends SpecialMove {

    public Blizzard(double pow, double acc) {
        super(Type.ICE, pow, acc);
    }

    @Override
    protected void applyOppEffects(Pokemon p) {
        super.applyOppEffects(p);

        if (Program.chance(0.1)) {
            Effect.freeze(p);
        }

    }

    @Override
    protected String describe() {
        String[] pieces = this.getClass().toString().split("\\.");
        return "does " + pieces[pieces.length-1];
    }

}
