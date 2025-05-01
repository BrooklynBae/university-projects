package validators;

public class InputValidator {
    boolean canBeNull = false;

    public boolean validate(String string) {
        if (!canBeNull) {
            return !string.isBlank() && !string.isEmpty();
        }
        return true;
    }

    public void canBeNull(boolean canBeNull) {
        this.canBeNull = canBeNull;
    }

}
