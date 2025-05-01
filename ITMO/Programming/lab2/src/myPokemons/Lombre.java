package myPokemons;

import myMoves.lotad.Rest;
import myMoves.lotad.Scald;
import ru.ifmo.se.pokemon.Pokemon;
import ru.ifmo.se.pokemon.Type;

public class Lombre extends Pokemon {

    public Lombre(String name, int level) {
        super(name, level);

        super.setType(Type.WATER, Type.GRASS);
        super.setStats(60, 50, 50, 60, 70, 50);

        Rest rest = new Rest(0, 0);
        Scald scald = new Scald(80, 100);

        super.setMove(rest, scald);
    }


}
