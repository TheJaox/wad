package mb;

import dao.crudDAO;
import java.io.Serializable;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.faces.application.FacesMessage;
import model.UserPrueba;

/**
 *
 * @author jair_
 */
@ManagedBean(name = "loginMB")
@SessionScoped
public class LoginMB implements Serializable{
    
    private String userName;
    
    private String userPass;
    
    private crudDAO dao;
    
    private CrudMB crudMB = new CrudMB();
    
    private Boolean userNameFlag = true;
    
    private Boolean userPassFlag = true;
    
    public String prueba() {
        String redirect = "index";
        System.out.println("User: "+userName);
        System.out.println("Pass: "+userPass);
        dao = new crudDAO();
        List<UserPrueba> users = dao.findAllUserPueba();
        for(UserPrueba iter : users) {
            if(iter.getUserName().equals(userName)) {
                if(iter.getPass().equals(userPass)) {
                    System.err.println("userLogeado");
                    crudMB.setUserPrueba(iter);
                    redirect = crudMB.prepareIndex();
                    userNameFlag = false;
                    userPassFlag = false;
                } else {
                    System.out.println("Password incorrecta");
                }
            } else {
                System.out.println("Username incorrecto");
            }
        }
        if(userNameFlag) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "UserName incorrecto"));
        }
        if(userPassFlag) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "Password incorrecto"));
        }
        return redirect;
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
