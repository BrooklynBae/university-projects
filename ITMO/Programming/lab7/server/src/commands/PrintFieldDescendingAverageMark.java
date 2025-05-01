package commands;

import exceptions.BuildObjectException;
import managers.CollectionManager;
import model.StudyGroup;
import server.CommandData;
import server.Server;

import java.io.Serial;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;

public class PrintFieldDescendingAverageMark extends Command implements Serializable {
    @Serial
    private static final long serialVersionUID = 11L;
    CollectionManager manager;
    private Server server;


    @Override
    public String getName() {
        return "print_field_descending_average_mark";
    }

    @Override
    public void execute(CommandData commandData) throws BuildObjectException {
        manager = commandData.getCsvManager();
        server = commandData.getServer();

            if (manager.getCollection() != null || !manager.getCollection().isEmpty()) {
                Iterator<StudyGroup> iterator = manager.getCollection().iterator();
                ArrayList<Float> marks = new ArrayList<>();

                while (iterator.hasNext()) {
                    marks.add(iterator.next().getAverageMark());
                }
                marks.sort(Collections.reverseOrder());
                server.sendMessageToClient(marks.toString());
            } else {
                server.sendMessageToClient("Команда пока не работает, коллекция пуста!");
            }
        }

    }

