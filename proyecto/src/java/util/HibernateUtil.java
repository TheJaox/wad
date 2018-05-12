package util;

import java.io.File;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.AnnotationConfiguration;

/**
 * Esta clase es la que configura Hibernate por primera vez, 
 * busca el archivo de confiuracion XML y nos da una SessionFactory,
 * se espera que esa  sesion sea la unica que usemos en todo el proyecto.
 * @author jair_
 */
public class HibernateUtil {

    private static SessionFactory factory;

    static {
        try {
            factory = new AnnotationConfiguration()
                    .configure(new File("src/java/util/hibernate.cfg.xml"))
                    .buildSessionFactory();
        } catch (HibernateException ex) {
            throw new ExceptionInInitializerError(ex);
        }
    }

    public static Session getSession() throws HibernateException {
        return factory.openSession();
    }

}
