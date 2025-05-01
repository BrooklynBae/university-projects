package managers;

import client.Client;
import commands.*;
import builders.StudyGroupBuilder;
import exceptions.BuildObjectException;
import exceptions.UnknownCommandException;
import validators.CollectionValidator;

import java.util.LinkedHashMap;
import java.util.NoSuchElementException;

public class CommandManager {

    private final LinkedHashMap<String, Command> commandMap;
    private Client client;
    private StudyGroupBuilder studyGroupBuilder;
    private CommandExecuter executer;
    private CSVManager manager;
    private CollectionValidator collectionValidator;

    public CommandManager(CSVManager manager, Client client) {
        commandMap = new LinkedHashMap<>();
        studyGroupBuilder = new StudyGroupBuilder();
        executer = new CommandExecuter(this);
        collectionValidator = new CollectionValidator();
        this.client = client;
        this.manager = manager;
    }
    public void regCommands(){
        commandMap.put("add", new Add(studyGroupBuilder, manager, client));
        commandMap.put("add_if_max", new AddIfMax(studyGroupBuilder, manager, client));
        commandMap.put("remove_greater", new RemoveGreater(studyGroupBuilder, manager, client));
        commandMap.put("update_id", new UpdateId(studyGroupBuilder, manager, client));
        commandMap.put("remove_by_id", new RemoveById(manager, client));
        commandMap.put("clean", new Clean(manager, client));
        commandMap.put("remove_all_by_should_be_expelled", new RemoveAllByShouldBeExpelled(manager, client));
        commandMap.put("print_field_descending_average_mark", new PrintFieldDescendingAverageMark(manager, client));
        commandMap.put("count_less_than_semester_enum", new CountLessThanSemesterEnum(manager, client));
        //commandMap.put("shuffle", new Shuffle(client));
        commandMap.put("show", new Show(client));
        commandMap.put("info", new Info(client));
        commandMap.put("exit", new Exit(manager, client));
        commandMap.put("help", new Help(this));
        commandMap.put("execute_script", ExecuteScript.getInstance(manager, executer));
        //commandMap.put("save", new Save(client, manager));
    }
    public void executeCommand(String str) throws UnknownCommandException, BuildObjectException {
        try {
            String[] args = str.split(" ");

            try {
                if (str.isEmpty() || str.isBlank() || !commandMap.containsKey(args[0]))
                    throw new UnknownCommandException("\nКоманды " + args[0] + " не обнаружено :( ");

                if (args.length > 1) {
                    commandMap.get(args[0]).setArgument(args[1]);
                    commandMap.get(args[0]).execute();
                } else {
                    commandMap.get(args[0]).execute();
                    commandMap.get(args[0]).setArgument(null);
                }

            } catch (IllegalArgumentException | NullPointerException | NoSuchElementException e) {
                System.err.println("Выполнение команды пропущено из-за неправильных предоставленных аргументов! (" + e.getMessage() + ")");
            } catch (BuildObjectException | UnknownCommandException e) {
                System.err.println(e.getMessage());
            } catch (Exception e) {
                e.printStackTrace();
                System.err.println("В командном менеджере произошла непредвиденная ошибка! ");
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public LinkedHashMap<String, Command> getCommandMap() {
        return commandMap;
    }
}
