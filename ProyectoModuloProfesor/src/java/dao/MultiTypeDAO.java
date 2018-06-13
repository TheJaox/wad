package dao;

import java.util.ArrayList;
import java.util.List;
import model.MultiType;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import util.HibernateUtil;

/**
 * DAO para la comunicacion con la tabla de MultiTypes
 * @author jair_
 */
public class MultiTypeDAO {
    
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
    
    public MultiType findMultiTypeById(Integer id) {
        MultiType multiType = new MultiType();
        try {
            startOperation();
            multiType = (MultiType) session.get(MultiType.class, id);
        } catch (HibernateException ex) {
            transaction.rollback();
            manageException(ex);
        } finally {
            closeSession();
        }
        return multiType;
    }
    
    public List<MultiType> findAllMultiType() {
        List<MultiType> multiTypes = new ArrayList<>();
        try {
            startOperation();
            multiTypes = this.session.createQuery("from MultiType").list();
            if(multiTypes.isEmpty()) {
                multiTypes = null;
            }
        } catch (HibernateException ex) {
            this.transaction.rollback();
            manageException(ex);
        } finally {
            closeSession();
        }
        return multiTypes;
    }
    
    
}
