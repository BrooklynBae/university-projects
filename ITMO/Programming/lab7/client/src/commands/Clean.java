package commands;

import client.Client;
import managers.CSVManager;
import validators.IdValidator;

import java.io.Serializable;


public class Clean extends Command implements Serializable {
    private static final long serialVersionUID = 4L;
    CSVManager manager;
    private Client client;

    public Clean() {}

    public Clean(CSVManager manager, Client client) {
        super();
        this.manager = manager;
        this.client = client;
    }

    @Override
    public String getName() {
        return "clean";
    }

    @Override
    public String getDescription() {
        return "Очищает коллекцию";
    }

    @Override
    public boolean checkArgument(Object argument) {
        if (argument == null) {
            return true;
        } else {
            System.out.println("Команда \"clean\" не имеет аргументов!");
            return false;
        }
    }

    @Override
    public void execute() {
        if (checkArgument(getArgument())) {
            client.sendObjectToServer(new Clean());
            client.receivedObjectFromServer();
        }
    }
}
