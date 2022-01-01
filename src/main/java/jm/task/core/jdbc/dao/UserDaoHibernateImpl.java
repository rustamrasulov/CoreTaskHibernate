package jm.task.core.jdbc.dao;


import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

public class UserDaoHibernateImpl implements UserDao {
    private String userTable = "users";
    public UserDaoHibernateImpl() {

    }

    @Override
    public void createUsersTable() {
        try(Session session = Util.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            session.createSQLQuery("CREATE TABLE IF NOT EXISTS " + userTable +
                    "                    (id BIGINT PRIMARY KEY AUTO_INCREMENT," +
                    "                    name VARCHAR (100)," +
                    "                    lastName VARCHAR (100)," +
                    "                    age INT)")
                    .executeUpdate();
            transaction.commit();
        }
    }

    @Override
    public void dropUsersTable() {
        try(Session session = Util.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            session.createSQLQuery("DROP TABLE IF EXISTS " + userTable).executeUpdate();
            transaction.commit();
        }
    }

    @Override
    public void saveUser(String name, String lastName, byte age) {
        try(Session session = Util.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            System.out.println("Попытка сохранения юзера " + name);
            session.save(new User(name, lastName, age));
            transaction.commit();
        }
    }

    @Override
    public void removeUserById(long id) {
        try(Session session = Util.getSessionFactory().openSession()) {
        Transaction transaction = session.beginTransaction();
        session.delete(id);
        transaction.commit();
        }
    }

    @Override
    public List<User> getAllUsers() {
        List<User> users = (List<User>) Util.getSessionFactory().openSession().createQuery("From User").getResultList();
        return users;
    }

    @Override
    public void cleanUsersTable() {
        try(Session session = Util.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            session.delete("FROM User");
            transaction.commit();
        }

    }
}
