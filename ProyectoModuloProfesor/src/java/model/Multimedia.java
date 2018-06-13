package model;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 *
 * @author jair_
 */
@Entity
@Table(name = "multimedia")
public class Multimedia implements Serializable {
    
    @Id
    @GeneratedValue
    @Column(name = "idMultimedia")
    private int id;
    
    @Column(name = "multimediaName")
    private String name;
    
    @Column(name = "multiFile")
    private byte[] file;
    
    @Column(name = "multiContent")
    private String content;
    
    @Column(name = "multiText")
    private String text;
        
    @ManyToOne
    @JoinColumn(name = "idUser")
    private User user;
        
    @ManyToOne
    @JoinColumn(name = "idMultiType")
    private MultiType multiType;
    
    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@JoinTable(name = "multiExcercise", catalog = "proyectoWad", joinColumns = { 
			@JoinColumn(name = "idMultimedia", nullable = false, updatable = false) }, 
			inverseJoinColumns = { @JoinColumn(name = "idExcercise", 
					nullable = false, updatable = false) })
    private Set<Excercise> excercises = new HashSet<>();

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public byte[] getFile() {
        return file;
    }

    public void setFile(byte[] file) {
        this.file = file;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public MultiType getMultiType() {
        return multiType;
    }

    public void setMultiType(MultiType multiType) {
        this.multiType = multiType;
    }

    public Set<Excercise> getExcercises() {
        return excercises;
    }

    public void setExcercises(Set<Excercise> excercises) {
        this.excercises = excercises;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
    
}
