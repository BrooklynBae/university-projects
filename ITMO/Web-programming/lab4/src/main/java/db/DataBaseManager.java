package db;

import entities.Point;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import java.util.List;

public class DataBaseManager {
    private static SessionFactory sessionFactory;
    private static List<Point> requests;

    static {
        try {
            sessionFactory = new Configuration().configure("hibernate.cfg.xml").buildSessionFactory();
        } catch (Throwable ex) {
            System.err.println("Инициализация SessionFactory завершилась неудачей: " + ex);
            throw new RuntimeException(ex);
        }
    }

    public static List<Point> getRequestsByOwner(String owner) {
        List<Point> results = null;
        try (Session session = sessionFactory.openSession()) {
            results = session.createQuery("FROM Point WHERE owner = :owner", Point.class)
                    .setParameter("owner", owner)
                    .getResultList();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return results;
    }

    public static void deleteAllRequestsByOwner(String owner) {
        Transaction transaction = null;
        try (Session session = sessionFactory.openSession()) {
            transaction = session.beginTransaction();

            session.createQuery("DELETE FROM Point WHERE owner = :owner")
                    .setParameter("owner", owner)
                    .executeUpdate();

            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }

    public static void deleteRequestByIdAndOwner(Long id, String owner) {
        Transaction transaction = null;
        try (Session session = sessionFactory.openSession()) {
            transaction = session.beginTransaction();

            session.createQuery("DELETE FROM Point WHERE id = :id AND owner = :owner")
                    .setParameter("id", id)
                    .setParameter("owner", owner)
                    .executeUpdate();

            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }

    public static List<Point> getRequests() {
        return requests;
    }

    public static void setRequests(List<Point> requests) {
        DataBaseManager.requests = requests;
    }

    public static boolean doesUserExist(String login) {
        try (Session session = sessionFactory.openSession()) {
            String hql = "SELECT COUNT(u) FROM User u WHERE u.login = :login";
            Long count = session.createQuery(hql, Long.class)
                    .setParameter("login", login)
                    .uniqueResult();
            return count != null && count > 0;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public static String getPasswordByLogin(String login) {
        try (Session session = sessionFactory.openSession()) {
            String hql = "SELECT u.password FROM User u WHERE u.login = :login";
            return session.createQuery(hql, String.class)
                    .setParameter("login", login)
                    .uniqueResult();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public static void addData(Object o) {
        Transaction transaction = null;
        Session session = null;
        try {
            session = sessionFactory.openSession();
            transaction = session.beginTransaction();
            session.save(o);
            session.flush();
            System.out.println("DEBUG: Объект сохранен в сессии - " + o);
            transaction.commit();
            System.out.println("DEBUG: Транзакция успешно закоммичена");
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
                System.out.println("DEBUG: Транзакция была откатилась из-за ошибки");
            }
            e.printStackTrace();
        } finally {
            if (session != null) {
                session.close();
                System.out.println("DEBUG: Сессия закрыта");
            }
        }
    }


}