package validators;

import java.io.Serializable;

public class CollectionValidator implements Serializable {

    boolean isEmpty;

    public void setCollectionInfo(boolean isEmpty) {
        this.isEmpty = isEmpty;
    }

    public boolean getCollectionInfo() {
        return isEmpty;
    }

}
