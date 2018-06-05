package dao;

import java.util.ArrayList;
import java.util.List;
import model.User;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import util.HibernateUtil;

/**
 * DAO para la comunicacion con la tabla de Usuarios
 * @author jair_
 */
public class UserDAO {
    
    private Session session;

    private Transaction transaction;

    private void startOperation() {
        this.session = HibernateUtil.getSessionFactory().openSession();
        this.transaction = session.beginTransaction();
    }

    private void closeSession() {
        if (session != null) {
            session.close();
        }
    }

    private void manageException(HibernateException ex) {
        System.out.println(">>>>>>>>>>>Ex: " + ex.getMessage());
    }

    public Integer insertUser(User user) {
        Integer id = 0;
        try {
            startOperation();
            id = (Integer) session.save(user);
            transaction.commit();
        } catch (HibernateException ex) {
            transaction.rollback();
            manageException(ex);
        } finally {
            closeSession();
        }
        return id;
    }

    public void updateUser(User user) {
        try {
            startOperation();
            session.update(user);
            transaction.commit();
        } catch (HibernateException ex) {
            transaction.rollback();
            manageException(ex);
        } finally {
            closeSession();
        }
    }

    public void deleteUser(User user) {
        try {
            startOperation();
            session.delete(user);
            transaction.commit();
        } catch (HibernateException ex) {
            transaction.rollback();
            manageException(ex);
        } finally {
            closeSession();
        }
    }

    public User findUserById(Integer id) {
        User user = new User();
        try {
            startOperation();
            user = (User) session.get(User.class, id);
        } catch (HibernateException ex) {
            transaction.rollback();
            manageException(ex);
        } finally {
            closeSession();
        }
        return user;
    }

    public User findUserByName(String userName) {
        User user = null;
        try {
            startOperation();
            Query query = session.createQuery("FROM User u WHERE u.name = :userName");
            query.setParameter("userName", userName);
            if(query.list().size() > 0) {
                user = (User) query.list().get(0);
            }
        } catch (HibernateException ex) {
            transaction.rollback();
            manageException(ex);
        } finally {
            closeSession();
        }
        return user;
    }
    
    public List<User> findAllUser() {
        List<User> user = new ArrayList<>();
        try {
            startOperation();
            user = this.session.createQuery("from User").list();
        } catch (HibernateException ex) {
            this.transaction.rollback();
            manageException(ex);
        } finally {
            closeSession();
        }
        return user;
    }
    
    
}
