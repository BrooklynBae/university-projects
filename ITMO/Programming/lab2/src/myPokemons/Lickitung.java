package myPokemons;

import myMoves.castform.Blizzard;
import myMoves.lickitung.Facade;
import myMoves.lickitung.Swagger;
import ru.ifmo.se.pokemon.Pokemon;
import ru.ifmo.se.pokemon.Type;

public class Lickitung extends Pokemon {

    public Lickitung(String name, int level) {
        super(name, level);

        super.setType(Type.NORMAL);
        super.setStats(90, 55, 75, 60, 75, 30);

        Swagger swagger = new Swagger(0, 85);
        Facade facade = new Facade(70, 100);
        Blizzard blizzard = new Blizzard(110, 70);

        super.setMove(swagger, facade, blizzard);
    }
}
