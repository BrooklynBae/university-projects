package enums;

public enum Location {

    ON_SEA_SHORE ("на речном берегу"),
    HERE_UNDER_WINDOW ("Тут под окошком"),
    TO_WINDOW ("к окну"),
    OUTSIDE ("наружу"),
    AT_HOME ("дома"),
    IN_TRAVEL ("в путешествие"),
    DOWN ("Внизу"),
    NEXT_LADDER ("у лестницы"),
    DOWN_TO_FLOW ("вниз по течению"),
    UNDER_FEET ("везде под ногами"),
    IN_THE_HOUSE ("в доме"),
    NOT_A_LOCATION("Исключение");

    public final String definition;
    Location(String description) {
        this.definition = description;
    }

}
