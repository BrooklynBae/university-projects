package enums;

public enum Gender {
    MALE(""),
    FEMALE("а"),
    NEUTRAL("о"),
    ALL("и");

    private final String ending;

    Gender(String ending) {
        this.ending = ending;
    }

    public String getName() {
        return ending;
    }
}
