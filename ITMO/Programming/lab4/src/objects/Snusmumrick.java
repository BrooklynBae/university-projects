package objects;

import enums.*;
import exceptions.ExceptionTime;

public class Snusmumrick extends Creatures {
    private static String pronoun = "он";
    private String time;

    public static class Pronoun {
        public static String getPronoun() {
            return pronoun;
        }
    }

    public Snusmumrick(String name) {
        super(name);
    }

     public class Heart {
        String pronoun = "оно";
        String name;

        public Heart(String name) {this.name = name;}

        public String getPronoun() {
            return pronoun;
        }

        public String getName() {
            return name;
        }

        public void toSetCharacteristic(Characteristic characteristic) {
            setCharacteristic(characteristic);
        }

        public String toGetCharacteristic() {
            return getCharacteristic();
        }

    }

    public class Whistle {

        String name;

        public Whistle(String name) {
            this.name = name;
        }

        public String getName() {
            return name;
        }

        public void toSetCharacteristic(Characteristic characteristic) {
            setCharacteristic(characteristic);
        }
        public String toGetCharacteristic() {
            return getCharacteristic();
        }

        public void toSetLocation(Location location) {
            setLocation(location);
        }

        public String toGetLocation() {
            return getLocation();
        }
    }

    @Override
    public void setTime(Time time) throws ExceptionTime {
        if(time != Time.DEFINETELY_NOT_TIME) {
            this.time = time.definition;
        } else {
            throw new ExceptionTime();
        }
    }

    @Override
    public String getTime() {
        return time;
    }

}
