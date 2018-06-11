package mb;

import dao.GrupoDAO;
import dao.UserDAO;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import model.Grupo;
import model.User;

/**
 * Controlador para la seccion de Grupos
 *
 * @author jair_
 */
@ManagedBean(name = "gruposMB")
@SessionScoped
public class GruposMB implements Serializable {

    private UserDAO userDAO;

    private GrupoDAO grupoDAO;

    private List<Grupo> grupos;

    private List<User> alumnos;
    
    private List<User> alumnosAgregados;
    
    private List<User> profesores;
    
    private Grupo grupo;

    private User profesor;
    
    private User alumnoSeleccionado;

    private User user;
    
    private String grupoName;

    @PostConstruct
    public void init() {
        grupos = new ArrayList<>();
        alumnos = new ArrayList<>();
        alumnosAgregados = new ArrayList<>();
        profesores = new ArrayList<>();
        profesor = new User();
        user = new User();
        grupoDAO = new GrupoDAO();
        userDAO = new UserDAO();
    }

    public String prepareIndex() {
        String pageToReturn = "/grupos/index";
        grupos = grupoDAO.findAllGrupo();
        if (grupos.isEmpty()) {
            FacesContext.getCurrentInstance()
                    .addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "No hay grupos", null));
        }
        return pageToReturn;
    }

    public String prepareAdd() {
        String pageToReturn = "/grupos/add";
        profesores = userDAO.findAllProfesores();
        alumnos = userDAO.findAllAlumnos();
        alumnosAgregados = new ArrayList<>();
        grupoName = "";
        grupo = new Grupo();
        return pageToReturn;
    }
    
    public void onAdd() {
        int index = 0;
        for(User iterAlumno : alumnos) {
            if(iterAlumno.getId() == alumnoSeleccionado.getId()) {
                break;
            }
            index++;
        }
        alumnos.remove(index);
        alumnosAgregados.add(alumnoSeleccionado);
    }
    
    public void onRemove() {
        int index = 0;
        for(User iterAlumno : alumnosAgregados) {
            if(iterAlumno.getId() == alumnoSeleccionado.getId()) {
                break;
            }
            index++;
        }
        alumnosAgregados.remove(index);
        alumnos.add(alumnoSeleccionado);
    }

    public String add() {
        String pageToReturn = "/grupos/index";
        return pageToReturn;
    }

    public String prepareUpdate() {
        String pageToReturn = "/grupos/index";
        return pageToReturn;
    }

    public String update() {
        String pageToReturn = "/grupos/index";
        return pageToReturn;
    }

    public String delete() {
        String pageToReturn = "/grupos/index";
        return pageToReturn;
    }

    public List<Grupo> getGrupos() {
        return grupos;
    }

    public void setGrupos(List<Grupo> grupos) {
        this.grupos = grupos;
    }

    public List<User> getAlumnos() {
        return alumnos;
    }

    public void setAlumnos(List<User> alumnos) {
        this.alumnos = alumnos;
    }

    public User getProfesor() {
        return profesor;
    }

    public void setProfesor(User profesor) {
        this.profesor = profesor;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Grupo getGrupo() {
        return grupo;
    }

    public void setGrupo(Grupo grupo) {
        this.grupo = grupo;
    }

    public List<User> getProfesores() {
        return profesores;
    }

    public void setProfesores(List<User> profesores) {
        this.profesores = profesores;
    }

    public String getGrupoName() {
        return grupoName;
    }

    public void setGrupoName(String grupoName) {
        this.grupoName = grupoName;
    }

    public List<User> getAlumnosAgregados() {
        return alumnosAgregados;
    }

    public void setAlumnosAgregados(List<User> alumnosAgregados) {
        this.alumnosAgregados = alumnosAgregados;
    }

    public User getAlumnoSeleccionado() {
        return alumnoSeleccionado;
    }

    public void setAlumnoSeleccionado(User alumnoSeleccionado) {
        this.alumnoSeleccionado = alumnoSeleccionado;
    }
    
}
