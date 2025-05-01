package database;

import enums.Color;
import enums.Country;
import enums.Semester;
import managers.CollectionManager;
import model.Coordinates;
import model.Person;
import model.StudyGroup;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.sql.*;
import java.util.NoSuchElementException;
import java.util.Scanner;
import java.util.Stack;

public class DatabaseHandler {

    private String URL = "jdbc:postgresql://localhost:7777/studs";
    //private String URL = "jdbc:postgresql://pg:5432/studs";
    private String dataBasePassword;
    private String dataBaseUsername;
    private Boolean signUpFlag = false;
    private Boolean loginFlag = false;
    private static Connection connection;
    private Scanner data;
    CollectionManager collectionManager = new CollectionManager();

    public Boolean getLoginFlag() {
        return loginFlag;
    }

    public void setLoginFlag(Boolean loginFlag) {
        this.loginFlag = loginFlag;
    }

    public Boolean getSignUpFlag() {
        return signUpFlag;
    }

    public void setSignUpFlag(Boolean signUpFlag) {
        this.signUpFlag = signUpFlag;
    }

    public void connectToDatabase() {
        try {
            data = new Scanner(new FileReader("file.csv"));
        } catch (FileNotFoundException e) {
            System.out.println("Файл с данными для входа в базу данных не найден.");
        }
        try {
            dataBaseUsername = data.nextLine().trim();
            dataBasePassword = data.nextLine().trim();
        } catch (NoSuchElementException e) {
            System.out.println("Не найдены данные для подключечния к базе данных, завершение программы...");
            System.exit(-2);
        }
        try {
            connection = DriverManager.getConnection(URL, dataBaseUsername, dataBasePassword);
            System.out.println("Подключение к базе данных установленно.");
        } catch (SQLException e) {
            System.err.println("Не удалось выполнить подключение к базе данных. Завершение работы...");
            System.out.println(e.getLocalizedMessage());
            //throw new RuntimeException(e);
            System.exit(-1);
        }

    }

    public Stack<StudyGroup> loadCollectionFromDB() throws SQLException {
        Stack<StudyGroup> collection = new Stack<>();
        try {
            PreparedStatement getCollection = connection.prepareStatement(Const.SELECT_ALL_FROM_SG_REQUEST);
            ResultSet result = getCollection.executeQuery();

            while (result.next()) {
                try {
                    StudyGroup sg = extractStudyGroupFromResult(result);
                    collection.push(sg); //TODO: check id unic
                } catch (Exception e) {
                    System.out.println("Поломанный объект");
                }
            }
            getCollection.close();
            System.out.println("Коллекция успешно загружена из базы данных. Количество элементов: " + collection.size());
        } catch (SQLException e) {
            System.out.println("Произошла ошибка при загрузке коллекции из базы данных. Завершение работы.");
            e.printStackTrace();
            System.exit(0);
        }
        return collection;
    }

    private StudyGroup extractStudyGroupFromResult(ResultSet result) throws Exception {
        String owner = result.getString(Const.SG_OWNER);
        long groupId = result.getInt(Const.SG_ID);
        if (groupId < 0) throw new Exception();

        String groupName = result.getString(Const.SG_NAME);
        if (groupName == null || groupName.isEmpty()) throw new Exception();
        Long x = result.getLong(Const.SG_COORDINATE_X);
        if (result.wasNull()) x = null;
        double y = result.getDouble(Const.SG_COORDINATE_Y);
        Date date = result.getDate(Const.SG_DATE);
        Long studentsCount = result.getLong(Const.SG_STUDENTS_COUNT);
        if (result.wasNull()) studentsCount = null;
        int shouldByExpelled = result.getInt(Const.SG_SHOULD_BE_EXPELLED);
        float averageMark = result.getFloat(Const.SG_AVERAGE_MARK);
        String semesterString = result.getString(Const.SG_SEMESTER);
        Semester semester = null;
        if (semesterString != null && !result.wasNull()) {
            semester = Semester.valueOf(semesterString);
        }

        String personName = result.getString(Const.SG_PERSON_NAME);
        if (result.wasNull()) personName = null;

        Date birthday = result.getDate(Const.SG_PERSON_BIRTHDAY);
        if (result.wasNull()) birthday = null;

        String hairColorString = result.getString(Const.SG_PERSON_HAIR_COLOR);
        Color hairColor = null;
        if (hairColorString != null && !result.wasNull()) {
            hairColor = Color.valueOf(hairColorString);
        }

        String eyeColorString = result.getString(Const.SG_PERSON_EYE_COLOR);
        Color eyeColor = null;
        if (eyeColorString != null && !result.wasNull()) {
            eyeColor = Color.valueOf(eyeColorString);
        }

        String nationalityString = result.getString(Const.SG_PERSON_NATIONALITY);
        Country nationality = null;
        if (nationalityString != null && !result.wasNull()) {
            nationality = Country.valueOf(nationalityString);
        }
        Coordinates coordinates = new Coordinates(x, y);
        Person person = new Person(personName, birthday, eyeColor, hairColor, nationality);
        StudyGroup studyGroup = new StudyGroup(groupId, owner, groupName, coordinates, date, studentsCount, shouldByExpelled, averageMark, semester, person);

        return studyGroup;
    }

