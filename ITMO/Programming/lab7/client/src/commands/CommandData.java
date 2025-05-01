package commands;

import database.User;

import java.io.Serializable;

public class CommandData implements Serializable {
    private static final long serialVersionUID = 5824144243465154839L;
    private int key;
    private Float distance;
    private String name;
    static User user;
    private int breakpoint;

    public void setBreakpoint(int breakpoint) {
        this.breakpoint = breakpoint;
    }

    public int getBreakpoint() {
        return breakpoint;
    }

    public Float getDistance() {
        return distance;
    }

    public void setDistance(Float distance) {
        this.distance = distance;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setKey(int key) {
        this.key = key;
    }

    public static User getUser() {
        return user;
    }

    public static void setUser(User user1) {
        user = user1;
    }

    public int getKey() {
        return key;
    }
}
