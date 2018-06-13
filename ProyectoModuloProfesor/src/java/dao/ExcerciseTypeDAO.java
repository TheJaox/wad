package dao;

import java.util.ArrayList;
import java.util.List;
import model.ExcerciseType;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import util.HibernateUtil;

/**
 * DAO para la comunicacion con la tabla de ExcerciseTypes
 * @author jair_
 */
public class ExcerciseTypeDAO {
    
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
    
    public ExcerciseType findExcerciseTypeById(Integer id) {
        ExcerciseType excerciseType = new ExcerciseType();
        try {
            startOperation();
            excerciseType = (ExcerciseType) session.get(ExcerciseType.class, id);
        } catch (HibernateException ex) {
            transaction.rollback();
            manageException(ex);
        } finally {
            closeSession();
        }
        return excerciseType;
    }
    
    public List<ExcerciseType> findAllExcerciseType() {
        List<ExcerciseType> excerciseTypes = new ArrayList<>();
        try {
            startOperation();
            excerciseTypes = this.session.createQuery("from ExcerciseType").list();
            if(excerciseTypes.isEmpty()) {
                excerciseTypes = null;
            }
        } catch (HibernateException ex) {
            this.transaction.rollback();
            manageException(ex);
        } finally {
            closeSession();
        }
        return excerciseTypes;
    }
    
    
}
