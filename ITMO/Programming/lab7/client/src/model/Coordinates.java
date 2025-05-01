package model;

import exceptions.NullException;
import exceptions.WrongNumberException;

import java.io.Serializable;

public class Coordinates implements Serializable {

    private static final long serialVersionUID = 454165465L;
    private Long x; //Максимальное значение поля: 987
    private Double y; //Поле не может быть null

    public Coordinates() {}

    public void setX(Long x) throws WrongNumberException {
        this.x = x;
    }

    public Long getX() {
        return x;
    }

    public void setY(Double y) throws NullException {
        this.y = y;
    }

    public Double getY() {
        return y;
    }

    public int compareTo(Coordinates o) {
        if (o == null) {
            return 1;
        }
        int result = Long.compare(this.x, o.x);
        if (result == 0)
            return Double.compare(this.y, o.y);
        return result;
    }
}