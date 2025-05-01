package commands;

import database.DatabaseHandler;
import exceptions.BuildObjectException;
import managers.CollectionManager;
import model.StudyGroup;
import server.CommandData;
import server.Server;
import validators.IdValidator;

import java.io.Serial;
import java.io.Serializable;


public class RemoveById extends Command implements Serializable {
    @Serial
    private static final long serialVersionUID = 13L;
    CollectionManager manager;
    Server server;
    private Long argument;
    private DatabaseHandler databaseHandler;

    @Override
    public String getName() {
        return "remove_by_id";
    }

    @Override
    public void execute(CommandData commandData) throws BuildObjectException {
        manager = commandData.getCsvManager();
        server = commandData.getServer();
        databaseHandler = commandData.getDatabaseHandler();

            if (manager.getCollection() == null || manager.getCollection().isEmpty()) {
                server.sendMessageToClient("Команда пока не работает, коллекция пуста!");

            } else {
                server.sendMessageToClient(manager.removeById(argument, commandData.getUser(), databaseHandler));
            }
    }
}
