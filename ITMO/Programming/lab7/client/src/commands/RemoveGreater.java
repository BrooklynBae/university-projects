package commands;

import builders.StudyGroupBuilder;
import client.Client;
import exceptions.BuildObjectException;
import managers.CSVManager;
import model.StudyGroup;

import java.io.Serial;
import java.io.Serializable;
import java.util.Iterator;

public class RemoveGreater extends Command implements Serializable {
    @Serial
    private static final long serialVersionUID = 14L;
    private StudyGroup studyGroup;
    StudyGroupBuilder builder;
    CSVManager manager;
    private Client client;

    public RemoveGreater(StudyGroupBuilder builder, CSVManager manager, Client client) {
        super();
        this.builder = builder;
        this.manager = manager;
        this.client = client;
    }

    public RemoveGreater(StudyGroup studyGroup) {
        this.studyGroup = studyGroup;
    }

    @Override
    public String getName() {
        return "remove_greater";
    }

    @Override
    public String getDescription() {
        return "Удаляет наибольший элемент";
    }

    @Override
    public boolean checkArgument(Object argument) {
        if (argument == null) {
            return true;
        } else {
            System.out.println("Команда \"Remove greater\" не имеет аргументов!");
            return false;
        }
    }

    @Override
    public void execute() throws BuildObjectException {
        if (checkArgument(this.getArgument())) {
            builder = new StudyGroupBuilder();
            StudyGroup sg = builder.buildObject();
            client.sendObjectToServer(new RemoveGreater(sg));
            client.receivedObjectFromServer();
        }
    }
}
