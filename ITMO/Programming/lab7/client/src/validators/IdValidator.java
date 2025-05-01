package validators;

import java.util.HashSet;

public class IdValidator {
    private static final HashSet<Long> idHashSet = new HashSet<>();
    static Long id;

    public static Long generateId() {
        id = 0L;
        while (idHashSet.contains(id)) {
            id ++;
        }
        idHashSet.add(id);
        return id;
    }

    public static void clearHashSet() {
        idHashSet.clear();
    }

    public static void removeElement(Long id) {
        idHashSet.remove(id);
    }

    public static Long validateInputId(String input) {
        try {
            id = Long.valueOf(input);
            return id;
        } catch (NumberFormatException e) {
            System.out.println("Некорректный ввод! Попробуйте снова");
        }
        return null;
    }
}
