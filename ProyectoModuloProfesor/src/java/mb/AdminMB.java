package mb;

import dao.UserDAO;
import dao.UserTypeDAO;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import model.User;
import model.UserType;

/**
 * Controlador para la seccion de Administrador
 *
 * @author jair_
 */
@ManagedBean(name = "adminMB")
@SessionScoped
public class AdminMB implements Serializable {

    private UserDAO userDAO;

    private UserTypeDAO userTypeDAO;

    private List<User> users;

    private User user;

    private String name;
    
    private String fullName;

    private String pass;

    private Integer id;

    private List<UserType> userTypes;

    private UserType userTypeSelected;

    @PostConstruct
    public void init() {
        userTypeDAO = new UserTypeDAO();
        user = new User();
        userTypeSelected = new UserType();
        users = new ArrayList<>();
        userTypes = new ArrayList<>();
    }

    public String prepareIndex() {
        String pageToReturn = "/admin/index";
        users = userDAO.findAllUser();
        return pageToReturn;
    }

    public String prepareAdd() {
        String pageToReturn = "/admin/add";
        userTypes = userTypeDAO.findAllUserType();
        if (userTypes == null) {
            FacesContext.getCurrentInstance()
                    .addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "No hay tipo de usuarios", null));
            pageToReturn = "/admin/index";
        }
        return pageToReturn;
    }

    public String add() {
        String pageToReturn;
        user = new User();
        user.setName(name);
        user.setFullName(fullName);
        user.setPass(pass);
        userTypeSelected = userTypeDAO.findUserTypeById(id);
        user.setUserType(userTypeSelected);
        if (userDAO.insertUser(user) == 0) {
            FacesContext.getCurrentInstance()
                    .addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error al crear usuario", null));
            pageToReturn = "/admin/add";
        } else {
            FacesContext.getCurrentInstance()
                    .addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Usuario creado", null));
            pageToReturn = prepareIndex();
        }
        return pageToReturn;
    }

    public String prepareUpdate() {
        user = userDAO.findUserById(user.getId());
        name = user.getName();
        fullName = user.getFullName();
        pass = user.getPass();
        userTypes = userTypeDAO.findAllUserType();
        return "/admin/edit";
    }

    public String update() {
        user.setName(name);
        user.setFullName(fullName);
        user.setPass(pass);
        userTypeSelected = userTypeDAO.findUserTypeById(id);
        user.setUserType(userTypeSelected);
        userDAO.updateUser(user);
        FacesContext.getCurrentInstance()
                .addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "Uusario modificado", null));

        return prepareIndex();
    }

    public String delete() {
        user = userDAO.findUserById(user.getId());
        if (userDAO.deleteUser(user) > 0) {
            FacesContext.getCurrentInstance()
                    .addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Usuario eliminado", null));
        } else {
            FacesContext.getCurrentInstance()
                    .addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error al eliminar", null));
        }
        return prepareIndex();
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

    public List<UserType> getUserTypes() {
        return userTypes;
    }

    public void setUserTypes(List<UserType> userTypes) {
        this.userTypes = userTypes;
    }

    public UserType getUserTypeSelected() {
        return userTypeSelected;
    }

    public void setUserTypeSelected(UserType userTypeSelected) {
        this.userTypeSelected = userTypeSelected;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

}
