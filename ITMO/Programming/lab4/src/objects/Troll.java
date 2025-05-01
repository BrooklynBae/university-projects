package objects;

import enums.Time;
import exceptions.ExceptionTime;

public class Troll extends Creatures {
    public Troll(String name) {
        super(name);
    }

    String pronoun = "он";
    private String time;

    public String getPronoun() {
        return pronoun;
    }

    @Override
    public void setTime(Time time) throws ExceptionTime {
        if (time != Time.DEFINETELY_NOT_TIME) {
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
