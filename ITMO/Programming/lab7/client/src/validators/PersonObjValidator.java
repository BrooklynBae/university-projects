package validators;

public class PersonObjValidator{

    public boolean validatePersonName(String name) {
        return !name.isEmpty() && !name.isBlank();
    }

    public boolean validateYear(int year) {
        return year >= 1900 && year <= 2024;
    }

    public boolean validateMonth(int month) {
        return month >= 1 && month <= 12;
    }

    public boolean validateDay(int day) {
        return day >= 1 && day <= 31;
    }


}
