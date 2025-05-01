package commands;

import exceptions.BuildObjectException;
import managers.CommandManager;

import java.io.Serial;
import java.io.Serializable;
import java.util.Optional;

public class Help extends Command implements Serializable {
    @Serial
    private static final long serialVersionUID = 9L;
    final CommandManager commandManager;
    public Help(CommandManager commandManager) {
        super();
        this.commandManager = commandManager;
    }

    @Override
    public String getName() {
        return "help";
    }

    @Override
    public String getDescription() {
        return "Выводит справку по доступным командам";
    }

    @Override
    public boolean checkArgument(Object argument) {
        if (argument == null) {
            return true;
        } else {
            System.out.println("Команда \"help\" имеет 1 или 0 аргументов");
            return false;
        }
    }

    @Override
    public void execute() throws BuildObjectException {

        if (this.getArgument() == null) {
            commandManager.getCommandMap().forEach((string, command) -> System.out.println(string + " : " + command.getDescription()));
        } else {
            var command = commandManager.getCommandMap().get(this.getArgument());
            System.out.println(this.getArgument() + " : " + Optional.ofNullable(command).map(Command::getDescription).orElse("Такой команды не существует :("));
            this.setArgument(null);
        }
    }
}
