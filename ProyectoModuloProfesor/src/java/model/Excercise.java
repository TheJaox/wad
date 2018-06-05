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
@Table(name = "excercises")
public class Excercise implements Serializable {
    
    @Id
    @GeneratedValue
    @Column(name = "idExcercise")
    private int id;
    
    @Column(name = "excerciseName")
    private String name;
        
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "idUser")
    private User user;

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
    
}
