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
@Table(name = "users")
public class User implements Serializable {
    
    @Id
    @GeneratedValue
    @Column(name = "idUser")
    private int id;

    @Column(name = "userName")
    private String name;
    
    @Column(name = "userPass")
    private String pass;
    
    @ManyToOne
    @JoinColumn(name = "idUserType")
    private UserType userType;

    @OneToMany(mappedBy = "users", cascade = CascadeType.ALL)
    private Set<Grupo> grupos;
    
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
}