    public static boolean insertStudyGroup(StudyGroup sg) {
        Coordinates coordinates = sg.getCoordinates();
        java.sql.Date creationDate = (Date) sg.getCreationDate();
        long studentsCount = sg.getStudentsCount();
        int shouldBeExpelled = sg.getShouldBeExpelled();
        float averageMark = sg.getAverageMark();
        Semester semester = sg.getSemesterEnum();
        Person person = sg.getGroupAdmin();

        try {
            connection.setAutoCommit(false);
            connection.setSavepoint();

            PreparedStatement addToStudyGroupStatement = connection.prepareStatement(Const.ADD_SG_REQUEST);
            addToStudyGroupStatement.setString(1, sg.getOwner());
            addToStudyGroupStatement.setString(2, sg.getName());
            if (coordinates.getX() != null) {
                addToStudyGroupStatement.setLong(3, coordinates.getX());
            } else {
                addToStudyGroupStatement.setNull(3, Types.NULL);
            }
            addToStudyGroupStatement.setDouble(4, coordinates.getY());
            addToStudyGroupStatement.setDate(5, creationDate);
            if (sg.getStudentsCount() != null) {
                addToStudyGroupStatement.setLong(6, studentsCount);
            } else {
                addToStudyGroupStatement.setNull(6, Types.NULL);
            }
            addToStudyGroupStatement.setInt(7, shouldBeExpelled);
            addToStudyGroupStatement.setFloat(8, averageMark);
            if (semester != null) {
                addToStudyGroupStatement.setString(9, semester.toString());
            } else {
                addToStudyGroupStatement.setNull(9, Types.NULL);
            }
            if (person != null) {
                addToStudyGroupStatement.setString(10, person.getName());
                addToStudyGroupStatement.setDate(11, person.getBirthday());
                if (person.getEyeColor() != null) {
                    addToStudyGroupStatement.setString(12, person.getEyeColor().toString());
                } else {
                    addToStudyGroupStatement.setNull(12, Types.NULL);
                }
                addToStudyGroupStatement.setString(13, person.getHairColor().toString());
                if (person.getNationality() != null) {
                    addToStudyGroupStatement.setString(14, person.getNationality().toString());
                } else {
                    addToStudyGroupStatement.setNull(14, Types.NULL);
                }
            } else {
                addToStudyGroupStatement.setNull(10, Types.NULL);
                addToStudyGroupStatement.setNull(11, Types.NULL);
                addToStudyGroupStatement.setNull(12, Types.NULL);
                addToStudyGroupStatement.setNull(13, Types.NULL);
                addToStudyGroupStatement.setNull(14, Types.NULL);
            }

            addToStudyGroupStatement.executeUpdate();
            addToStudyGroupStatement.close();

            connection.commit();
            connection.setAutoCommit(true);

            return true;

        } catch (SQLException e) {
            e.printStackTrace();
            rollback();
        }
        return false;
    }


    public void signUpUser(User user) throws SQLException {
        if (userExists(user)) signUpFlag = false;
        else {
            PreparedStatement addStatement = connection.prepareStatement(Const.ADD_USER_REQUEST);
            addStatement.setString(1, user.getUsername());
            addStatement.setString(2, DataHasher.encryptString(user.getPassword()));
            addStatement.executeUpdate();
            addStatement.close();
            signUpFlag = true;
        }
    }

