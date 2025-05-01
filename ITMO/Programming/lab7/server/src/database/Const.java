package database;

public class Const {
    public static final String USER_TABLE = "users";
    public static final String USER_ID = "id";
    public static final String USER_USERNAME = "username";
    public static final String USER_PASSWORD = "password";

    public static final String SG_TABLE = "study_groups";
    public static final String SG_OWNER = "owner";
    public static final String SG_ID = "sg_id";
    public static final String SG_NAME = "sg_name";
    public static final String SG_COORDINATE_X = "sg_coordinate_x";
    public static final String SG_COORDINATE_Y = "sg_coordinate_y";
    public static final String SG_DATE = "sg_date";
    public static final String SG_STUDENTS_COUNT = "sg_students_count";
    public static final String SG_SHOULD_BE_EXPELLED = "sg_should_be_expelled";
    public static final String SG_AVERAGE_MARK = "sg_average_mark";
    public static final String SG_SEMESTER = "sg_semester";
    public static final String SG_PERSON_NAME = "sg_person_name";
    public static final String SG_PERSON_BIRTHDAY = "sg_person_birthday";
    public static final String SG_PERSON_EYE_COLOR = "sg_person_eye_color";
    public static final String SG_PERSON_HAIR_COLOR = "sg_person_hair_color";
    public static final String SG_PERSON_NATIONALITY = "sg_person_nationality";

    public static final String ADD_USER_REQUEST = "INSERT INTO " + USER_TABLE + "(" +
            USER_USERNAME + "," + USER_PASSWORD + ") VALUES (?, ?)";
    public static final String FIND_USERNAME_REQUEST = "SELECT COUNT(*) AS count FROM " +
            USER_TABLE + " WHERE " + USER_USERNAME + " = ?";
    public static final String SELECT_USERNAME_PASSWORD_FROM_USERS_REQUEST = "SELECT " + USER_USERNAME + ", " +
            USER_PASSWORD + " FROM " + USER_TABLE + " WHERE " + USER_USERNAME + " = ?";
    public static final String SELECT_ALL_FROM_SG_REQUEST = "SELECT * FROM " + SG_TABLE;
//    public static final String ADD_SG_REQUEST = "INSERT INTO " + SG_TABLE + " (" +
//        SG_ID + "," + SG_OWNER + "," + SG_NAME + "," + SG_COORDINATE_X + "," + SG_COORDINATE_Y + "," +
//        SG_DATE + "," + SG_STUDENTS_COUNT + "," + SG_SHOULD_BE_EXPELLED + "," + SG_AVERAGE_MARK + "," +
//        SG_SEMESTER + "," + SG_PERSON_NAME + "," + SG_PERSON_BIRTHDAY + "," + SG_PERSON_EYE_COLOR + "," +
//        SG_PERSON_HAIR_COLOR + "," + SG_PERSON_NATIONALITY + ") VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";

    public static final String ADD_SG_REQUEST = "INSERT INTO " + SG_TABLE + " (" +
            SG_ID + "," + SG_OWNER + "," + SG_NAME + "," + SG_COORDINATE_X + "," + SG_COORDINATE_Y + "," +
            SG_DATE + "," + SG_STUDENTS_COUNT + "," + SG_SHOULD_BE_EXPELLED + "," + SG_AVERAGE_MARK + "," +
            SG_SEMESTER + "," + SG_PERSON_NAME + "," + SG_PERSON_BIRTHDAY + "," + SG_PERSON_EYE_COLOR + "," +
            SG_PERSON_HAIR_COLOR + "," + SG_PERSON_NATIONALITY + ") VALUES (nextval('study_group_id_seq'),?,?,?,?,?,?,?,?,?,?,?,?,?,?)";

    public static final String UPDATE_SG_REQUEST = "UPDATE " + SG_TABLE + " SET " +
            SG_NAME + " = ?," + SG_COORDINATE_X + " = ?," + SG_COORDINATE_Y + " = ?," +
            SG_DATE + " = ?," + SG_STUDENTS_COUNT + " = ?," +
            SG_SHOULD_BE_EXPELLED + " = ?," + SG_AVERAGE_MARK + " = ?," +
            SG_SEMESTER + " = ?," + SG_PERSON_NAME + " = ?," +
            SG_PERSON_BIRTHDAY + " = ?," + SG_PERSON_EYE_COLOR + " = ?," +
            SG_PERSON_HAIR_COLOR + " = ?" + SG_PERSON_NATIONALITY + " = ? WHERE " + SG_ID + " = ?";
    public static final String SELECT_SG_OWNER_REQUEST = "SELECT " +
            SG_OWNER + " FROM " + SG_TABLE + " WHERE " + SG_ID + " = ?";
    public static final String DELETE_ALL_SG_REQUEST = "DELETE FROM " + SG_TABLE + " WHERE " +
            SG_OWNER + " = ?";
    public static final String DELETE_SG_BY_KEY_REQUEST = "DELETE FROM " + SG_TABLE + " WHERE " +
            SG_SHOULD_BE_EXPELLED + " = ? AND " + SG_OWNER + " = ?";
    public static final String DELETE_SG_BY_ID_REQUEST = "DELETE FROM " + SG_TABLE + " WHERE " +
            SG_ID + " = ? AND " + SG_OWNER + " = ?";
    public static final String QUERY_MIN_VALUE = "SELECT COALESCE(MIN(t1.sg_id)+1, 1) AS min_id " +
            "FROM study_groups t1 LEFT JOIN study_groups t2 " +
            "ON t1.sg_id + 1 = t2.sg_id " +
            "WHERE t2.sg_id IS NULL";
    public static final String RESET_SEQ = "SELECT setval('study_group_id_seq', ?, false)";
}
