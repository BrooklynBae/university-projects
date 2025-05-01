package commands;

import client.Client;
import exceptions.BuildObjectException;
import managers.CSVManager;
import model.StudyGroup;
import validators.IdValidator;

import java.io.Serializable;


public class RemoveById extends Command implements Serializable {
    private static final long serialVersionUID = 13L;
    CSVManager manager;
    private Client client;
    private Long argument;

    public RemoveById(Long argument) {
        this.argument = argument;
    }
    public RemoveById(CSVManager manager, Client client) {
        super();
        this.manager = manager;
        this.client = client;
    }

    @Override
    public String getName() {
        return "remove_by_id";
    }

    @Override
    public String getDescription() {
        return "Удалает элемент по его id";
    }

    @Override
    public boolean checkArgument(Object argument) {
        if (argument == null) {
            System.out.println("Команда \"remove by id\" имеет один аргумент!");
            return false;
        } else { return true; }
    }

    @Override
    public void execute() throws BuildObjectException {
        if (checkArgument(this.getArgument()) && IdValidator.validateInputId((String) this.getArgument()) != null) {

                Long finalId = IdValidator.validateInputId((String) this.getArgument());
                client.sendObjectToServer(new RemoveById(finalId));
                IdValidator.removeElement(finalId);
                client.receivedObjectFromServer();
            }

    }
}
