package mb;

import java.io.Serializable;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

/**
 *
 * @author jair_
 */
@ManagedBean(name = "loginMB")
@SessionScoped
public class loginMB implements Serializable{
    
    private String userName;
    
    private String userPass;
    
    public void prueba() {
        System.out.println("Quiubo");
        System.out.println("User: "+userName);
        System.out.println("Pass: "+userPass);
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
