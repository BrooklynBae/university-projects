package commands;

import database.DatabaseHandler;
import managers.CollectionManager;
import model.StudyGroup;
import server.CommandData;
import server.Server;

import java.io.IOException;
import java.io.Serial;
import java.io.Serializable;

public class Add extends Command implements Serializable {
    @Serial
    private static final long serialVersionUID = 2L;
    private StudyGroup studyGroup;
    private transient Server server;
    private transient CollectionManager manager;
    private DatabaseHandler databaseHandler;
    private CommandData commandData;

    @Override
    public String getName() {
        return "add";
    }

    @Override
    public void execute(CommandData commandData) throws IOException {
        manager = commandData.getCsvManager();
        server = commandData.getServer();
        databaseHandler = commandData.getDatabaseHandler();

        if (studyGroup != null) {
            System.out.println(studyGroup);
            DatabaseHandler.insertStudyGroup(studyGroup);
            manager.getCollection().push(studyGroup);

            server.sendMessageToClient("Объект \"Study group\" добавлен!");
        } else {
            server.sendMessageToClient("Study group is null");
        }
    }
}