package commands;

import client.Client;
import exceptions.BuildObjectException;
import managers.CSVManager;

import java.io.Serial;
import java.io.Serializable;

public class Info extends Command implements Serializable {
    @Serial
    private static final long serialVersionUID = 10L;
    private Client client;

    public Info(Client client) {
        super();
        this.client = client;
    }
    public Info() {}

    @Override
    public String getName() {
        return "info";
    }

    @Override
    public String getDescription() {
        return "Выводит в стандартный поток вывода информацию о коллекции";
    }

    @Override
    public boolean checkArgument(Object argument) {
        if (argument == null) {
            return true;
        } else {
            System.out.println("Команда \"info\" не имеет аргументов!");
            return false;
        }
    }

    @Override
    public void execute() throws BuildObjectException {
        if (checkArgument(this.getArgument())) {
            client.sendObjectToServer(new Info());
            client.receivedObjectFromServer();
        }
    }
}
