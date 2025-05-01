package commands;

import client.Client;
import exceptions.BuildObjectException;
import managers.CSVManager;
import model.StudyGroup;
import validators.IdValidator;

import java.io.Serializable;
import java.util.Iterator;
import java.util.Objects;

public class RemoveAllByShouldBeExpelled extends Command implements Serializable {
    private static final long serialVersionUID = 12L;
    CSVManager manager;
    private Client client;
    private int argument;

    public RemoveAllByShouldBeExpelled(CSVManager manager, Client client) {
        super();
        this.manager = manager;
        this.client = client;
    }

    public RemoveAllByShouldBeExpelled(int argument) {
        this.argument = argument;
    }

    @Override
    public String getName() {
        return "remove_all_by_should_be_expelled";
    }

    @Override
    public String getDescription() {
        return "Удаляет все элементы коллекции, значения поля \"Should be expelled\" которых совпадает с введенным аргументом";
    }

    @Override
    public boolean checkArgument(Object argument) {
        if (argument == null) {
            System.out.println("Команда имеет один аргумент типа int");
            return false;
        } else if (argument instanceof String) {
            try {
                Integer.parseInt((String) argument);
                return true;
            } catch (NumberFormatException e) {
                System.out.println("Команда имеет один аргумент типа int");
                return false;
            }
        }
        return true;
    }

    @Override
    public void execute() throws BuildObjectException {
        if (checkArgument(this.getArgument())) {
            int shouldBeExpelled = Integer.parseInt((String) this.getArgument());
            client.sendObjectToServer(new RemoveAllByShouldBeExpelled(shouldBeExpelled));
            client.receivedObjectFromServer();
        }
    }
}
