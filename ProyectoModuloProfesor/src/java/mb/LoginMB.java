package mb;

import dao.UserDAO;
import java.io.Serializable;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import model.User;
import util.BusinessConstants;

/**
 * Controlador para la seccion de Login
 *
 * @author jair_
 */
@ManagedBean(name = "loginMB")
@SessionScoped
public class LoginMB implements Serializable {

    @ManagedProperty(value = "#{adminMB}")
    private AdminMB adminMB;

    @ManagedProperty(value = "#{excerciseMB}")
    private ExcerciseMB excerciseMB;

    private UserDAO userDAO;

    private User user;

    private String userName;

    private String userPass;

    private Boolean renderLogOut;

    @PostConstruct
    public void init() {
        user = new User();
        userDAO = new UserDAO();
        renderLogOut = false;
    }

    public String prepareLogin() {
        user = new User();
        renderLogOut = false;
        return "/index";
    }

    public String login() {
        String pageToReturn = "/index";
        User userFound;
        userFound = userDAO.findUserByName(userName);
        if (userFound != null) {
            if (userFound.getPass().equals(userPass)) {
                renderLogOut = true;
                user = userFound;
                if (user.getUserType().getId() == BusinessConstants.USERTYPE_ADMIN) {
                    adminMB.setUserDAO(userDAO);
                    adminMB.setUser(user);
                    pageToReturn = adminMB.prepareIndex();
                } else if (user.getUserType().getId() == BusinessConstants.USERTYPE_PROFE) {
                    excerciseMB.setUserDAO(userDAO);
                    excerciseMB.setUser(user);
                    pageToReturn = excerciseMB.prepareIndex();
                } else {
                    //Cambiar por alumno
                }
                FacesContext.getCurrentInstance()
                        .addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Usuario logeado", null));
            } else {
                FacesContext.getCurrentInstance()
                        .addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Password incorrecta", null));
            }
        } else {
            FacesContext.getCurrentInstance()
                    .addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Usuario no encontrado", null));
        }
        return pageToReturn;
    }

    public void setAdminMB(AdminMB adminMB) {
        this.adminMB = adminMB;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
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

    public void setExcerciseMB(ExcerciseMB excerciseMB) {
        this.excerciseMB = excerciseMB;
    }

    public Boolean getRenderLogOut() {
        return renderLogOut;
    }

    public void setRenderLogOut(Boolean renderLogOut) {
        this.renderLogOut = renderLogOut;
    }

}
