package managers;

import client.Client;
import model.StudyGroup;

import java.io.*;
import java.util.*;

public class CSVManager implements Serializable {
    public Stack<StudyGroup> studyGroupStack = new Stack<>();
    String ENV_KEY = "LAB_5";
    String pathToDataFile = System.getenv(ENV_KEY);

    boolean collectionInfo = true;
    Client client;

    public boolean getCollectionInfo(boolean collectionInfo){
        return collectionInfo;
    }

    public void setCollectionInfo() {
        this.collectionInfo = collectionInfo;
    }
    public void addElementToCollection(StudyGroup studyGroup) {

        if (studyGroupStack == null) {
            studyGroupStack = new Stack<>();
            studyGroupStack.push(studyGroup);
        } else {
            studyGroupStack.push(studyGroup);
        }
    }

    public Stack<StudyGroup> getCollection() {
        return studyGroupStack;
    }
    public void clearCollection() {
        studyGroupStack.clear();
    }
}
