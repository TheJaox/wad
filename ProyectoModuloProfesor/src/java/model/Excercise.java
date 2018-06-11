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
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 *
 * @author jair_
 */
@Entity
@Table(name = "excercises")
public class Excercise implements Serializable {
    
    @Id
    @GeneratedValue
    @Column(name = "idExcercise")
    private int id;
    
    @Column(name = "excerciseName")
    private String name;
        
    @ManyToOne
    @JoinColumn(name = "idUser")
    private User user;

    @OneToMany(mappedBy = "excercise", cascade = CascadeType.ALL)
    private Set<Answer> respuestas;
    
    @ManyToMany(fetch = FetchType.LAZY, mappedBy = "excercises")
    private Set<Multimedia> multimedias = new HashSet();
    
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

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Set<Answer> getRespuestas() {
        return respuestas;
    }

    public void setRespuestas(Set<Answer> respuestas) {
        this.respuestas = respuestas;
    }

    public Set<Multimedia> getMultimedias() {
        return multimedias;
    }

    public void setMultimedias(Set<Multimedia> multimedias) {
        this.multimedias = multimedias;
    }
    
}
