package commands;

import exceptions.BuildObjectException;
import managers.CollectionManager;
import managers.CommandManager;
import server.CommandData;
import server.Server;

import java.io.IOException;
import java.io.Serial;
import java.io.Serializable;


public abstract class Command implements Serializable {
    private Object argument;
    private CollectionManager csvManager;
    private Server server;
    private CommandManager manager;
    private CommandExecuter executer;
    @Serial
    private static final long serialVersionUID = 1L;

    //public abstract void initialize(CommandData commandData);

    public abstract String getName();
    public abstract void execute(CommandData commandData) throws BuildObjectException, IOException;
    public Object getArgument() { return argument; }
    public void setArgument(String argument) {
        this.argument = argument;
    }

    public void setCsvManager(CollectionManager csvManager) {
        this.csvManager = csvManager;
    }
    public CollectionManager getCsvManager() { return csvManager; }

    public void setServer(Server server) {
        this.server = server;
    }
    public Server getServer() {
        return server;
    }

    public void setManager(CommandManager manager) { this.manager = manager; }
    public CommandManager getManager() {
        return manager;
    }

    public void setExecuter(CommandExecuter executer) {
        this.executer = executer;
    }
    public CommandExecuter getExecuter() {
        return executer;
    }

    public void setArgument(Object argument) {
        this.argument = argument;
    }
    //public void initialize(CollectionManager manager, Server server) { }

}
