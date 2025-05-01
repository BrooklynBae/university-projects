package commands;

import database.DatabaseHandler;
import exceptions.BuildObjectException;
import managers.CollectionManager;
import model.StudyGroup;
import server.CommandData;
import server.Server;
import validators.IdValidator;

import java.io.IOException;
import java.io.Serializable;
import java.util.Iterator;
import java.util.Objects;

public class RemoveAllByShouldBeExpelled extends Command implements Serializable {
    private static final long serialVersionUID = 12L;
    CollectionManager manager;
    Server server;
    private int argument;
    private DatabaseHandler databaseHandler;

    @Override
    public String getName() {
        return "remove_all_by_should_be_expelled";
    }

    @Override
    public void execute(CommandData commandData) throws BuildObjectException {
        manager = commandData.getCsvManager();
        server = commandData.getServer();
        databaseHandler = commandData.getDatabaseHandler();

        if (manager.getCollection() == null || manager.getCollection().isEmpty()) {
            server.sendMessageToClient("Команда пока не работает, коллекция пуста!");

        } else {
            manager.removeByShouldBeExpelled(argument, commandData.getUser(), databaseHandler);
            server.sendMessageToClient("Объкты удалены!");
        }
    }
}

