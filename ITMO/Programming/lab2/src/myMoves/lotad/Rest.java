package myMoves.lotad;

import ru.ifmo.se.pokemon.*;

public class Rest extends StatusMove {

    public Rest(double pow, double acc) {
        super(Type.PSYCHIC, pow, acc);
    }


    @Override
    protected void applySelfEffects(Pokemon p) {

        super.applySelfEffects(p);

        Effect e = new Effect().condition(Status.SLEEP).turns(2).stat(Stat.HP, (int)p.getStat(Stat.HP));
        p.addEffect(e);


    }

    @Override
    protected String describe() {
        String[] pieces = this.getClass().toString().split("\\.");
        return "does " + pieces[pieces.length-1];
    }

}
//.stat(Stat.HP, (int)p.getStat(Stat.HP));