    public boolean userExistCheck(User user) throws SQLException {
        PreparedStatement findUserStatement = connection.prepareStatement(Const.FIND_USERNAME_REQUEST);
        findUserStatement.setString(1, user.getUsername());
        ResultSet result = findUserStatement.executeQuery();
        result.next();
        int count = result.getInt(1);
        findUserStatement.close();
        return count == 1;
    }


    public void loginUser(User user) throws SQLException {
        if (!userExistCheck(user)) loginFlag = false;
        else {
            PreparedStatement findStatement = connection.prepareStatement(Const.SELECT_USERNAME_PASSWORD_FROM_USERS_REQUEST);
            findStatement.setString(1, user.getUsername());
            ResultSet result = findStatement.executeQuery();
            result.next();
            String username = result.getString(Const.USER_USERNAME);
            String password = result.getString(Const.USER_PASSWORD);

            if (user.getUsername().equals(username) & DataHasher.encryptString(user.getPassword()).equals(password)) {
                loginFlag = true;
            }
        }
    }

    public boolean isOwnerOf(StudyGroup studyGroup) throws SQLException {
        PreparedStatement ownerStatement = connection.prepareStatement(Const.SELECT_SG_OWNER_REQUEST);
        ownerStatement.setLong(1, studyGroup.getId());
        ResultSet result = ownerStatement.executeQuery();
        result.next();
        String owner = result.getString(Const.SG_OWNER);
        ownerStatement.close();
        if (owner.equals(studyGroup.getOwner())) return true;
        return false;
    }

    public boolean userExists(User user) throws SQLException {
        PreparedStatement findStatement = connection.prepareStatement(Const.FIND_USERNAME_REQUEST);
        findStatement.setString(1, user.getUsername());
        ResultSet result = findStatement.executeQuery();
        result.next();
        int count = result.getInt(1);
        findStatement.close();
        if (count == 1) return true;
        return false;
    }

    public void deleteAllStudyGroupsFromDataBase(User user) {
        try {
            PreparedStatement addStatement = connection.prepareStatement(Const.DELETE_ALL_SG_REQUEST);
            addStatement.setString(1, user.getUsername());
            addStatement.executeUpdate();
            addStatement.close();
        } catch (SQLException e) {
            e.printStackTrace();
            rollback();
        }
    }

    public void deleteStudyGroupsByKeyFromDataBase(int key, User user) {
        try {
            PreparedStatement addStatement = connection.prepareStatement(Const.DELETE_SG_BY_KEY_REQUEST);
            addStatement.setInt(1, key);
            addStatement.setString(2, user.getUsername());
            addStatement.executeUpdate();
            addStatement.close();
        } catch (SQLException e) {
            e.printStackTrace();
            rollback();
        }
    }

    public void deleteStudyGroupsByKeyFromDataBase(Long key, User user) {
        try {
            PreparedStatement addStatement = connection.prepareStatement(Const.DELETE_SG_BY_ID_REQUEST);
            addStatement.setLong(1, key);
            addStatement.setString(2, user.getUsername());
            addStatement.executeUpdate();
            addStatement.close();
        } catch (SQLException e) {
            e.printStackTrace();
            rollback();
        }
    }

    public void resetSequence() {
        try {
            // Найти минимально доступное значение для id
            PreparedStatement statementMinId = connection.prepareStatement(Const.QUERY_MIN_VALUE);
            ResultSet resultSet = statementMinId.executeQuery();
            long minId = 1;
            if (resultSet.next()) {
                minId = resultSet.getLong("min_id");
            }
            statementMinId.close();

            // Установить значение последовательности
            PreparedStatement statementResetSeq = connection.prepareStatement(Const.RESET_SEQ);
            statementResetSeq.setLong(1, minId);
            statementResetSeq.executeUpdate();
            statementResetSeq.close();

        } catch (SQLException e) {
            e.printStackTrace();
            rollback();
        }
    }

        public static void rollback () {
            try {
                connection.rollback();
                connection.commit();
            } catch (SQLException e) {
                System.out.println("Не удалось откатить изменения.");
            }
        }
    }

