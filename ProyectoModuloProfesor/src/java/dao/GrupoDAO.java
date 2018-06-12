package dao;

import java.util.ArrayList;
import java.util.List;
import model.Grupo;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import util.HibernateUtil;

/**
 * DAO para la comunicacion con la tabla de Grupos
 *
 * @author jair_
 */
public class GrupoDAO {

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

    public Integer insertGrupo(Grupo grupo) {
        Integer id = 0;
        try {
            startOperation();
            id = (Integer) session.save(grupo);
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

    public void updateGrupo(Grupo grupo) {
        try {
            startOperation();
            session.update(grupo);
            transaction.commit();
        } catch (HibernateException ex) {
            transaction.rollback();
            manageException(ex);
        } finally {
            closeSession();
        }
    }

    public int deleteGrupo(Grupo grupo) {
        int toReturn = -1;
        try {
            startOperation();
            System.out.println("Delete Grupo: " + grupo.getId());
            Query query = session.createQuery("delete Grupo where id=:idGrupo");
            query.setParameter("idGrupo", grupo.getId());
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

    public Grupo findGrupoById(Integer id) {
        Grupo grupo = new Grupo();
        try {
            startOperation();
            grupo = (Grupo) session.get(Grupo.class, id);
        } catch (HibernateException ex) {
            transaction.rollback();
            manageException(ex);
        } finally {
            closeSession();
        }
        return grupo;
    }

    public List<Grupo> findAllGrupo() {
        List<Grupo> grupos = new ArrayList<>();
        try {
            startOperation();
            Query query = this.session.createQuery("from Grupo");
            if(query.list() != null) {
                grupos = query.list();
            }
        } catch (HibernateException ex) {
            this.transaction.rollback();
            manageException(ex);
        } finally {
            closeSession();
        }
        return grupos;
    }

}
