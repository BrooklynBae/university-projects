package exceptions;

public class BuildObjectException extends Throwable{
    public BuildObjectException(String errorOccuredDuringCre) {
        super(errorOccuredDuringCre);
    }
}
