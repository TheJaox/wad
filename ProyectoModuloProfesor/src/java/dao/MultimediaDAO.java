package dao;

import java.util.ArrayList;
import java.util.List;
import model.Multimedia;
import model.User;
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
public class MultimediaDAO {

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

    public Integer insertMultimedia(Multimedia multimedia) {
        Integer id = 0;
        try {
            startOperation();
            id = (Integer) session.save(multimedia);
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

    public void updateMultimedia(Multimedia multimedia) {
        try {
            startOperation();
            session.update(multimedia);
            transaction.commit();
        } catch (HibernateException ex) {
            transaction.rollback();
            manageException(ex);
        } finally {
            closeSession();
        }
    }

    public int deleteMultimedia(Multimedia multimedia) {
        int toReturn = -1;
        try {
            startOperation();
            System.out.println("Delete Multimedia: " + multimedia.getId());
            Query query = session.createQuery("delete Multimedia where id=:idMultimedia");
            query.setParameter("idMultimedia", multimedia.getId());
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

    public Multimedia findMultimediaById(Integer id) {
        Multimedia multimedia = new Multimedia();
        try {
            startOperation();
            multimedia = (Multimedia) session.get(Multimedia.class, id);
        } catch (HibernateException ex) {
            transaction.rollback();
            manageException(ex);
        } finally {
            closeSession();
        }
        return multimedia;
    }

    public List<Multimedia> findAllMultimedia() {
        List<Multimedia> multimedias = new ArrayList<>();
        try {
            startOperation();
            multimedias = this.session.createQuery("from Multimedia").list();
        } catch (HibernateException ex) {
            this.transaction.rollback();
            manageException(ex);
        } finally {
            closeSession();
        }
        return multimedias;
    }

    public List<Multimedia> findAllMultimediaByUser(User user) {
        List<Multimedia> multimedias = new ArrayList<>();
        try {
            startOperation();
            Query query = this.session.createQuery("from Multimedia m where m.user.id=:idUser");
            query.setParameter("idUser", user.getId());
            multimedias = query.list();
        } catch (HibernateException ex) {
            this.transaction.rollback();
            manageException(ex);
        } finally {
            closeSession();
        }
        return multimedias;
    }

}
