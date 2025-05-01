package commands;

import builders.StudyGroupBuilder;
import client.Client;
import exceptions.BuildObjectException;
import managers.CSVManager;
import model.StudyGroup;
import validators.IdValidator;

import java.io.Serial;
import java.io.Serializable;
import java.util.Objects;

public class UpdateId extends Command implements Serializable {
    @Serial
    private static final long serialVersionUID = 18L;
    StudyGroupBuilder builder;
    private StudyGroup studyGroup;
    CSVManager manager;
    Client client;
    private Object argument;

    public UpdateId(Long argument) {
        this.argument = argument;
    }

    public UpdateId(StudyGroup studyGroup) {
        this.studyGroup = studyGroup;
    }

    public UpdateId(StudyGroupBuilder builder, CSVManager manager, Client client) {
        super();
        this.builder = builder;
        this.manager = manager;
        this.client = client;
    }


    @Override
    public String getName() {
        return "update_id id {element}";
    }

    @Override
    public String getDescription() {
        return "Обновляет значения элемента коллекции, id которого равен заданному";
    }

    @Override
    public boolean checkArgument(Object argument) {
        if (argument == null) {
            System.out.println("Команда \"update id\" имеет один аргумент типа Long");
            return false;
        } else if (argument instanceof String) {
            try {
                Long.parseLong((String) argument);
                return true;
            } catch (NumberFormatException e) {
                System.out.println("Команда \"update id\" имеет один аргумент типа Long");
                return false;
            }
        }
        return true;
    }

    @Override
    public void execute() throws BuildObjectException {
        if (checkArgument(this.getArgument()) && IdValidator.validateInputId((String) this.getArgument()) != null) {

            Long finalId = IdValidator.validateInputId((String) this.getArgument());
            client.sendObjectToServer(new UpdateId(finalId));
            client.receivedObjectFromServer();


            if (Objects.equals(client.getMessage(), "Элемент с таким id найден.")) {
                StudyGroupBuilder builder = new StudyGroupBuilder();
                StudyGroup sg = builder.buildObject();
                sg.setOwner(CommandData.getUser().getUsername());
                client.sendObjectToServer(new UpdateId(sg));
                client.receivedObjectFromServer();
            }
        }
    }
}