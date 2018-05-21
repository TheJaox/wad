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
        this.session = HibernateUtil.getSessionFactory().openSession();
        this.transaction = session.beginTransaction();
    }

    private void closeSession() {
        if (session != null) {
            session.close();
        }
    }

    private void manageException(HibernateException ex) {
        System.out.println(">>>>>>>>>>>Ex: " + ex);
    }

    public int insertUserPrueba(UserPrueba userPrueba) {
        int id = 0;
        try {
            startOperation();
            id = (int) session.save(userPrueba);
            transaction.commit();
        } catch (HibernateException ex) {
            transaction.rollback();
            manageException(ex);
        } finally {
            closeSession();
        }
        return id;
    }

    public void updateUserPrueba(UserPrueba userPrueba) {
        try {
            startOperation();
            session.update(userPrueba);
            transaction.commit();
        } catch (HibernateException ex) {
            transaction.rollback();
            manageException(ex);
        } finally {
            closeSession();
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
            closeSession();
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
            closeSession();
        }
        return userPrueba;
    }

    public List<UserPrueba> findAllUserPueba() {
        List<UserPrueba> usersPrueba = new ArrayList<>();
        try {
            startOperation();
            usersPrueba = this.session.createQuery("from UserPrueba").list();
        } catch (HibernateException ex) {
            this.transaction.rollback();
            manageException(ex);
        } finally {
            closeSession();
        }
        return usersPrueba;
    }
}
