package model;

import java.io.Serializable;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
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
    private User user;

    @OneToMany(mappedBy = "grupo", cascade = CascadeType.ALL)
    private Set<User> users;
    
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
        return user;
    }

    public void setProfesor(User profesor) {
        this.user = profesor;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Set<User> getUsers() {
        return users;
    }

    public void setUsers(Set<User> users) {
        this.users = users;
    }

}
