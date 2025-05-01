package builders;

import validators.InputValidator;
import validators.PersonObjValidator;

import java.time.format.DateTimeParseException;
import java.util.Scanner;

public class BirthdayBuilder implements BuildManager<java.sql.Date> {
    @Override
    public java.sql.Date buildObject() {

        System.out.println("Генерируем день рождения админа... ");
        Scanner scanner = new Scanner(System.in);
        InputValidator inputValidator = new InputValidator();
        PersonObjValidator personObjValidator = new PersonObjValidator();
        String nextLine;
        System.out.println();
        java.sql.Date birthday = new java.sql.Date(System.currentTimeMillis());

        try {
            //year
            while (true) {
                try {
                    System.out.println("Введите год. Тип: int");
                    nextLine = scanner.nextLine();
                    inputValidator.canBeNull(false);

                    if (inputValidator.validate(nextLine) && personObjValidator.validateYear(Integer.parseInt(nextLine))) {
                        birthday.setYear(Integer.parseInt(nextLine) - 1900);
                        break;
                    } else {
                        System.out.println("Некорректный ввод. Попробуйте снова!");
                    }
                } catch (NumberFormatException e) {
                    System.out.println("НЕКОРРЕКТНЫЙ!");
                }
            }

            //month
            while (true) {
                try {
                    System.out.println("Введите месяц. Тип: int");
                    nextLine = scanner.nextLine();
                    inputValidator.canBeNull(false);

                    if (inputValidator.validate(nextLine) && personObjValidator.validateMonth(Integer.parseInt(nextLine)) && (Integer.parseInt(nextLine) <= 12)) {
                        birthday.setMonth(Integer.parseInt(nextLine));
                        break;
                    } else {
                        System.out.println("Некорректный ввод. Попробуйте снова!");
                    }
                } catch (NumberFormatException e) {
                    System.out.println("НЕКОРРЕКТНЫЙ!");
                }
            }

            //day
            while (true) {
                try {
                    System.out.println("Введите день. Тип: int");
                    nextLine = scanner.nextLine();
                    inputValidator.canBeNull(false);

                    if (inputValidator.validate(nextLine) && personObjValidator.validateDay(Integer.parseInt(nextLine)) && (Integer.parseInt(nextLine) <= 30) ) {
                        birthday.setDate(Integer.parseInt(nextLine));
                        break;
                    } else {
                        System.out.println("Некорректный ввод. Попробуйте снова!");
                    }
                } catch (NumberFormatException e) {
                    System.out.println("НЕКОРРЕКТНЫЙ!");
                }
            }
            birthday.setHours(0);
            birthday.setMinutes(0);
            birthday.setSeconds(0);

        } catch (DateTimeParseException e) {
            System.out.println("Во время создания объекта Birthday возникла ошибка!");
        }
        return birthday;
    }
}
