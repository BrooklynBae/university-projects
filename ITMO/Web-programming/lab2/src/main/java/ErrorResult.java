import java.io.Serializable;

public class ErrorResult implements Serializable {
    public String errorMessage;

    public ErrorResult(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }
}
