package model;

import java.io.Serializable;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
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
    
    @Column(name = "multiText")
    private String text;
        
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "idUser")
    private User user;
        
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "idMultiType")
    private MultiType multiType;

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
    
}