package commands;

import exceptions.BuildObjectException;
import managers.CollectionManager;
import model.StudyGroup;
import server.CommandData;
import server.Server;

import java.io.Serial;
import java.io.Serializable;


public class Show extends Command implements Serializable {
    @Serial
    private static final long serialVersionUID = 16L;
    private CollectionManager manager;
    private Server server;


    @Override
    public String getName() {
        return "show";
    }
    @Override
    public String toString() {
        return "show";
    }


    @Override
    public void execute(CommandData commandData) throws BuildObjectException {
        manager = commandData.getCsvManager();
        server = commandData.getServer();

            if (manager.getCollection() == null || manager.getCollection().isEmpty()) {
                server.sendMessageToClient("Коллекция пустая, показывать нечего");
            } else {
                StringBuilder stringBuilder = new StringBuilder();
                for (StudyGroup studyGroup : manager.getCollection()) {
                    stringBuilder.append(StudyGroup.toString(studyGroup)).append("\n");
                }
                server.sendMessageToClient(stringBuilder.toString());
            }
        }
    }

