package commands;

import client.Client;
import enums.Semester;
import exceptions.BuildObjectException;
import managers.CSVManager;
import model.StudyGroup;

import java.io.Serializable;
import java.util.Iterator;

public class CountLessThanSemesterEnum extends Command implements Serializable {
    private static final long serialVersionUID = 6L;
    CSVManager manager;
    private Client client;
    private int argument;

    public CountLessThanSemesterEnum(CSVManager manager, Client client) {
        super();
        this.manager = manager;
        this.client = client;
    }

    public CountLessThanSemesterEnum(int argument) {
        this.argument = argument;
    }

    @Override
    public String getName() {
        return "count_less_than_semester_enum";
    }

    @Override
    public String getDescription() {
        return "Выводит количество элементов коллекции, значения поля \"Semester\" которых, меньше заданного";
    }

    @Override
    public boolean checkArgument(Object argument) {

        if (argument == null) {
            System.out.println("У этой команды один аргумент, введите один из следующих:\n " + Semester.nameList());
            return false;
        } else {
            switch ((String) argument) {
                case "FOURTH", "FIFTH", "SIXTH", "SEVENTH", "EIGHT" -> {
                    return true;
                }
            }
            System.out.println("Некорректный ввод, попробуйте снова!\n" + Semester.nameList());
            return false;
        }
    }

    @Override
    public void execute() throws BuildObjectException {
        if (checkArgument(this.getArgument())) {
            Semester semester;
            switch ((String) this.getArgument()) {
                case "FOURTH" -> semester = Semester.FOURTH;
                case "FIFTH" -> semester = Semester.FIFTH;
                case "SIXTH" -> semester = Semester.SIXTH;
                case "SEVENTH" -> semester = Semester.SEVENTH;
                case "EIGHT" -> semester = Semester.EIGHTH;
                default -> throw new IllegalStateException("Некорректное значение: " + this.getArgument());
            }
            int arg = Semester.getKey(semester);

            client.sendObjectToServer(new CountLessThanSemesterEnum(arg));
            client.receivedObjectFromServer();

        }
    }
}
