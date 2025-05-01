package commands;

import exceptions.BuildObjectException;
import managers.CollectionManager;
import server.CommandData;
import server.Server;

import java.io.Serial;
import java.io.Serializable;
import java.util.Collections;

public class Shuffle extends Command implements Serializable {
    @Serial
    private static final long serialVersionUID = 17L;
    CollectionManager manager;
    Server server;

    @Override
    public String getName() {
        return "shuffle";
    }


    @Override
    public void execute(CommandData commandData) throws BuildObjectException {
            if (manager.getCollection() == null || manager.getCollection().isEmpty()) {
                server.sendMessageToClient("Эта команда пока не работает, зачем перемешивать пустоту...");
            } else if (manager.getCollection().size() < 2) {
                server.sendMessageToClient("Перемешивать нечего, элемент один :)");
            } else {
                Collections.shuffle(manager.getCollection());
                server.sendMessageToClient("Коллекция перемешанна в случайном порядке!'");
            }
        }
    }

