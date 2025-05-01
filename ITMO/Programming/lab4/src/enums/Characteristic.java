package enums;

public enum Characteristic {
    IN_SUCH ("В такие"),
    WITH_HIS ("со своей"),
    AT_ALL ("совсем"),
    THIS ("этой"),
    SOME ("какие-нибудь"),
    QUIET ("негромкий"),
    HAPPILY ("от радости"),
    ALONE ("один"),
    KIND ("мировые"),
    A_LITTLE_UPSET ("несколько растроенный"),
    BRIGHT ("светлую"),
    LONELY ("одиноких"),
    CRAWLING ("крадущихся"),
    JULY ("июньскую"),
    NO ("никакого"),
    GENTLE ("тихонько");


    public final String definition;
    Characteristic(String description) {
        this.definition =description;
    }

}
