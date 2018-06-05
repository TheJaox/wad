package mb;

import dao.UserDAO;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import model.User;

/**
 * Controlador para la seccion de Administrador
 * @author jair_
 */
@ManagedBean(name = "adminMB")
@SessionScoped
public class AdminMB implements Serializable {
    
    private UserDAO userDAO;
    
    private List<User> users;
    
    private User user;
    
    @PostConstruct
    public void init() {
        user = new User();
        users = new ArrayList<>();
    }
    
    public String prepareIndex() {
        String pageToReturn = "/admin/index";
        users = userDAO.findAllUser();
        return pageToReturn;
    }
    
    public String prepareAdd() {
        return "/admin/add";
    }
    
    public String prepareUpdate() {
        user = userDAO.findUserById(user.getId());
        return "/admin/edit";
    }
    
    public String delete() {
        user = userDAO.findUserById(user.getId());
        userDAO.deleteUser(user);
        FacesContext.getCurrentInstance()
                    .addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Usuario eliminado", null));
        return "/admin/index";
    }

    public UserDAO getUserDAO() {
        return userDAO;
    }

    public void setUserDAO(UserDAO userDAO) {
        this.userDAO = userDAO;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public List<User> getUsers() {
        return users;
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }
    
}
