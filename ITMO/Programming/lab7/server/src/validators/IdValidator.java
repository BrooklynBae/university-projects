package validators;

import managers.CollectionManager;
import model.StudyGroup;

import java.util.HashSet;
import java.util.Objects;

public class IdValidator {
    private static final HashSet<Long> idHashSet = new HashSet<>();
    static Long id = 0L;

    public static StudyGroup checkById(Long id, CollectionManager manager) {
        for (StudyGroup studyGroup : manager.getCollection()) {
            if (Objects.equals(studyGroup.getId(), id)) {
                return studyGroup;
            }
        }
        return null;
    }
    //TODO:сука айди чтоб с нуля начинался
    public static void clearHashSet() {
        idHashSet.clear();
    }

}
