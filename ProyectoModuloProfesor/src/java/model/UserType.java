package model;

import java.io.Serializable;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * Mapeo de la entidad userType
 * @author jair_
 */
@Entity
@Table(name = "userType")
public class UserType implements Serializable {
    
    @Id
    @GeneratedValue
    @Column(name = "idUserType")
    private Integer id;

    @Column(name = "userTypeName")
    private String userTypeName;
    
    @OneToMany(mappedBy = "userType", cascade = CascadeType.ALL)
    private Set<User> users;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUserTypeName() {
        return userTypeName;
    }

    public void setUserTypeName(String userTypeName) {
        this.userTypeName = userTypeName;
    }
    
    @Override
    public String toString() {
        return "UserType:\tId: " + this.getId() + "\tUserType: " + this.getUserTypeName() + "\n";
    }
}
