package enums;

public enum Semester {
    FOURTH,
    FIFTH,
    SIXTH,
    SEVENTH,
    EIGHTH;

    public static String nameList() {
        StringBuilder nameList = new StringBuilder();
        for (Semester semester : values()) {
            nameList.append(semester.name()).append(", ");
        }
        return nameList.substring(0, nameList.length() - 3);
    }

    public static Integer getKey(Semester semester) {
        switch (semester) {
            case FOURTH -> { return 4; }
            case FIFTH -> { return 5; }
            case SIXTH -> { return 6; }
            case SEVENTH -> { return 7; }
            case EIGHTH -> { return 8; }
            default -> { return 10; }
        }
    }
}