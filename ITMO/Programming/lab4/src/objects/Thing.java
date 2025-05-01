package objects;

import enums.Characteristic;
import enums.Location;
import exceptions.ExceptionLocation;
import interfaces.ActionInt;
import interfaces.CharacteristicInt;
import interfaces.LocationInt;

import java.util.Objects;

//класс для неживых объектов, которые не могут взаимодействовать
public class Thing implements CharacteristicInt, LocationInt {

    private String name;
    private String charactreristic;
    private String location;

    @Override
    public boolean equals(Object o) {
        if (o.getClass() != getClass()) {
            return false;
        } else {
            Thing thing = (Thing) o;
            return Objects.equals(name.toLowerCase(), thing.name.toLowerCase());
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

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    @Override
    public void setCharacteristic(Characteristic charactreristic) {
        this.charactreristic = charactreristic.definition;
    }

    @Override
    public String getCharacteristic() {
        return charactreristic;
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



}
