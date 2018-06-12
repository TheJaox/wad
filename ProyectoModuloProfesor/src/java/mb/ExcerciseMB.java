package mb;

import dao.ExcerciseDAO;
import dao.UserDAO;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import model.Excercise;
import model.User;
import org.primefaces.context.RequestContext;

/**
 *
 * @author jair_
 */
@ManagedBean(name = "excerciseMB")
@SessionScoped
public class ExcerciseMB implements Serializable {

    private ExcerciseDAO excerciseDAO;
    
    private UserDAO userDAO;

    private List<Excercise> ejercicios;

    private User user;
    
    private User profesor;

    private Excercise ejercicio;
    
    private Integer id;

    @PostConstruct
    public void init() {
        excerciseDAO = new ExcerciseDAO();
        ejercicios = new ArrayList<>();
        ejercicio = new Excercise();
        profesor = new User();
    }

    public String prepareIndex() {
        String pageToReturn = "/excercises/index";
        ejercicios = excerciseDAO.findAllExcercise();
        return pageToReturn;
    }

    public String prepareAdd() {
        String pageToReturn = "/excercises/add";
        ejercicio = new Excercise();
        profesor = userDAO.findUserById(user.getId());
        FacesContext.getCurrentInstance()
                .addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Add", null));
        return pageToReturn;
    }

    public String add() {
        String pageToReturn = "";
        return pageToReturn;
    }

    public String prepareUpdate() {
        String pageToReturn = "";
        return pageToReturn;
    }

    public String update() {
        String pageToReturn = "";
        return pageToReturn;
    }

    public String delete() {
        String pageToReturn = "";
        return pageToReturn;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public List<Excercise> getEjercicios() {
        return ejercicios;
    }

    public void setEjercicios(List<Excercise> ejercicios) {
        this.ejercicios = ejercicios;
    }

    public Excercise getEjercicio() {
        return ejercicio;
    }

    public void setEjercicio(Excercise ejercicio) {
        this.ejercicio = ejercicio;
    }

    public User getProfesor() {
        return profesor;
    }

    public void setProfesor(User profesor) {
        this.profesor = profesor;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

}
