package commands;

import database.DatabaseHandler;
import enums.Semester;
import exceptions.BuildObjectException;
import managers.CollectionManager;
import model.StudyGroup;
import server.CommandData;
import server.Server;

import java.io.Serial;
import java.io.Serializable;
import java.util.Iterator;

public class CountLessThanSemesterEnum extends Command implements Serializable {
    @Serial
    private static final long serialVersionUID = 6L;
    private transient CollectionManager manager;
    private transient Server server;
    private int argument;

    @Override
    public String getName() {
        return "count_less_than_semester_enum";
    }


    @Override
    public void execute(CommandData commandData) throws BuildObjectException {
        manager = commandData.getCsvManager();
        server = commandData.getServer();

        if (manager.getCollection() != null || !manager.getCollection().isEmpty()) {
            Iterator<StudyGroup> iterator = manager.getCollection().iterator();
            int i = 0;
            while (iterator.hasNext()) {
                if (argument > Semester.getKey(iterator.next().getSemesterEnum())) {
                    i++;
                }
            }
            server.sendMessageToClient("Найдено элементов: " + i);
        } else {
            server.sendMessageToClient("Команда пока не работает, коллекция пуста!");
        }
    }

}