package dao;

import java.util.ArrayList;
import java.util.List;
import model.Option;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import util.HibernateUtil;

/**
 * DAO para la comunicacion con la tabla de Usuarios
 *
 * @author jair_
 */
public class OptionDAO {

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

    public Integer insertOption(Option option) {
        Integer id = 0;
        try {
            startOperation();
            id = (Integer) session.save(option);
            transaction.commit();
        } catch (HibernateException ex) {
            transaction.rollback();
            manageException(ex);
            id = 0;
        } finally {
            closeSession();
        }
        return id;
    }

    public void updateOption(Option option) {
        try {
            startOperation();
            session.update(option);
            transaction.commit();
        } catch (HibernateException ex) {
            transaction.rollback();
            manageException(ex);
        } finally {
            closeSession();
        }
    }

    public int deleteOption(Option option) {
        int toReturn = -1;
        try {
            startOperation();
            System.out.println("Delete Option: " + option.getId());
            Query query = session.createQuery("delete Option where id=:idOption");
            query.setParameter("idOption", option.getId());
            int result = query.executeUpdate();
            if (result > 0) {
                System.out.println("Deleted");
                toReturn = 1;
            } else {
                System.out.println("Error");
            }
            transaction.commit();
        } catch (HibernateException ex) {
            transaction.rollback();
            manageException(ex);
        } finally {
            closeSession();
        }
        return toReturn;
    }

    public Option findOptionById(Integer id) {
        Option option = new Option();
        try {
            startOperation();
            option = (Option) session.get(Option.class, id);
        } catch (HibernateException ex) {
            transaction.rollback();
            manageException(ex);
        } finally {
            closeSession();
        }
        return option;
    }

    public List<Option> findAllOption() {
        List<Option> options = new ArrayList<>();
        try {
            startOperation();
            options = this.session.createQuery("from Option").list();
        } catch (HibernateException ex) {
            this.transaction.rollback();
            manageException(ex);
        } finally {
            closeSession();
        }
        return options;
    }

}
