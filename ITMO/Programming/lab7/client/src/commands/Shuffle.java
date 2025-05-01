//package commands;
//
//import client.Client;
//import exceptions.BuildObjectException;
//import managers.CSVManager;
//
//import java.io.Serial;
//import java.io.Serializable;
//import java.util.Collections;
//
//public class Shuffle extends Command implements Serializable {
//    @Serial
//    private static final long serialVersionUID = 17L;
//    Client client;
//
//    public Shuffle(Client client) {
//        super();
//        this.client = client;
//    }
//
//    public Shuffle() {}
//
//    @Override
//    public String getName() {
//        return "shuffle";
//    }
//
//    @Override
//    public String getDescription() {
//        return "Перемешивает элементы коллекции в случайном порядке";
//    }
//
//    @Override
//    public boolean checkArgument(Object argument) {
//        if (argument == null) {
//            return true;
//        } else {
//            System.out.println("Команда \"Shuffle\" не имеет аргументов!");
//            return false;
//        }
//    }
//
//    @Override
//    public void execute() throws BuildObjectException {
//        if (checkArgument(this.getArgument())) {
//            client.sendObjectToServer(new Shuffle());
//            client.receivedObjectFromServer();
//        }
//    }
//}
