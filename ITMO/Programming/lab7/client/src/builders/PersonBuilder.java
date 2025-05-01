package builders;

import enums.Color;
import enums.Country;
import exceptions.BuildObjectException;
import model.Person;
import validators.InputValidator;
import validators.PersonObjValidator;

import java.util.NoSuchElementException;
import java.util.Scanner;

public class PersonBuilder implements BuildManager<Person> {

    @Override
    public Person buildObject() throws BuildObjectException {

        try {
            System.out.println("Создание объекта Person...");
            Scanner scanner = new Scanner(System.in);
            String nextLine;
            System.out.println();
            Person person = new Person();
            InputValidator inputValidator = new InputValidator();
            PersonObjValidator personObjValidator = new PersonObjValidator();

            // name
            String name;
            while (true) {
                System.out.println("Введите имя админа. Тип: String. Имя не может быть null");
                nextLine = scanner.nextLine();
                inputValidator.canBeNull(false);

                if (inputValidator.validate(nextLine)) {
                    name = nextLine;
                    if (personObjValidator.validatePersonName(name)) {
                        person.setName(name);
                        break;
                    } else {
                        System.out.println("Некорректный ввод. Попробуйте снова!");
                    }
                } else {
                    System.out.println("Некорректный ввод, поле не может быть null. Попробуйте снова!");
                }
            }

            // birthday
//            BirthdayBuilder birthdayBuilder = new BirthdayBuilder();
            java.sql.Date birthday = new java.sql.Date(System.currentTimeMillis());
            person.setBirthday(birthday);

            //eye color
            Color eyeColor;
            while (true) {
                try {
                    System.out.println("Введите цвет глаз. Это поле может быть null. Выберите одно из следующих: ");
                    System.out.println(Color.nameList());
                    nextLine = scanner.nextLine();
                    inputValidator.canBeNull(true);

                    if (inputValidator.validate(nextLine)) {
                        switch (nextLine) {
                            case "RED" -> eyeColor = Color.RED;
                            case "BLACK" -> eyeColor = Color.BLACK;
                            case "BLUE" -> eyeColor = Color.BLUE;
                            case "YELLOW" -> eyeColor = Color.YELLOW;
                            case "WHITE" -> eyeColor = Color.WHITE;
                            case "" -> eyeColor = null;
                            default -> throw new IllegalStateException("Unexpected value: " + nextLine);
                        }
                        person.setEyeColor(eyeColor);
                        break;
                    }
                } catch (IllegalStateException e) {
                    System.out.println("Некорректный ввод. Попробуйте снова!");
                }
            }

            // hair color
            Color hairColor;
            while (true) {
                try {
                    System.out.println("Введите цвет волос. Это поле не может быть null. Выберите одно из следующих: ");
                    System.out.println(Color.nameList());
                    nextLine = scanner.nextLine();
                    inputValidator.canBeNull(false);

                    if (inputValidator.validate(nextLine)) {

                        switch (nextLine) {
                            case "RED" -> hairColor = Color.RED;
                            case "BLACK" -> hairColor = Color.BLACK;
                            case "BLUE" -> hairColor = Color.BLUE;
                            case "YELLOW" -> hairColor = Color.YELLOW;
                            case "WHITE" -> hairColor = Color.WHITE;
                            default -> throw new IllegalStateException("Unexpected value: " + nextLine);
                        }
                        person.setHairColor(hairColor);
                        break;
                    } else {
                        System.out.println("Поле не может быть null. Попробуйте снова!");
                    }
                } catch (IllegalStateException e) {
                    System.out.println("Некорректный ввод. Попробуйте снова!");
                }
            }

            // nationality
            Country nationality;
            while (true) {
                try {
                    System.out.println("Введите национальность. Поле может быть null. Выберите одно из следующих: ");
                    System.out.println(Country.nameList());
                    nextLine = scanner.nextLine();
                    inputValidator.canBeNull(true);

                    if (inputValidator.validate(nextLine)) {
                        switch (nextLine) {
                            case "UNITED_KINGDOM" -> nationality = Country.UNITED_KINGDOM;
                            case "GERMANY" -> nationality = Country.GERMANY;
                            case "VATICAN" -> nationality = Country.VATICAN;
                            case "NORTH_KOREA" -> nationality = Country.NORTH_KOREA;
                            case "JAPAN" -> nationality = Country.JAPAN;
                            case "" -> nationality = null;
                            default -> throw new IllegalStateException("Unexpected value: " + nextLine);
                        }
                        person.setNationality(nationality);
                        break;
                    }
                } catch (IllegalStateException e) {
                    System.out.println("Некорректный ввод. Попробуйте снова!");
                }
            }
            return person;

        } catch (NoSuchElementException e) {
            throw new BuildObjectException("При создании объекта возникла ошибка! " + e.getLocalizedMessage());
        }
    }
}
