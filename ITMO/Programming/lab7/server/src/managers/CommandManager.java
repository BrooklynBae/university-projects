package managers;

import commands.*;
import exceptions.BuildObjectException;
import server.CommandData;
import server.Server;

import java.io.IOException;
import java.util.LinkedHashMap;

public class CommandManager {

    private final LinkedHashMap<String, Command> commandMap;
    private Server server;
    private CollectionManager manager;
    private Object argument;
    private CommandData commandData;

    public CommandManager(CollectionManager manager, Server server, Object argument, CommandData commandData) {
        commandMap = new LinkedHashMap<>();
        CommandExecuter executer = new CommandExecuter(this);
        this.server = server;
        this.manager = manager;
        this.argument = argument;
        this.commandData = commandData;
    }

    public void executeCommand(Command command) throws BuildObjectException, IOException {
        command.execute(commandData);
    }

    public LinkedHashMap<String, Command> getCommandMap() {
        return commandMap;
    }
}
