package mb;

import dao.crudDAO;
import java.io.Serializable;
import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import model.UserPrueba;

/**
 *
 * @author jair_
 */
@ManagedBean(name = "crudMB")
@SessionScoped
public class CrudMB implements Serializable{
    
    private UserPrueba userPrueba;
    
    private List<UserPrueba> users;
        
    private String userName;
    
    private String userPass;
    
    private crudDAO dao = new crudDAO();
    
    public String prueba() {
        String redirect = "index";
        Boolean userNameFlag = true;
        System.out.println("User: "+userName);
        System.out.println("Pass: "+userPass);
        dao = new crudDAO();
        List<UserPrueba> usersList = dao.findAllUserPueba();
        for(UserPrueba iter : usersList) {
            System.out.println("iter: "+iter.getUserName());
            System.out.println("iter: "+iter.getPass());
            if(iter.getUserName().equals(userName)) {
                if(iter.getPass().equals(userPass)) {
                    System.err.println("userLogeado");
                    redirect = prepareIndex();
                    userNameFlag = false;
                } else {
                    System.out.println("Password incorrecta");
                    FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "Password incorrecto"));
                }
            } else {
                System.out.println("Username incorrecto");
            }
        }
        if(userNameFlag) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "UserName incorrecto"));
        }
        return redirect;
    }
    
    public String prepareIndex() {
        String redirect = "/crud/index";
        //System.out.println("UserPureba: "+userPrueba.toString());
        users = dao.findAllUserPueba();
        System.out.println("Users: "+users.size());
        return redirect;
    }
    
    public String prepareAdd() {
        String redirect = "add";
        System.out.println("PreapreAdd");
        userName = "";
        userPass = "";
        return redirect;
    }
    
    public String add() {
        String redirect = "index";
        System.out.println("UserName: "+userName);
        System.out.println("UserPass: "+userPass);
        userPrueba = new UserPrueba();
        userPrueba.setUserName(userName);
        userPrueba.setPass(userPass);
        dao.insertUserPrueba(userPrueba);
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Info add", "Usuario agregado"));
        
        return prepareIndex();
    }
    
    public String prepareUpdate(UserPrueba user) {
        String redirect = "edit";
        userPrueba = dao.findUserPruebaById(user.getId());
        System.out.println("UserPureba: "+userPrueba.toString());
        return redirect;
    }
    
    public String update() {
        String redirect;
        System.out.println("UserName: "+userName);
        System.out.println("UserPass: "+userPass);
        userPrueba.setUserName(userName);
        userPrueba.setPass(userPass);
        dao.updateUserPrueba(userPrueba);
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Info edit", "Usuario modificado"));
        redirect = prepareIndex();
        return redirect;
    }
    
    public String delete(UserPrueba user) {
        String redirect = "index";
        System.out.println("UserName: "+user.getUserName());
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Info delete", "Usuario eliminado"));
        dao.deleteUserPrueba(user);
        return prepareIndex();
    }

    public UserPrueba getUserPrueba() {
        return userPrueba;
    }

    public void setUserPrueba(UserPrueba userPrueba) {
        this.userPrueba = userPrueba;
    }

    public List<UserPrueba> getUsers() {
        return users;
    }

    public void setUsers(List<UserPrueba> users) {
        this.users = users;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserPass() {
        return userPass;
    }

    public void setUserPass(String userPass) {
        this.userPass = userPass;
    }
}
