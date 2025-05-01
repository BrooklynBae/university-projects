package myPokemons;

import myMoves.castform.*;
import ru.ifmo.se.pokemon.Pokemon;
import ru.ifmo.se.pokemon.Type;

public class Castform extends Pokemon {

    public Castform(String name, int level) {
        super(name, level);

        super.setType(Type.NORMAL);
        super.setStats(70, 70, 70, 70, 70, 70);

        Headbutt headbutt = new Headbutt(70, 100);
        ThunderBolt thunderBolt = new ThunderBolt(90, 100);
        Blizzard blizzard = new Blizzard(110, 70);
        ShadowBall shadowBall = new ShadowBall(80, 100);

        super.setMove(headbutt, thunderBolt, blizzard, shadowBall);
    }
}
// таблица виртуальных методов