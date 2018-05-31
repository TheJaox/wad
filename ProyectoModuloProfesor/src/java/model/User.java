package model;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
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
    
    
    
}
