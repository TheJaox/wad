package mb;

import model.UserPrueba;
import org.hibernate.Session;
import util.HibernateUtil;

/**
 *
 * @author jair_
 */
public class test {
    
    /**
     * Este metodo de prueba solo nos trae una sesion de Hibernate, 
     * hacemos un nuevo usuario como objeto y despues lo isertamos en la bd.
     * @param args 
     */
    public static void main(String[] args) {
        //Traemos la sesion de nuestra clase HbernateUtil
        Session session = HibernateUtil.getSessionFactory().openSession();
        //Empezamos la transaccion
        session.beginTransaction();
        
        //Creamos un nuevo usuario solo como objeto
        UserPrueba userPrueba = new UserPrueba();
        userPrueba.setUserName("user");
        userPrueba.setPass("pass");
        
        //Persisitimos en la BD
        session.save(userPrueba);
        //Hacemos comit a la transaccion para hacer los cambios
        session.getTransaction().commit();
        
        //Imprimimos el id que se le asigno
        System.out.println("user>>>>>>>"+userPrueba.getId());
    }
}
