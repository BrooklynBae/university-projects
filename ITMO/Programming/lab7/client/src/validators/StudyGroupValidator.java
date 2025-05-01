package validators;

import exceptions.NullException;
import exceptions.WrongNumberException;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class StudyGroupValidator{

    public boolean validateAverageMark (Float averageMark) {
        return averageMark > 0;
    }

    public boolean validateX(Long x) throws WrongNumberException {
        return x < 987;
    }

    public boolean validateY(Double y) throws WrongNumberException, NullException {
        return y != null;
    }

    public boolean validateName(String name) {
        return !name.isEmpty() && !name.isBlank();
    }

    public boolean validateStudentsCount(Long studentsCount) {
        return studentsCount > 0;
    }

    public boolean validateShouldBeExpelled(Integer shouldBeExpelled) {
        return shouldBeExpelled!=null && shouldBeExpelled > 0;
    }
}
