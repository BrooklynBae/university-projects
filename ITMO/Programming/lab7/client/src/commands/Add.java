package commands;

import builders.StudyGroupBuilder;
import client.Client;
import exceptions.BuildObjectException;
import managers.CSVManager;
import model.StudyGroup;
import validators.CollectionValidator;

import java.io.Serial;
import java.io.Serializable;
import java.util.Stack;

public class Add extends Command implements Serializable {
    @Serial
    private static final long serialVersionUID = 2L;
    private StudyGroup studyGroup;
    private StudyGroupBuilder studyGroupBuilder;
    private Client client;
    private CSVManager manager;

    public Add(StudyGroup studyGroup) {
        this.studyGroup = studyGroup;
    }

    public Add(StudyGroupBuilder studyGroupBuilder, CSVManager manager, Client client) {
        super();
        this.studyGroupBuilder = studyGroupBuilder;
        this.manager = manager;
        this.client = client;
    }

    @Override
    public String getName() {
        return "add";
    }

    @Override
    public String getDescription() {
        return "Добавляет новый элемент в коллекцию";
    }

    @Override
    public boolean checkArgument(Object argument) {
        if (argument == null) {
            return true;
        } else {
            System.out.println("Команда \"add\" не имеет аргументов!");
            return false;
        }
    }


    @Override
    public void execute() throws BuildObjectException {
        if (checkArgument(this.getArgument())) {
            StudyGroupBuilder builder = new StudyGroupBuilder();
            StudyGroup sg = builder.buildObject();
            sg.setOwner(CommandData.getUser().getUsername());

            client.sendObjectToServer(new Add(sg));
            client.receivedObjectFromServer();
        }
    }
}
