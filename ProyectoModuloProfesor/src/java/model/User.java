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
@Table(name = "users")
public class User implements Serializable {
    
    @Id
    @GeneratedValue
    @Column(name = "idUser")
    private int id;

    @Column(name = "userName")
    private String name;
    
    @Column(name = "fullName")
    private String fullName;
    
    @Column(name = "userPass")
    private String pass;
    
    @ManyToOne
    @JoinColumn(name = "idUserType")
    private UserType userType;

    @OneToMany(mappedBy = "profesor", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private Set<Grupo> grupos;
    
    @ManyToOne
    @JoinColumn(name = "idGrupo")
    private Grupo grupo;
    
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

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

    public UserType getUserType() {
        return userType;
    }

    public void setUserType(UserType userType) {
        this.userType = userType;
    }

    public Set<Grupo> getGrupos() {
        return grupos;
    }

    public void setGrupos(Set<Grupo> grupos) {
        this.grupos = grupos;
    }

    public Grupo getGrupo() {
        return grupo;
    }

    public void setGrupo(Grupo grupo) {
        this.grupo = grupo;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }
        
}
