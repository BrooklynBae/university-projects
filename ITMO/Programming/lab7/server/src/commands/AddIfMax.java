package commands;

import database.DatabaseHandler;
import exceptions.BuildObjectException;
import managers.CollectionManager;
import model.StudyGroup;
import server.CommandData;
import server.Server;

import java.io.Serializable;

public class AddIfMax extends Command implements Serializable {
    private static final long serialVersionUID = 3L;
    CollectionManager manager;
    private transient Server server;
    StudyGroup studyGroup;
    private DatabaseHandler databaseHandler;

    @Override
    public String getName() {
        return "add_if_max";
    }

    @Override
    public void execute(CommandData commandData) throws BuildObjectException {
        manager = commandData.getCsvManager();
        server = commandData.getServer();
        databaseHandler = commandData.getDatabaseHandler();

        if (manager.getCollection() != null || !manager.getCollection().isEmpty())  {
            var maxCount = maxCount();

            if (studyGroup.getStudentsCount() > maxCount) {
                DatabaseHandler.insertStudyGroup(studyGroup);
                manager.getCollection().push(studyGroup);
                server.sendMessageToClient("Объект \"Study group\" добавлен!");
            } else {
                server.sendMessageToClient("Значение элемента ниже, чем максимальное. Элемент не добавлен.");
            }
        } else {
            server.sendMessageToClient("Эта команда пока не работает, коллекция пуста!"); //программу пидора исправить
        }
    }
    private Long maxCount() {
        return manager.getCollection().stream()
                .map(StudyGroup::getStudentsCount)
                .mapToLong(Long::longValue)
                .max()
                .orElse(-1);
    }
}

