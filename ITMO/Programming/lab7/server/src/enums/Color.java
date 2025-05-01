package enums;

public enum Color {
    RED,
    BLACK,
    BLUE,
    YELLOW,
    WHITE;

    public static String nameList() {
        StringBuilder nameList = new StringBuilder();
        for (Color color : values()) {
            nameList.append(color.name()).append(", ");
        }
        return nameList.substring(0, nameList.length() - 2);
    }

}