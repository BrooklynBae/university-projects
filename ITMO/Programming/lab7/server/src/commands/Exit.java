package commands;

import exceptions.BuildObjectException;
import managers.CollectionManager;
import server.CommandData;
import server.Server;

import java.io.Serial;
import java.io.Serializable;


public class Exit extends Command implements Serializable {
    @Serial
    private static final long serialVersionUID = 8L;
    CollectionManager manager;
    Server server;


    @Override
    public String getName() {
        return "exit";
    }

    @Override
    public void execute(CommandData commandData) throws BuildObjectException {
        server = commandData.getServer();
            server.sendMessageToClient("Завершение программы...");
            System.exit(0);
    }
}
