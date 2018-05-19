package mb;

import dao.crudDAO;
import java.util.List;
import model.UserPrueba;

/**
 *
 * @author jair_
 */
public class test {
    
    private static crudDAO dao;
    
    /**
     * Este metodo de prueba solo nos trae una sesion de Hibernate, 
     * hacemos un nuevo usuario como objeto y despues lo isertamos en la bd.
     * @param args 
     */
    public static void main(String[] args) {
        
        
        //Creamos un nuevo usuario solo como objeto
        UserPrueba userPrueba = new UserPrueba();
        userPrueba.setUserName("user");
        userPrueba.setPass("pass");
        dao = new crudDAO();
        List<UserPrueba> list = dao.findAllUserPueba();
        for(UserPrueba iter: list) {
            System.out.println("id: " + iter.getId() + "\tname: "+iter.getUserName() + "\tPass: " + iter.getPass());
        }
        //System.out.println("Que hongo>>>>>"+dao.insertUserPrueba(userPrueba));
    }
}
