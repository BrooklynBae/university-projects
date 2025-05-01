import lombok.Getter;
import lombok.Setter;
import com.google.gson.Gson;

import javax.annotation.PostConstruct;
import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.stream.Collectors;

@ManagedBean(name = "table")
@ApplicationScoped
public class DataBaseBean {
    private static final long serialVersionUID = 1L;
    private static final Logger logger = Logger.getLogger(DataBaseBean.class.getName());
    private final Lock lock = new ReentrantLock();

    private final String URL = "jdbc:postgresql://localhost:8888/studs";
    private final String dataBasePassword = "s412958";
    private final String dataBaseUsername = "vFLr*8357";
    private static Connection connection;
    @Getter
    @Setter
    private List<PointBean> results;

    @PostConstruct
    public void init() {
        try {
            connection = DriverManager.getConnection(URL, dataBaseUsername, dataBasePassword);
            logger.log(Level.INFO, "Connection succeed");
            createTable();
            getDataFromTable();
        } catch (SQLException e) {
            logger.log(Level.INFO, e.getLocalizedMessage());
            System.exit(-1);
        }
    }

    private void createTable() throws SQLException {
        Statement statement = connection.createStatement();
        statement.execute("CREATE TABLE IF NOT EXISTS HITS"
                + "(X DOUBLE PRECISION, "
                + "Y DOUBLE PRECISION, "
                + "R DOUBLE PRECISION,"
                + "HIT BOOLEAN, "
                + "ATTEMPT_TIME BIGINT,"
                + "EXECUTION_TIME DOUBLE PRECISION);");
        logger.log(Level.INFO, "Table created");
    }

    private void getDataFromTable() throws SQLException {
        results = new ArrayList<>();
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery("SELECT * FROM HITS");
        while (resultSet.next()) {
            try {
                lock.lock();
                results.add(new PointBean(resultSet.getDouble("X"), resultSet.getDouble("Y"),
                        resultSet.getDouble("R"), resultSet.getBoolean("HIT"), resultSet.getLong("ATTEMPT_TIME"),
                        resultSet.getLong("EXECUTION_TIME")));
            } finally {
                lock.unlock();
            }
        }
        logger.log(Level.INFO, "Results got successfully ");
    }

    public void addPoint(PointBean point) throws SQLException {
        try {
            PreparedStatement statement = connection.prepareStatement("INSERT INTO HITS "
                    + "(X, Y, R, HIT, ATTEMPT_TIME, EXECUTION_TIME) VALUES (?, ?, ?, ?, ?, ?)");
            statement.setDouble(1, point.getX());
            statement.setDouble(2, point.getY());
            statement.setDouble(3, point.getR());
            statement.setBoolean(4, point.isHit());
            statement.setLong(5, point.getAttemptTime());
            statement.setDouble(6, point.getExecutionTime());
            statement.execute();
            try {
                lock.lock();
                results.add(point);
            } finally {
                lock.unlock();
            }
            logger.log(Level.INFO, "Point has been added to the database");
        } catch (SQLException e) {
            logger.log(Level.WARNING, e.getLocalizedMessage());
        }
    }

    public void clear() {
        try {
            Statement statement = connection.createStatement();
            statement.execute("TRUNCATE TABLE HITS");
            try {
                lock.lock();
                results.clear();
            } finally {
                lock.unlock();
            }
            logger.log(Level.INFO, "Table cleared successfully");
        } catch (SQLException e) {
            logger.log(Level.WARNING, e.getLocalizedMessage());
        }
    }

    public String getX() {
        return new Gson().toJson(getResults().stream().map(PointBean::getX).collect(Collectors.toList()));
    }

    public String getY() {
        return new Gson().toJson(getResults().stream().map(PointBean::getY).collect(Collectors.toList()));
    }

    public String getR() {
        return new Gson().toJson(getResults().stream().map(PointBean::getR).collect(Collectors.toList()));
    }

    public String getHit() {
        return new Gson().toJson(getResults().stream().map(PointBean::isHit).collect(Collectors.toList()));
    }
}
//TODO: понять: @ManagedBean: Аннотация указывает, что данный класс является управляемым бином в JavaServer Faces (JSF). Он доступен в рамках JSF-приложения под именем table.
//@ApplicationScoped: Бин создаётся один раз для всего приложения и используется всеми пользователями (общий для всех сессий).
//Использует PreparedStatement для вставки значений (безопасно от SQL-инъекций).