package commands;

import client.Client;
import exceptions.BuildObjectException;
import managers.CSVManager;

import model.StudyGroup;

import java.io.Serial;
import java.io.Serializable;


public class Show extends Command implements Serializable {
    @Serial
    private static final long serialVersionUID = 16L;
    private Client client;
    public Show( Client client) {
        super();
        this.client = client;
    }
    public Show() { }

    @Override
    public String getName() {
        return "show";
    }

    @Override
    public String getDescription() {
        return "Выводит все элементы коллекции в строковом представлении";
    }

    @Override
    public boolean checkArgument(Object argument) {
        if (argument == null) {
            return true;
        } else {
            System.out.println("Команда \"show\" не имеет аргументов!");
            return false;
        }
    }

    @Override
    public void execute() throws BuildObjectException {
        client.sendObjectToServer(new Show());
        client.receivedObjectFromServer();
    }
}
