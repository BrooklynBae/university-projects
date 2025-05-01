//package model;
//
//import exceptions.NullException;
//import exceptions.WrongNumberException;
//
//import java.io.Serializable;
//
//public class Coordinates implements Serializable {
//
//    private static final long serialVersionUID = 454165466L;
//    private long x; //Максимальное значение поля: 987
//    private Double y; //Поле не может быть null
//
//    public Coordinates() {}
//
//    public void setX(long x) throws WrongNumberException {
//        this.x = x;
//    }
//
//    public long getX() {
//        return x;
//    }
//
//    public void setY(Double y) throws NullException {
//        this.y = y;
//    }
//    public Double getY() {
//        return y;
//    }
//
//}
package model;

import com.opencsv.bean.CsvBindByName;
import exceptions.NullException;
import exceptions.WrongNumberException;

import java.io.Serializable;

public class Coordinates implements Serializable {

    private static final long serialVersionUID = 454165465L;

    private Long x; //Максимальное значение поля: 987

    private Double y; //Поле не может быть null

    public Coordinates() {}

    public Coordinates(long x, Double y) {
        this.y = y;
        this.x = x;
    }

    public void setX(Long x) throws WrongNumberException {
        if (x > 987) {
            throw new WrongNumberException("Максимальное значение поля x: 987");
        }
        this.x = x;
    }

    public Long getX() {
        return x;
    }

    public void setY(Double y) throws NullException {
        if (y == null) {
            throw new NullException("Поле y не может быть null");
        }
        this.y = y;
    }

    public Double getY() {
        return y;
    }
}
