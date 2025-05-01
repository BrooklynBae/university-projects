package myPokemons;

import ru.ifmo.se.pokemon.Type;

public class Ludicolo extends Lotad {

    public Ludicolo(String name, int level) {
        super(name, level);

        super.setType(Type.WATER, Type.GRASS);
        super.setStats(80, 70, 70, 90, 100, 70);


    }

}
