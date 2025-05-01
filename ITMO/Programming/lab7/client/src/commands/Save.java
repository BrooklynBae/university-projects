//package commands;
//
//import client.Client;
//import exceptions.BuildObjectException;
//import managers.CSVManager;
//
//import java.io.Serial;
//import java.io.Serializable;
//
//public class Save extends Command implements Serializable {
//    @Serial
//    private static final long serialVersionUID = 15L;
//    CSVManager manager;
//    Client client;
//
//    public Save(Client client, CSVManager manager) {
//        this.client = client;
//        this.manager = manager;
//    }
//
//    public Save() {}
//
//    @Override
//    public String getName() {
//        return "save";
//    }
//
//    @Override
//    public String getDescription() {
//        return "Сохраняет коллекцию в файл";
//    }
//
//    @Override
//    public boolean checkArgument(Object argument) {
//        if (argument == null) {
//            return true;
//        } else {
//            System.out.println("Команда \"Save\" не имеет аргументов!");
//            return false;
//        }
//    }
//
//    @Override
//    public void execute() throws BuildObjectException {
//
//        if (manager.getCollection() == null || manager.getCollection().isEmpty()) {
//            client.sendObjectToServer(new Save());
//            client.receivedObjectFromServer();
//        }
//    }
//}