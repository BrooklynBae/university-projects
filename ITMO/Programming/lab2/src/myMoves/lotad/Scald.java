package myMoves.lotad;

import lab2.Program;
import ru.ifmo.se.pokemon.Effect;
import ru.ifmo.se.pokemon.Pokemon;
import ru.ifmo.se.pokemon.SpecialMove;
import ru.ifmo.se.pokemon.Type;

public class Scald extends SpecialMove {

    public Scald(double pow, double acc) {
        super(Type.WATER, pow, acc);
    }

    @Override
    protected void applyOppEffects(Pokemon p) {

        super.applyOppEffects(p);

        if (Program.chance(0.3)) {
            Effect.burn(p);
        }
    }


    @Override
    protected String describe() {
        String[] pieces = this.getClass().toString().split("\\.");
        return "does " + pieces[pieces.length-1];
    }

}
