package dao;

import java.util.ArrayList;
import java.util.List;
import model.UserType;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import util.HibernateUtil;

/**
 * DAO para la comunicacion con la tabla de Usuarios
 * @author jair_
 */
public class UserTypeDAO {
    
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

    public Integer insertUserType(UserType userType) {
        Integer id = 0;
        try {
            startOperation();
            id = (Integer) session.save(userType);
            transaction.commit();
        } catch (HibernateException ex) {
            transaction.rollback();
            manageException(ex);
        } finally {
            closeSession();
        }
        return id;
    }

    public void updateUserType(UserType userType) {
        try {
            startOperation();
            session.update(userType);
            transaction.commit();
        } catch (HibernateException ex) {
            transaction.rollback();
            manageException(ex);
        } finally {
            closeSession();
        }
    }

    public void deleteUserType(UserType userType) {
        try {
            startOperation();
            session.delete(userType);
            transaction.commit();
        } catch (HibernateException ex) {
            transaction.rollback();
            manageException(ex);
        } finally {
            closeSession();
        }
    }

    public UserType findUserTypeById(Integer id) {
        UserType userType = new UserType();
        try {
            startOperation();
            userType = (UserType) session.get(UserType.class, id);
        } catch (HibernateException ex) {
            transaction.rollback();
            manageException(ex);
        } finally {
            closeSession();
        }
        return userType;
    }
    
    public List<UserType> findAllUserType() {
        List<UserType> userType = new ArrayList<>();
        try {
            startOperation();
            userType = this.session.createQuery("from UserType").list();
            if(userType.isEmpty()) {
                userType = null;
            }
        } catch (HibernateException ex) {
            this.transaction.rollback();
            manageException(ex);
        } finally {
            closeSession();
        }
        return userType;
    }
    
    
}
