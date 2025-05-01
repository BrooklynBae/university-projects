package commands;

import exceptions.BuildObjectException;
import managers.CollectionManager;
import server.CommandData;
import server.Server;

import java.io.Serial;
import java.io.Serializable;

public class Info extends Command implements Serializable {
    @Serial
    private static final long serialVersionUID = 10L;
    CollectionManager manager;
    private Server server;

    @Override
    public String getName() {
        return "info";
    }

    @Override
    public void execute(CommandData commandData) throws BuildObjectException {
        manager = commandData.getCsvManager();
        server = commandData.getServer();

        if (manager.getCollection() != null || !manager.getCollection().isEmpty()) {
            System.out.println(manager.getCollection().peek());
            server.sendMessageToClient("Тип коллекции: " + manager.getCollection().getClass() +
                    "\nДата инициализации: " + manager.getCollection().peek().getCreationDate().toString() +
                    "\nРазмер коллекции: " + manager.getCollection().size());
        } else {
            server.sendMessageToClient("Команда пока не работает, коллекция пуста!");
        }
    }
}