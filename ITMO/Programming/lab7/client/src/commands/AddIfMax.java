package commands;

import builders.StudyGroupBuilder;
import client.Client;
import exceptions.BuildObjectException;
import managers.CSVManager;
import model.StudyGroup;

import java.io.Serializable;

public class AddIfMax extends Command implements Serializable {
    private static final long serialVersionUID = 3L;
    StudyGroupBuilder studyGroupBuilder;
    StudyGroup studyGroup;
    CSVManager manager;
    Client client;

    public AddIfMax(StudyGroup studyGroup) {
        this.studyGroup = studyGroup;
    }

    public AddIfMax(StudyGroupBuilder studyGroupBuilder, CSVManager manager, Client client) {
        super();
        this.studyGroupBuilder = studyGroupBuilder;
        this.manager = manager;
        this.client = client;
    }

    @Override
    public String getName() {
        return "add_if_max";
    }

    @Override
    public String getDescription() {
        return "Добавляет элемент, если значение выше максимального";
    }

    @Override
    public boolean checkArgument(Object argument) {
        if (argument == null) {
            return true;
        } else {
            System.out.println("Команда \"add if max\" не имеет аргуентов!");
            return false;
        }
    }

    @Override
    public void execute() throws BuildObjectException {
        if (checkArgument(this.getArgument())) {
            studyGroupBuilder = new StudyGroupBuilder();
            StudyGroup studyGroup = studyGroupBuilder.buildObject();
            studyGroup.setOwner(CommandData.getUser().getUsername());
            client.sendObjectToServer(new AddIfMax(studyGroup));
            client.receivedObjectFromServer();
        }
    }
}

