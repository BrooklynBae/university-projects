package builders;

import enums.Semester;
import exceptions.BuildObjectException;
import model.StudyGroup;
import validators.IdValidator;
import validators.InputValidator;
import validators.StudyGroupValidator;

import java.util.Date;
import java.util.NoSuchElementException;
import java.util.Objects;
import java.util.Scanner;

public class StudyGroupBuilder implements BuildManager<StudyGroup> {

    @Override
    public StudyGroup buildObject() throws BuildObjectException {

        StudyGroupValidator validator = new StudyGroupValidator();

        try {
            System.out.println("Создание нового объекта study group...");
            StudyGroup studyGroup = new StudyGroup();
            Scanner scanner = new Scanner(System.in);
            InputValidator inputValidator = new InputValidator();
            String nextLine;
            System.out.println();

            // id
            studyGroup.setId(IdValidator.generateId());

            // name
            String name;
            inputValidator.canBeNull(false);
            while (true) {
                System.out.println("Введите имя. Тип поля: String. Поле не может быть null.");
                nextLine = scanner.nextLine();

                if (inputValidator.validate(nextLine)) {
                    name = nextLine;

                    if (validator.validateName(nextLine)) {
                        studyGroup.setName(name);
                        break;
                    } else {
                        System.out.println("Поле \"имя\" было введенно некорректно, попробуйте снова.");
                    }

                } else {
                    System.out.println("Введите что-нибудь (пожалуйста), поле не может быть null");
                }
            }

            // coordinates
            CoordinateBuilder coordinateBuilder = new CoordinateBuilder();
            studyGroup.setCoordinates(coordinateBuilder.buildObject());

            // creation date
            java.sql.Date creationDate = new java.sql.Date(System.currentTimeMillis());
            studyGroup.setCreationDate(creationDate);

            // students count
            Long studentsCount;
            while (true) {
                try {
                    System.out.println("Введите количество студентов. Тип: Long. Количество должно быть положительным");
                    nextLine = scanner.nextLine();
                    inputValidator.canBeNull(true);

                    if (Objects.equals(nextLine, "")) {
                        break;
                    } else {
                        studentsCount = Long.parseLong(nextLine);

                        if (validator.validateStudentsCount(studentsCount)) {
                            studyGroup.setStudentsCount(studentsCount);
                            break;
                        } else {
                            System.out.println("Поле \"количество студентов\" было введенно некорректно, попробуйте снова.");
                        }
                    }
                } catch (NumberFormatException e) {
                    System.out.println("Некорректный ввод. Попробуйте снова!");
                }
            }

            // should be expelled
            Integer shouldBeExpelled;
            while (true) {
                try {
                    System.out.println("Введите колличество студентов, которых хотите отчислить :) Тип: Integer. Поле должно быть положительным и не равным null");
                    nextLine = scanner.nextLine();
                    shouldBeExpelled = Integer.valueOf(nextLine);

                    if(validator.validateShouldBeExpelled(shouldBeExpelled)) {
                        studyGroup.setShouldBeExpelled(shouldBeExpelled);
                        break;
                    } else {
                        System.out.println("Поле \"количество отчисленных\" было введенно некорректно, попробуйте снова.");
                    }
                } catch (NumberFormatException e) {
                    System.out.println("Некорректный ввод. Попробуйте снова!");
                }

            }

            // average mark
            float averageMark;
            while (true) {
                try {
                    System.out.println("Введите среднюю оценку. Тип: float. Поле должно быть положительным.");
                    nextLine = scanner.nextLine();
                    inputValidator.canBeNull(true);


                    averageMark = Float.parseFloat(nextLine);

                    if(validator.validateAverageMark(averageMark)) {
                        studyGroup.setAverageMark(averageMark);
                        break;
                    } else {
                        System.out.println("Поле \"средняя оценка\" было введенно некорректно, попробуйте снова.");
                    }
                } catch (NumberFormatException e) {
                    System.out.println("Некорректный ввод. Попробуйте снова!");
                }
            }

            // semester enum
            Semester semester;
            while (true) {
                try {
                    System.out.println("Введите семестр. Это поле может быть null. Выберете один из следующих: ");
                    System.out.println(Semester.nameList());
                    nextLine = scanner.nextLine();
                    inputValidator.canBeNull(true);

                    if (inputValidator.validate(nextLine)) {
                        switch (nextLine) {
                            case "FOURTH" -> semester = Semester.FOURTH;
                            case "FIFTH" -> semester = Semester.FIFTH;
                            case "SIXTH" -> semester = Semester.SIXTH;
                            case "SEVENTH" -> semester = Semester.SEVENTH;
                            case "EIGHT" -> semester = Semester.EIGHTH;
                            case "" -> semester = null;
                            default -> throw new IllegalStateException("Unexpected value: " + nextLine);
                        }
                        studyGroup.setSemesterEnum(semester);
                        break;
                    }
                } catch (IllegalStateException e) {
                    System.out.println("Некорректный ввод. Попробуйте снова!");
                }
            }

            // study group admin
            while (true) {
                System.out.println("Хотите создать админа группы? Введите \"yes\" или \"no\".");
                nextLine = scanner.nextLine();

                if (nextLine.trim().equals("no")) {
                    break;
                } else if (nextLine.trim().equals("yes")){
                    PersonBuilder personBuilder = new PersonBuilder();
                    studyGroup.setGroupAdmin(personBuilder.buildObject());
                    break;
                } else {
                    System.out.println("У вас ТОЛЬКО ДВА варианта..!");
                }
            }
            return studyGroup;
        } catch (NoSuchElementException e) {
            throw new BuildObjectException("Возникла ошибка при создании объекта: " + e.getLocalizedMessage());
        }
    }
}
