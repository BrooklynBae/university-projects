package commands;

import exceptions.BuildObjectException;
import exceptions.UnknownCommandException;
import managers.CommandManager;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Scanner;

public class CommandExecuter implements Serializable {
    final CommandManager commandManager;
    private static final long serialVersionUID = 5L;
    public CommandExecuter(CommandManager commandManager) {
        this.commandManager = commandManager;
    }

//    public void startExecuting(InputStream input) {
//        Scanner scanner = new Scanner(input);
//
//        while (scanner.hasNext()) {
//            String line = scanner.nextLine().trim();
//            if (line.isEmpty()) continue;
//            try {
//                commandManager.executeCommand(line);
//                System.out.println();
//            } catch (BuildObjectException | UnknownCommandException e) {
//                System.err.println("Выполнение команды было прервано. Вы можете продолжать работу. Программа возвращена в безопасное состояние.");
//            }
//        }
//    }

//    public void startExecutingScript(BufferedReader reader) throws IOException {
//        String line;
//        ArrayList<String> commands = new ArrayList<>();
//
//        while ((line = reader.readLine()) != null) {
//            commands.add(line.trim());
//        }
//        for (String cm : commands) {
//            if (cm.isEmpty()) continue;
//            try {
//                commandManager.executeCommand(cm);
//            } catch (BuildObjectException | UnknownCommandException e) {
//                throw new RuntimeException(e);
//            }
//        }
//
//    }

}
