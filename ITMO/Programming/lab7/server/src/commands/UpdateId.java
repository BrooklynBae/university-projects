package commands;

import database.DatabaseHandler;
import database.User;
import exceptions.BuildObjectException;
import managers.CollectionManager;
import model.StudyGroup;
import server.CommandData;
import server.Server;
import validators.IdValidator;

import java.io.Serial;
import java.io.Serializable;
import java.util.Optional;

public class UpdateId extends Command implements Serializable {
    @Serial
    private static final long serialVersionUID = 18L;
    private StudyGroup studyGroup;
    private transient CollectionManager manager;
    private transient Server server;
    private Object argument;
    private DatabaseHandler databaseHandler;


    @Override
    public String getName() {
        return "update_id {element}";
    }

    @Override
    public void execute(CommandData commandData) throws BuildObjectException {
        manager = commandData.getCsvManager();
        server = commandData.getServer();
        databaseHandler = commandData.getDatabaseHandler();

        try {
            if (argument instanceof Long) {
                Long finalId = (Long) argument;
                handleIdCheck(finalId, commandData.getUser());
            } else {
                handleStudyGroup(studyGroup);
            }
        } catch (Exception e) {
            System.out.println(e.getLocalizedMessage());
        }
    }

    private void handleIdCheck(Long finalId, User user) throws BuildObjectException {
        if (manager.getCollection() == null || manager.getCollection().isEmpty()) {
            server.sendMessageToClient("Команда пока не работает, коллекция пуста!");
        } else {
            StudyGroup elementToRemove = IdValidator.checkById(finalId, manager);
            if (elementToRemove == null) {
                server.sendMessageToClient("Элемента с таким id не найдено");
            } else if (!elementToRemove.getOwner().equals(user.getUsername())) {
                server.sendMessageToClient("У вас нет прав на удаление этого элемента");
            } else {
                manager.getCollection().remove(elementToRemove);
                databaseHandler.deleteStudyGroupsByKeyFromDataBase(finalId, user);
                //server.sendMessageToClient("Элемент с id " + finalId + " был удален.");
                server.sendMessageToClient("Элемент с таким id найден.");
            }
        }
    }

    private void handleStudyGroup(StudyGroup studyGroup) throws BuildObjectException {
        if (studyGroup != null) {
            System.out.println(studyGroup);
            DatabaseHandler.insertStudyGroup(studyGroup);
            manager.getCollection().push(studyGroup);
            server.sendMessageToClient("Объект \"Study group\" добавлен!");
        }
    }
}
