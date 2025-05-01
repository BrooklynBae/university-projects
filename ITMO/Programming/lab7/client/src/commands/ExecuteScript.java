package commands;

import exceptions.BuildObjectException;
import managers.CSVManager;

import java.io.*;
import java.util.*;

public class ExecuteScript extends Command implements Serializable{
    @Serial
    private static final long serialVersionUID = 7L;
    CSVManager manager;
    CommandExecuter executer;
    Stack<String> usedPath;

    private static ExecuteScript instance;

    private ExecuteScript(CSVManager manager, CommandExecuter executer) {
        super();
        this.manager = manager;
        this.executer = executer;
        usedPath = new Stack<>();
    }

    public static ExecuteScript getInstance(CSVManager manager, CommandExecuter executer) {
        if (instance == null) {
            instance = new ExecuteScript(manager, executer);
        }
        return instance;
    }

    @Override
    public String getName() {
        return "execute_script";
    }

    @Override
    public String getDescription() {
        return "Выполняет скрипт";
    }

    @Override
    public boolean checkArgument(Object argument) {
        if (argument == null) {
            System.out.println("Укажите путь, что выполнять то...");
        } else {
            File filenameCheck = new File(String.valueOf(this.getArgument()));
            return filenameCheck.exists();
        }
        return false;
    }

    @Override
    public void execute() throws BuildObjectException {
        try {
            if (checkArgument(this.getArgument())) {
                if (!usedPath.isEmpty() && usedPath.contains((String) this.getArgument())) {
                    System.out.println("Опа, рекурсия");
                    return;
                }
                assert usedPath != null;
                usedPath.push((String) this.getArgument());

                executer.startExecutingScript(new BufferedReader(new InputStreamReader(new FileInputStream((String) this.getArgument()))));
            } else {
                System.out.println("Такого файла не существует.");
            }
        } catch (RuntimeException | IOException e) {
            throw new RuntimeException("Беды...");
        }
    }
}


