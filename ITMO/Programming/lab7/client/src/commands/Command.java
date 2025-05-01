package commands;

import exceptions.BuildObjectException;

import java.io.Serializable;


public abstract class Command implements Serializable {
    private static final long serialVersionUID = 1L;
    private Object argument;

    public abstract String getName();
    public abstract String getDescription();

    public Command() {
    }

    public abstract boolean checkArgument(Object argument);

    public abstract void execute() throws BuildObjectException;

    public Object getArgument() {
        return argument;
    }

    public void setArgument(String argument) {
        this.argument = argument;
    }

}
