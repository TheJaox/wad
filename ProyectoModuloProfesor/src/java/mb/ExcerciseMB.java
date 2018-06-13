package mb;

import dao.ExcerciseDAO;
import dao.ExcerciseTypeDAO;
import dao.GrupoDAO;
import dao.MultiTypeDAO;
import dao.MultimediaDAO;
import dao.OptionDAO;
import dao.UserDAO;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.PhaseId;
import model.Excercise;
import model.ExcerciseType;
import model.Grupo;
import model.MultiType;
import model.Multimedia;
import model.Option;
import model.User;
import org.jboss.weld.util.collections.ArraySet;
import org.primefaces.event.DragDropEvent;
import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;
import org.primefaces.model.UploadedFile;
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

    private MultimediaDAO multimediaDAO;

    private MultiTypeDAO multiTypeDAO;

    private ExcerciseTypeDAO excerciseTypeDAO;

    private GrupoDAO grupoDAO;

    private OptionDAO optionDAO;

    private List<Excercise> ejercicios;

    private List<ExcerciseType> excerciseTypes;

    private List<Multimedia> multimedia;

    private List<Multimedia> multimediaDropped;

    private List<MultiType> multiTypes;

    private List<Option> options;

    private Multimedia multi;

    private User user;

    private User profesor;

    private Excercise ejercicio;

    private ExcerciseType excerciseType;

    private Option option;

    private Grupo grupo;

    private Integer id;

    private UploadedFile uploadedFile;

    private Boolean renderAdd;

    private Boolean renderUpload;

    @PostConstruct
    public void init() {
        excerciseDAO = new ExcerciseDAO();
        multiTypeDAO = new MultiTypeDAO();
        excerciseTypeDAO = new ExcerciseTypeDAO();
        grupoDAO = new GrupoDAO();
        optionDAO = new OptionDAO();
        multimediaDAO = new MultimediaDAO();
        ejercicios = new ArrayList<>();
        excerciseTypes = new ArrayList<>();
        multimediaDropped = new ArrayList<>();
        multimedia = new ArrayList<>();
        multiTypes = new ArrayList<>();
        options = new ArrayList<>();
        ejercicio = new Excercise();
        excerciseType = new ExcerciseType();
        profesor = new User();
        option = new Option();
        grupo = new Grupo();
        renderUpload = false;
        renderAdd = true;
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
            excerciseType = excerciseTypeDAO.findExcerciseTypeById(id);
            ejercicio = new Excercise();
            multiTypes = multiTypeDAO.findAllMultiType();
            renderAdd = true;
            getImages();
            FacesContext.getCurrentInstance()
                    .addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Add", null));
        }
        return pageToReturn;
    }

    public void addOption() {
        System.out.println("Add Option");
        renderAdd = false;
    }

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
        System.out.println("Excercise: " + i);
        option.setExcercise(ejercicio);
        i = optionDAO.insertOption(option);
        System.out.println("Option: " + i);
        for(Multimedia iter : multimediaDropped) {
            iter = multimediaDAO.findMultimediaById(iter.getId());
            Set<Excercise> excercises = iter.getExcercises();
            excercises.add(ejercicio);
            multimediaDAO.updateMultimedia(iter);
        }
        return prepareIndex();
    }

    public void upload() {
        if (uploadedFile != null) {
            renderUpload = false;
            multi = new Multimedia();
            multi.setExcercises(null);
            multi.setFile(uploadedFile.getContents());
            MultiType multiTypeTemp = multiTypeDAO.findMultiTypeById(BusinessConstants.MULTITYPE_IMAGE);
            multi.setMultiType(multiTypeTemp);
            multi.setName(uploadedFile.getFileName());
            multi.setContent(uploadedFile.getContentType());
            multi.setUser(profesor);
            System.out.println("Insertar multimedia");
            int i = multimediaDAO.insertMultimedia(multi);
            System.out.println("Id: " + i);
            if (i > 0) {
                FacesMessage message = new FacesMessage("Succesful", uploadedFile.getFileName() + " is uploaded.");
                FacesContext.getCurrentInstance().addMessage(null, message);
                getImages();
            }
        } else {
            FacesContext.getCurrentInstance()
                    .addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error al subir imagen", null));
        }
    }

    private void getImages() {
        multimedia = multimediaDAO.findAllMultimediaByUser(profesor);
        int i = 0;
        List<Integer> list = new ArrayList<>();
        
        for(Multimedia iter : multimedia) {
            for(i = 0; i < multimediaDropped.size(); i++) {
                if(multimedia.get(i).getId() == multimediaDropped.get(i).getId()) {
                    list.add(i);
                }
            }
        }
        for(Integer iterInt : list) {
            boolean remove = multimedia.remove(iterInt);
            if(!remove)
                break;
        }
    }

    public StreamedContent getImage() throws IOException {
        FacesContext context = FacesContext.getCurrentInstance();

        if (context.getCurrentPhaseId() == PhaseId.RENDER_RESPONSE) {
            return new DefaultStreamedContent();
        } else {
            String imageId = context.getExternalContext().getRequestParameterMap().get("imageId");
            Multimedia image = multimediaDAO.findMultimediaById(Integer.parseInt(imageId));
            return new DefaultStreamedContent(new ByteArrayInputStream(image.getFile()));
        }
    }

    public void onImageDrop(DragDropEvent ddEvent) {
        Multimedia selectedImage = ((Multimedia) ddEvent.getData());
        multimediaDropped.add(selectedImage);
        multimedia.remove(selectedImage);
    }

    public void addImages() {
        renderUpload = true;
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

    public UploadedFile getUploadedFile() {
        return uploadedFile;
    }

    public void setUploadedFile(UploadedFile uploadedFile) {
        this.uploadedFile = uploadedFile;
    }

    public Grupo getGrupo() {
        return grupo;
    }

    public void setGrupo(Grupo grupo) {
        this.grupo = grupo;
    }

    public Boolean getRenderUpload() {
        return renderUpload;
    }

    public void setRenderUpload(Boolean renderUpload) {
        this.renderUpload = renderUpload;
    }

    public Multimedia getMulti() {
        return multi;
    }

    public void setMulti(Multimedia multi) {
        this.multi = multi;
    }

    public Boolean getRenderAdd() {
        return renderAdd;
    }

    public void setRenderAdd(Boolean renderAdd) {
        this.renderAdd = renderAdd;
    }

    public List<Multimedia> getMultimediaDropped() {
        return multimediaDropped;
    }

    public void setMultimediaDropped(List<Multimedia> multimediaDropped) {
        this.multimediaDropped = multimediaDropped;
    }

}
