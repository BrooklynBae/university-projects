package managers;

import database.DatabaseHandler;
import database.User;
import model.StudyGroup;

import java.io.*;
import java.util.*;
import java.util.stream.Collectors;

public class CollectionManager implements Serializable {
    public Stack<StudyGroup> studyGroupStack = new Stack<>();
    String ENV_KEY = "LAB_5";
    String pathToDataFile = System.getenv(ENV_KEY);
    public void setCollection(Stack<StudyGroup> studyGroupStack) {
        this.studyGroupStack = studyGroupStack;
    }

    public Stack<StudyGroup> getCollection() {
        return studyGroupStack;
    }

    public void clearCollection(User user) {
        Stack<StudyGroup> tempStack = new Stack<>();

        while (!studyGroupStack.isEmpty()) {
            StudyGroup studyGroup = studyGroupStack.pop();
            if (!studyGroup.getOwner().equals(user.getUsername())) {
                tempStack.push(studyGroup);
            }
        }
        while (!tempStack.isEmpty()) {
            studyGroupStack.push(tempStack.pop());
        }
    }

    public void removeByShouldBeExpelled(int key, User user, DatabaseHandler databaseHandler) {
        Stack<StudyGroup> tempStack = new Stack<>();

        while (!studyGroupStack.isEmpty()) {
            StudyGroup studyGroup = studyGroupStack.pop();
            if (studyGroup.getShouldBeExpelled() == key && studyGroup.getOwner().equals(user.getUsername())) {
                databaseHandler.deleteStudyGroupsByKeyFromDataBase(key, user);
            } else {
                tempStack.push(studyGroup);
            }
        }
        while (!tempStack.isEmpty()) {
            studyGroupStack.push(tempStack.pop());
        }
    }

    public String removeById(Long key, User user, DatabaseHandler databaseHandler) {
        Stack<StudyGroup> tempStack = new Stack<>();
        String message = "Элемента с таким id не найдено!";

        while (!studyGroupStack.isEmpty()) {
            StudyGroup studyGroup = studyGroupStack.pop();
            if (Objects.equals(studyGroup.getId(), key) && studyGroup.getOwner().equals(user.getUsername())) {
                databaseHandler.deleteStudyGroupsByKeyFromDataBase(key, user);
                message = "Элемент с id " + key + " удален!";
            } else {
                tempStack.push(studyGroup);
            }
        }
        while (!tempStack.isEmpty()) {
            studyGroupStack.push(tempStack.pop());
        }
        return message;
    }

    public List<Long> removeGreater(StudyGroup studyGroup) {
        List<Long> removedIds = new ArrayList<>();

        Collection<StudyGroup> collection = studyGroupStack.stream()
                .filter(setStudentsCount -> Double.compare(setStudentsCount.getStudentsCount(), studyGroup.getStudentsCount()) > 0)
                .collect(Collectors.toList());

        collection.forEach(sg -> {
            removedIds.add(sg.getId());
            studyGroupStack.remove(studyGroup);
        });
        return removedIds;
    }

    public String removeMax(User user, DatabaseHandler databaseHandler) {
        String message = "Коллекция пуста!";
        Stack<StudyGroup> tempStack = new Stack<>();
        StudyGroup maxStudyGroup = null;


        while (!studyGroupStack.isEmpty()) {
            StudyGroup studyGroup = studyGroupStack.pop();
            if (studyGroup.getOwner().equals(user.getUsername()) &&
                    (maxStudyGroup == null || studyGroup.getStudentsCount() > maxStudyGroup.getStudentsCount())) {
                if (maxStudyGroup != null) {
                    tempStack.push(maxStudyGroup);
                }
                maxStudyGroup = studyGroup;
            } else {
                tempStack.push(studyGroup);
            }
        }
        // Удалить элемент с максимальным значением studentsCount из базы данных
        if (maxStudyGroup != null) {
            databaseHandler.deleteStudyGroupsByKeyFromDataBase(maxStudyGroup.getId(), user);
            message = "Элемент с количеством студентов " + maxStudyGroup.getStudentsCount() + " удален!";
        }
        // Переносим остальные элементы обратно в исходный стек
        while (!tempStack.isEmpty()) {
            studyGroupStack.push(tempStack.pop());
        }
        return message;
    }

    public String removeGreaterThan(User user, DatabaseHandler databaseHandler, Long studentsCountThreshold) {
        String message = "Коллекция пуста!";
        Stack<StudyGroup> tempStack = new Stack<>();
        boolean removed = false;

        // Найти и удалить элементы с количеством студентов больше заданного значения
        while (!studyGroupStack.isEmpty()) {
            StudyGroup studyGroup = studyGroupStack.pop();
            if (studyGroup.getOwner().equals(user.getUsername()) &&
                    studyGroup.getStudentsCount() > studentsCountThreshold) {
                databaseHandler.deleteStudyGroupsByKeyFromDataBase(studyGroup.getId(), user);
                removed = true;
            } else {
                tempStack.push(studyGroup);
            }
        }
        // Переносим оставшиеся элементы обратно в исходный стек
        while (!tempStack.isEmpty()) {
            studyGroupStack.push(tempStack.pop());
        }
        if (removed) {
            message = "Элементы с количеством студентов больше " + studentsCountThreshold + " удалены!";
        }
        return message;
    }


}
