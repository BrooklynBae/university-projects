package objects;

import enums.*;
import exceptions.ExceptionLocation;
import exceptions.ExceptionTime;
import interfaces.*;

import java.util.Objects;


//класс для всех объектов, которые могут двигаться
public abstract class Creatures implements CharacteristicInt, LocationInt {

    final private String name;
    private String characteristic;
    private String location;

    public Creatures(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    @Override
    public boolean equals(Object o) {
        if (o.getClass() != getClass()) {
            return false;
        } else {
            Creatures entity = (Creatures) o;
            return Objects.equals(name.toLowerCase(), entity.name.toLowerCase());
        }
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }

    @Override
    public String toString() {
        return name;
    }

    @Override
    public void setCharacteristic(Characteristic characteristic) {
        this.characteristic = characteristic.definition;
    }

    @Override
    public String getCharacteristic() {
        return characteristic;
    }


    @Override
    public void setLocation(Location location) {
        if(this.location != Location.NOT_A_LOCATION.definition) {
            this.location = location.definition;
        } else {
            throw new ExceptionLocation("Непроверяемое исключение");
        }
    }

    @Override
    public String getLocation() {
        return location;
    }


    public abstract void setTime(Time time) throws ExceptionTime;

    public abstract String getTime();

    public void sayPerson(ActionInt a) {
        a.say(this.getName());
    }

}
