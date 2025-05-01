package lab2;

import myPokemons.*;
import ru.ifmo.se.pokemon.Battle;
import ru.ifmo.se.pokemon.Pokemon;

public class Program {



    public static void main(String[] args) {

        Battle b = new Battle();

        Pokemon lickitung = new Lickitung("Лизун", 1);
        Pokemon castform = new Castform("Голова", 1);
        Pokemon lickilicky = new Lickilicky("Начёс", 1);
        Pokemon lombre = new Lombre("Мучачо", 1);
        Pokemon lotad = new Lotad("Водоросля", 1);
        Pokemon ludicolo = new Ludicolo("Амиго", 1);

        b.addAlly(lickitung);
        b.addAlly(lickilicky);
        b.addAlly(castform);

        b.addFoe(lotad);
        b.addFoe(lombre);
        b.addFoe(ludicolo);

        b.go();

    }




    public static boolean chance(double d) {
        return d > Math.random();
    }
}
