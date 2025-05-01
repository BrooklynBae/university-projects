package commands;

import client.Client;
import exceptions.BuildObjectException;
import managers.CSVManager;

import java.io.Serial;
import java.io.Serializable;


public class Exit extends Command implements Serializable {
    @Serial
    private static final long serialVersionUID = 8L;
    CSVManager manager;
    Client client;

    public Exit(CSVManager manager, Client client) {
        super();
        this.manager = manager;
        this.client = client;
    }

    public Exit() {}

    @Override
    public String getName() {
        return "exit";
    }

    @Override
    public String getDescription() {
        return "Завершает программу (без сохраниния в файл)";
    }

    @Override
    public boolean checkArgument(Object argument) {
        if (argument == null) {
            return true;
        } else {
            System.out.println("Команда \"exit\" не имеет аргументов!");
            return false;
        }
    }

    @Override
    public void execute() throws BuildObjectException {
        if (checkArgument(this.getArgument())){
            client.sendObjectToServer(new Exit());
            client.receivedObjectFromServer();
            System.exit(0);
        }
    }
}
