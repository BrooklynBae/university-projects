package myMoves.castform;

import lab2.Program;
import ru.ifmo.se.pokemon.Effect;
import ru.ifmo.se.pokemon.Pokemon;
import ru.ifmo.se.pokemon.SpecialMove;
import ru.ifmo.se.pokemon.Type;

public class ThunderBolt extends SpecialMove {

    public ThunderBolt(double pow, double acc) {
        super(Type.ELECTRIC, pow, acc);
    }


    @Override
    protected void applyOppEffects(Pokemon p) {
        super.applyOppEffects(p);

        if (Program.chance(0.1)) {
            Effect.paralyze(p);
        }
    }

    @Override
    protected String describe() {
        String[] pieces = this.getClass().toString().split("\\.");
        return "does " + pieces[pieces.length-1];
    }
}
