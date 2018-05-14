package dao;

import java.util.ArrayList;
import java.util.List;
import model.UserPrueba;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import util.HibernateUtil;

/**
 *
 * @author jair_
 */
public class crudDAO {

    private Session session;

    private Transaction transaction;

    private void startOperation() {
        session = HibernateUtil.getSessionFactory().openSession();
        transaction = session.beginTransaction();
    }

    private void manageException(HibernateException ex) {
        System.out.println("Ex: " + ex);
    }

    public Long insertUserPrueba(UserPrueba userPrueba) {
        Long id = 0L;
        try {
            startOperation();
            id = (Long) session.save(userPrueba);
            transaction.commit();
        } catch (HibernateException ex) {
            transaction.rollback();
            manageException(ex);
        } finally {
            session.close();
        }
        return id;
    }

    public void updateUserPrueba(UserPrueba userPrueba) {
        try {
            startOperation();
            session.update(this);
            transaction.commit();
        } catch (HibernateException ex) {
            transaction.rollback();
            manageException(ex);
        } finally {
            session.close();
        }
    }

    public void deleteUserPrueba(UserPrueba userPrueba) {
        try {
            startOperation();
            session.delete(userPrueba);
            transaction.commit();
        } catch (HibernateException ex) {
            transaction.rollback();
            manageException(ex);
        } finally {
            session.close();
        }
    }

    public UserPrueba findUserPruebaById(int id) {
        UserPrueba userPrueba = new UserPrueba();
        try {
            startOperation();
            userPrueba = (UserPrueba) session.get(UserPrueba.class, id);
        } catch (HibernateException ex) {
            transaction.rollback();
            manageException(ex);
        } finally {
            session.close();
        }
        return userPrueba;
    }

    public List<UserPrueba> findAllUserPueba() {
        List<UserPrueba> usersPrueba = new ArrayList<>();
        try {
            startOperation();
            usersPrueba = session.createQuery("FROM users").list();
        } catch (HibernateException ex) {
            transaction.rollback();
            manageException(ex);
        } finally {
            session.close();
        }
        return usersPrueba;
    }

}
