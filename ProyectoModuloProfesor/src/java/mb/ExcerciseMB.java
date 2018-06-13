package mb;

import dao.ExcerciseDAO;
import dao.ExcerciseTypeDAO;
import dao.GrupoDAO;
import dao.MultiTypeDAO;
import dao.OptionDAO;
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
import model.Excercise;
import model.ExcerciseType;
import model.Grupo;
import model.MultiType;
import model.Multimedia;
import model.Option;
import model.User;
import org.jboss.weld.util.collections.ArraySet;
import util.BusinessConstants;

/**
 *
 * @author jair_
 */
@ManagedBean(name = "excerciseMB")
@SessionScoped
public class ExcerciseMB implements Serializable {

    private ExcerciseDAO excerciseDAO;

    private UserDAO userDAO;

    private MultiTypeDAO multiTypeDAO;

    private ExcerciseTypeDAO excerciseTypeDAO;
    
    private GrupoDAO grupoDAO;
    
    private OptionDAO optionDAO;

    private List<Excercise> ejercicios;

    private List<ExcerciseType> excerciseTypes;

    private List<Multimedia> multimedia;

    private List<MultiType> multiTypes;

    private List<Option> options;

    private User user;

    private User profesor;

    private Excercise ejercicio;

    private ExcerciseType excerciseType;

    private Option option;
    
    private Grupo grupo;

    private Integer id;

    private Boolean renderExcerciseType1;

    private Boolean renderExcerciseType2;

    private Boolean renderExcerciseType3;

    private Boolean renderIntructionButton;

    private Boolean renderTextButton;

    private Boolean renderOptionButton;

    @PostConstruct
    public void init() {
        excerciseDAO = new ExcerciseDAO();
        multiTypeDAO = new MultiTypeDAO();
        excerciseTypeDAO = new ExcerciseTypeDAO();
        grupoDAO = new GrupoDAO();
        optionDAO = new OptionDAO();
        ejercicios = new ArrayList<>();
        excerciseTypes = new ArrayList<>();
        multimedia = new ArrayList<>();
        multiTypes = new ArrayList<>();
        options = new ArrayList<>();
        ejercicio = new Excercise();
        excerciseType = new ExcerciseType();
        profesor = new User();
        option = new Option();
        grupo = new Grupo();
    }

    public String prepareIndex() {
        String pageToReturn = "/excercises/index";
        ejercicios = excerciseDAO.findAllExcercise();
        excerciseTypes = excerciseTypeDAO.findAllExcerciseType();
        return pageToReturn;
    }

    public String prepareAdd() {
        String pageToReturn = "/excercises/add";
        profesor = userDAO.findUserById(user.getId());
        if (profesor.getGrupos() == null) {
            pageToReturn = "/excercises/index";
            FacesContext.getCurrentInstance()
                    .addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "No tiene grupos", null));
        } else {
            getRenders();
            excerciseType = excerciseTypeDAO.findExcerciseTypeById(id);
            ejercicio = new Excercise();
            multiTypes = multiTypeDAO.findAllMultiType();
            FacesContext.getCurrentInstance()
                    .addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Add", null));
        }
        return pageToReturn;
    }

    private void getRenders() {
        if (excerciseType.getId() == BusinessConstants.EXCERCISETYPE_COMPLETE_SENTENCE) {
            renderExcerciseType1 = Boolean.TRUE;
            renderExcerciseType2 = Boolean.FALSE;
            renderExcerciseType3 = Boolean.FALSE;
        } else if (excerciseType.getId() == BusinessConstants.EXCERCISETYPE_SELECT_OPTION) {
            renderExcerciseType2 = Boolean.TRUE;
            renderExcerciseType1 = Boolean.FALSE;
            renderExcerciseType3 = Boolean.FALSE;
        } else {
            renderExcerciseType3 = Boolean.TRUE;
            renderExcerciseType2 = Boolean.FALSE;
            renderExcerciseType1 = Boolean.FALSE;
        }
    }
    
    public void addOption() {
        System.out.println("Add Option");
    }

    /* if (excerciseType.getId() == BusinessConstants.EXCERCISETYPE_COMPLETE_SENTENCE) {
            
        } else if (excerciseType.getId() == BusinessConstants.EXCERCISETYPE_SELECT_OPTION) {
            
        } else {
            
        } */
    public String add() {
        System.out.println("Add add");
        grupo = grupoDAO.findGrupoById(id);
        ejercicio.setGrupo(grupo);
        ejercicio.setExcerciseType(excerciseType);
        ejercicio.setUser(profesor);
        Set<Option> setOp = new ArraySet<>();
        setOp.add(option);
        option.setCorrecto(1);
        System.out.println("Add1");
        ejercicio.setOpciones(null);
        System.out.println("Add2");
        int i = excerciseDAO.insertExcercise(ejercicio);
        ejercicio.setId(i);
        System.out.println("Add3");
        option.setExcercise(ejercicio);
        optionDAO.insertOption(option);
        return prepareIndex();
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

    public UserDAO getUserDAO() {
        return userDAO;
    }

    public void setUserDAO(UserDAO userDAO) {
        this.userDAO = userDAO;
    }

    public List<Multimedia> getMultimedia() {
        return multimedia;
    }

    public void setMultimedia(List<Multimedia> multimedia) {
        this.multimedia = multimedia;
    }

    public List<MultiType> getMultiTypes() {
        return multiTypes;
    }

    public void setMultiTypes(List<MultiType> multiTypes) {
        this.multiTypes = multiTypes;
    }

    public List<ExcerciseType> getExcerciseTypes() {
        return excerciseTypes;
    }

    public void setExcerciseTypes(List<ExcerciseType> excerciseTypes) {
        this.excerciseTypes = excerciseTypes;
    }

    public ExcerciseType getExcerciseType() {
        return excerciseType;
    }

    public void setExcerciseType(ExcerciseType excerciseType) {
        this.excerciseType = excerciseType;
    }

    public Boolean getRenderExcerciseType1() {
        return renderExcerciseType1;
    }

    public void setRenderExcerciseType1(Boolean renderExcerciseType1) {
        this.renderExcerciseType1 = renderExcerciseType1;
    }

    public Boolean getRenderExcerciseType2() {
        return renderExcerciseType2;
    }

    public void setRenderExcerciseType2(Boolean renderExcerciseType2) {
        this.renderExcerciseType2 = renderExcerciseType2;
    }

    public Boolean getRenderExcerciseType3() {
        return renderExcerciseType3;
    }

    public void setRenderExcerciseType3(Boolean renderExcerciseType3) {
        this.renderExcerciseType3 = renderExcerciseType3;
    }

    public Boolean getRenderIntructionButton() {
        return renderIntructionButton;
    }

    public void setRenderIntructionButton(Boolean renderIntructionButton) {
        this.renderIntructionButton = renderIntructionButton;
    }

    public Boolean getRenderTextButton() {
        return renderTextButton;
    }

    public void setRenderTextButton(Boolean renderTextButton) {
        this.renderTextButton = renderTextButton;
    }

    public Boolean getRenderOptionButton() {
        return renderOptionButton;
    }

    public void setRenderOptionButton(Boolean renderOptionButton) {
        this.renderOptionButton = renderOptionButton;
    }

    public List<Option> getOptions() {
        return options;
    }

    public void setOptions(List<Option> options) {
        this.options = options;
    }

    public Option getOption() {
        return option;
    }

    public void setOption(Option option) {
        this.option = option;
    }

}
