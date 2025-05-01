package commands;

import database.DatabaseHandler;
import exceptions.BuildObjectException;
import managers.CollectionManager;
import model.StudyGroup;
import server.CommandData;
import server.Server;

import javax.xml.crypto.Data;
import java.io.Serial;
import java.io.Serializable;

public class RemoveGreater extends Command implements Serializable {
    @Serial
    private static final long serialVersionUID = 14L;
    StudyGroup studyGroup;
    CollectionManager manager;
    Server server;
    private DatabaseHandler databaseHandler;

    @Override
    public String getName() {
        return "remove_greater";
    }


    @Override
    public void execute(CommandData commandData) throws BuildObjectException {
        manager = commandData.getCsvManager();
        server = commandData.getServer();
        databaseHandler = commandData.getDatabaseHandler();

            if (manager.getCollection() == null || manager.getCollection().isEmpty()) {
                server.sendMessageToClient("Команда пока не работате, коллекция пуста!");
            } else if (studyGroup != null){
                //manager.removeGreater(studyGroup);
                //server.sendMessageToClient("Удалено элементов: " + manager.removeGreater(studyGroup).size());
                server.sendMessageToClient(manager.removeGreaterThan(commandData.getUser(), databaseHandler, studyGroup.getStudentsCount()));
            }
        }
    }