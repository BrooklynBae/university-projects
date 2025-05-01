package enums;

public enum Time {

    STILL ("ещё"),
    SOON ("Скоро"),
    FREQUENTLY ("часто"),
    NOW ("теперь"),
    IN_THE_EVENING ("Вечером"),

    DEFINETELY_NOT_TIME ("Исключение!");

    public final String definition;
    Time(String description) {
        this.definition =description;
    }

}
