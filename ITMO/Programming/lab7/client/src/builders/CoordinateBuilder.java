package builders;

import exceptions.NullException;
import exceptions.WrongNumberException;
import model.Coordinates;
import validators.InputValidator;
import validators.StudyGroupValidator;

import java.util.Objects;
import java.util.Scanner;

public class CoordinateBuilder implements BuildManager<Coordinates> {
    @Override
    public Coordinates buildObject() {

        System.out.println("Генерируем координаты... ");
        Scanner scanner = new Scanner(System.in);
        Coordinates coordinates = new Coordinates();
        InputValidator inputValidator = new InputValidator();
        String nextLine;
        System.out.println();
        StudyGroupValidator coordinateValidator = new StudyGroupValidator();

        long x;
        while (true) {
            try {
                System.out.println("Введите координату Х. Тип: long. Максимальное значение этого поля: 987");
                nextLine = scanner.nextLine();
                inputValidator.canBeNull(true);

                if (Objects.equals(nextLine, "")) {
                    break;
                } else {
                    x = Long.parseLong(nextLine);

                    if (coordinateValidator.validateX(x)) {
                        coordinates.setX(x);
                        break;
                    } else {
                        System.out.println("Некорректный ввод. Попробуйте снова!");
                    }
                }

            } catch (WrongNumberException | NumberFormatException e) {
                System.out.println("Некорректный ввод. Попробуйте снова!");
            }
        }

        Double y;
        while (true) {
            try {
                System.out.println("Введите координату Y. Тип: Double. Это поле не может быть null.");
                nextLine = scanner.nextLine();
                inputValidator.canBeNull(false);

                if (inputValidator.validate(nextLine)) {
                    y = Double.parseDouble(nextLine);

                    if (coordinateValidator.validateY(y)) {
                        coordinates.setY(y);
                        break;
                    } else {
                        System.out.println("Поле было введено некорректно. Попробуйте снова!");
                    }
                } else {
                    System.out.println("Поле не может быть null, попробуйте снова!");
                }

            } catch (WrongNumberException | NullException | NumberFormatException e) {
                System.out.println("Некорректный ввод. Попробуйте снова!");
            }
        }

        return coordinates;
    }
}
