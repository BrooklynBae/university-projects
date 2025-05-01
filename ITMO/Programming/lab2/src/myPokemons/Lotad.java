package myPokemons;

import myMoves.Lombre.HydroPump;
import ru.ifmo.se.pokemon.Type;

public class Lotad extends Lombre {

    public Lotad(String name, int level) {
        super(name, level);

        super.setType(Type.WATER, Type.GRASS);
        super.setStats(40, 30, 30, 40, 50, 30);

        HydroPump hydroPump = new HydroPump(110, 80);

        addMove(hydroPump);
    }

}
