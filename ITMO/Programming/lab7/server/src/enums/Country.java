package enums;

public enum Country {
    UNITED_KINGDOM,
    GERMANY,
    VATICAN,
    NORTH_KOREA,
    JAPAN;

    public static String nameList() {
        StringBuilder nameList = new StringBuilder();
        for (Country country : values()) {
            nameList.append(country.name()).append(", ");
        }
        return nameList.substring(0, nameList.length() - 2);
    }

}