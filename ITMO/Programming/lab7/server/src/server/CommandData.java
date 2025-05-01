package server;

import commands.CommandExecuter;
import database.DatabaseHandler;
import database.User;
import managers.CollectionManager;
import managers.CommandManager;

import java.io.Serializable;

public class CommandData implements Serializable {
    private static final long serialVersionUID = 5824144243465154839L;
    private CollectionManager csvManager;
    private Server server;
    private CommandManager manager;
    private CommandExecuter executer;
    private Object arg;
    private User user;
    private DatabaseHandler databaseHandler;

    public User getUser() {
        return user;
    }
    public void setUser(User user) {
        this.user = user;
    }

    public void setCsvManager(CollectionManager csvManager) {
        this.csvManager = csvManager;
    }
    public CollectionManager getCsvManager() { return csvManager; }

    public DatabaseHandler getDatabaseHandler() {
        return databaseHandler;
    }
    public void setDatabaseHandler(DatabaseHandler databaseHandler) {
        this.databaseHandler = databaseHandler;
    }

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

}
