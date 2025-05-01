import database.DatabaseHandler;
import server.Server;
import commands.CommandExecuter;
import managers.CollectionManager;
import managers.CommandManager;
import server.CommandData;

import java.sql.SQLException;

public class Main {
    public static void main(String[] args) throws ClassNotFoundException, SQLException {
        Class.forName("org.postgresql.Driver");
        //String jdbcURL = "jdbc:postgresql://pg:5432/studs";

        DatabaseHandler handler = new DatabaseHandler();
        handler.connectToDatabase();
        Server server;
        {
            CommandData commandData = new CommandData();
            CollectionManager collectionManager = new CollectionManager();
            //DatabaseHandler databaseHandler = new DatabaseHandler();
            server = new Server(commandData, handler);
            Object argument = new Object();
            collectionManager.setCollection(handler.loadCollectionFromDB());

            CommandManager commandManager = new CommandManager(collectionManager, server, argument, commandData);
            CommandExecuter executer = new CommandExecuter(commandManager);

            commandData.setExecuter(executer);
            commandData.setServer(server);
            commandData.setManager(commandManager);
            commandData.setCsvManager(collectionManager);
            commandData.setDatabaseHandler(handler);

            server.run();
        }
    }
}