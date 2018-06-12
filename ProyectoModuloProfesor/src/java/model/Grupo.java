package model;

import java.io.Serializable;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 *
 * @author jair_
 */
@Entity
@Table(name = "grupo")
public class Grupo implements Serializable {
    
    @Id
    @GeneratedValue
    @Column(name = "idGrupo")
    private int id;

    @Column(name = "groupName")
    private String name;
        
    @ManyToOne
    @JoinColumn(name = "idProfesor")
    private User profesor;

    @OneToMany(mappedBy = "grupo", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private Set<User> users;
    
    @OneToMany(mappedBy = "grupo", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private Set<Excercise> ejercicios;
    
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

    public User getProfesor() {
        return profesor;
    }

    public void setProfesor(User profesor) {
        this.profesor = profesor;
    }

    public Set<User> getUsers() {
        return users;
    }

    public void setUsers(Set<User> users) {
        this.users = users;
    }

    public Set<Excercise> getEjercicios() {
        return ejercicios;
    }

    public void setEjercicios(Set<Excercise> ejercicios) {
        this.ejercicios = ejercicios;
    }

}
