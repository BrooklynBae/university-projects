package commands;

import database.DatabaseHandler;
import database.User;
import managers.CollectionManager;
import server.CommandData;
import server.Server;
import validators.IdValidator;

import java.io.Serial;
import java.io.Serializable;


public class Clean extends Command implements Serializable {
    @Serial
    private static final long serialVersionUID = 4L;
    CollectionManager collectionManager;
    private transient Server server;
    private DatabaseHandler databaseHandler;

    @Override
    public String getName() {
        return "clean";
    }


    @Override
    public void execute(CommandData commandData) {
        collectionManager = commandData.getCsvManager();
        server = commandData.getServer();
        databaseHandler = commandData.getDatabaseHandler();

            if (collectionManager.getCollection() == null || collectionManager.getCollection().isEmpty()) {
                server.sendMessageToClient("Здесь и так чисто!");
            } else {
                databaseHandler.deleteAllStudyGroupsFromDataBase(commandData.getUser());
                collectionManager.clearCollection(commandData.getUser());
                IdValidator.clearHashSet();
                server.sendMessageToClient("Коллекция очищена!");
            }
        }

}
