package dao;

import java.util.ArrayList;
import java.util.List;
import model.Excercise;
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
public class ExcerciseDAO {

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

    public Integer insertExcercise(Excercise excercise) {
        Integer id = 0;
        try {
            startOperation();
            id = (Integer) session.save(excercise);
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

    public void updateExcercise(Excercise excercise) {
        try {
            startOperation();
            session.update(excercise);
            transaction.commit();
        } catch (HibernateException ex) {
            transaction.rollback();
            manageException(ex);
        } finally {
            closeSession();
        }
    }

    public int deleteExcercise(Excercise excercise) {
        int toReturn = -1;
        try {
            startOperation();
            System.out.println("Delete Excercise: " + excercise.getId());
            Query query = session.createQuery("delete Excercise where id=:idExcercise");
            query.setParameter("idExcercise", excercise.getId());
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

    public Excercise findExcerciseById(Integer id) {
        Excercise excercise = new Excercise();
        try {
            startOperation();
            excercise = (Excercise) session.get(Excercise.class, id);
        } catch (HibernateException ex) {
            transaction.rollback();
            manageException(ex);
        } finally {
            closeSession();
        }
        return excercise;
    }

    public List<Excercise> findAllExcercise() {
        List<Excercise> excercises = new ArrayList<>();
        try {
            startOperation();
            excercises = this.session.createQuery("from Excercise").list();
        } catch (HibernateException ex) {
            this.transaction.rollback();
            manageException(ex);
        } finally {
            closeSession();
        }
        return excercises;
    }

}
