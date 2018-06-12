package mb;

import dao.GrupoDAO;
import dao.UserDAO;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import model.Grupo;
import model.User;
import org.jboss.weld.util.collections.ArraySet;

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

    private int id;

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
        alumnos = userDAO.findAllAlumnosAndNotGrupo();
        alumnosAgregados = new ArrayList<>();
        grupoName = "";
        grupo = new Grupo();
        return pageToReturn;
    }

    public void onAdd(User alumno) {
        alumnoSeleccionado = alumno;
        int index = 0;
        for (User iterAlumno : alumnos) {
            if (iterAlumno.getId() == alumnoSeleccionado.getId()) {
                break;
            }
            index++;
        }
        alumnos.remove(index);
        alumnosAgregados.add(alumnoSeleccionado);
    }

    public void onRemove(User alumno) {
        alumnoSeleccionado = alumno;
        int index = 0;
        for (User iterAlumno : alumnosAgregados) {
            if (iterAlumno.getId() == alumnoSeleccionado.getId()) {
                break;
            }
            index++;
        }
        alumnosAgregados.remove(index);
        alumnos.add(alumnoSeleccionado);
    }

    public String add() {
        String pageToReturn = "/grupos/add";
        profesor = userDAO.findUserById(id);
        grupo = new Grupo();
        grupo.setName(grupoName);
        grupo.setProfesor(profesor);
        Set<User> alumnosGrupo = new ArraySet<>();
        for (User iterAlumno : alumnosAgregados) {
            alumnosGrupo.add(iterAlumno);
        }
        grupo.setUsers(alumnosGrupo);
        int result = grupoDAO.insertGrupo(grupo);
        if (result > 0) {
            grupo.setId(result);
            for (User iterAlumno : alumnosAgregados) {
                iterAlumno.setGrupo(grupo);
                userDAO.updateUser(iterAlumno);
            }
            System.out.println("Insert grupo: " + result);
            pageToReturn = prepareIndex();
        } else {
            FacesContext.getCurrentInstance()
                    .addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error al hacer el grupo", null));
        }
        return pageToReturn;
    }

    public String prepareUpdate() {
        String pageToReturn = "/grupos/edit";
        grupo = grupoDAO.findGrupoById(grupo.getId());
        profesores = userDAO.findAllProfesores();
        profesor = userDAO.findUserById(grupo.getProfesor().getId());
        id = profesor.getId();
        alumnosAgregados = new ArrayList<>(grupo.getUsers());
        alumnos = userDAO.findAllAlumnosAndNotGrupo();
        grupoName = grupo.getName();
        return pageToReturn;
    }

    public String update() {
        profesor = userDAO.findUserById(id);
        grupo.setName(grupoName);
        grupo.setProfesor(profesor);
        Set<User> alumnosGrupo = new ArraySet<>();
        for (User iterAlumno : alumnosAgregados) {
            alumnosGrupo.add(iterAlumno);
        }
        grupo.setUsers(alumnosGrupo);
        grupoDAO.updateGrupo(grupo);
        for (User iterAlumno : alumnosAgregados) {
            iterAlumno.setGrupo(grupo);
            userDAO.updateUser(iterAlumno);
        }
        for (User iterAlumno : alumnos) {
            iterAlumno.setGrupo(null);
            userDAO.updateUser(iterAlumno);
        }
        return prepareIndex();
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

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

}
