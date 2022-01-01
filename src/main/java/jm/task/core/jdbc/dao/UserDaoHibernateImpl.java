package jm.task.core.jdbc.dao;


import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.loader.custom.sql.SQLQueryParser;
import org.hibernate.query.Query;

import java.util.List;

public class UserDaoHibernateImpl implements UserDao {

    public UserDaoHibernateImpl() {

    }

    @Override
    public void createUsersTable() {
        try(Session session = Util.getSessionFactory().openSession()) {
            session.beginTransaction();
            session.createSQLQuery("CREATE TABLE IF NOT EXISTS users" +
                    "                    (id BIGINT PRIMARY KEY AUTO_INCREMENT," +
                    "                    name VARCHAR (100)," +
                    "                    lastName VARCHAR (100)," +
                    "                    age INT)")
                    .executeUpdate();
            session.getTransaction().commit();
        }
    }

    @Override
    public void dropUsersTable() {
        try(Session session = Util.getSessionFactory().openSession()) {
            session.beginTransaction();
            session.createSQLQuery("DROP TABLE IF EXISTS users").executeUpdate();
            session.getTransaction().commit();
        }
    }

    @Override
    public void saveUser(String name, String lastName, byte age) {
        try(Session session = Util.getSessionFactory().openSession()) {
            session.beginTransaction();
            SQLQuery insertQuery =  session.createSQLQuery("INSERT INTO users (name, lastName, age) VALUES (?, ?, ?)");
            insertQuery.setParameter(1, name);
            insertQuery.setParameter(2, lastName);
            insertQuery.setParameter(3, age);
            insertQuery.executeUpdate();
            session.getTransaction().commit();
        }

    }

    @Override
    public void removeUserById(long id) {
        try(Session session = Util.getSessionFactory().openSession()) {
        session.beginTransaction();

        User user = session.get(User.class, id);
        if (user != null) {
            SQLQuery removeQuery = session.createSQLQuery("DELETE FROM users WHERE id = ?");
            removeQuery.setParameter(1, id);
            removeQuery.executeUpdate();
        }
        session.getTransaction().commit();
        }
    }

    @Override
    public List<User> getAllUsers() {
        try(Session session = Util.getSessionFactory().openSession()) {
            return session.createQuery("from User", User.class).list();
        }
    }

    @Override
    public void cleanUsersTable() {
        try(Session session = Util.getSessionFactory().openSession()) {
            session.beginTransaction();
            session.createSQLQuery("DELETE FROM users").executeUpdate();
            session.getTransaction().commit();
        }

    }
}
