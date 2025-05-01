package objects;

import enums.Location;
import enums.Time;
import exceptions.ExceptionTime;

public class Mom extends Creatures {
    private static String pronoun = "она";
    private String time;

    public Mom(String name) {
        super(name);
    }
    public class Hat {

        String pronoun = "он";
        String name;

        public Hat(String name) {
            this.name = name;
        }

        public String getPronoun() {
            return pronoun;
        }
        public String getName() {
            return name;
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
