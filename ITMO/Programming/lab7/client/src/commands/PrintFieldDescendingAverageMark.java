package commands;

import client.Client;
import exceptions.BuildObjectException;
import managers.CSVManager;
import model.StudyGroup;

import java.io.Serial;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;

public class PrintFieldDescendingAverageMark extends Command implements Serializable {
    @Serial
    private static final long serialVersionUID = 11L;
    CSVManager manager;
    private Client client;

    public PrintFieldDescendingAverageMark() { }

    public PrintFieldDescendingAverageMark(CSVManager manager, Client client) {
        super();
        this.manager = manager;
        this.client = client;
    }

    @Override
    public String getName() {
        return "print_field_descending_average_mark";
    }

    @Override
    public String getDescription() {
        return "Выводит значения поля \"Average mark\" всех элементов в порядке убывания";
    }

    @Override
    public boolean checkArgument(Object argument) {
        if (argument == null) {
            return true;
        } else {
            System.out.println("Команда \"print field descending average mark\" не имеет аргуентов");
            return false;
        }
    }

    @Override
    public void execute() throws BuildObjectException {
        if (checkArgument(this.getArgument())) {
            client.sendObjectToServer(new PrintFieldDescendingAverageMark());
            client.receivedObjectFromServer();
        }

    }
}
