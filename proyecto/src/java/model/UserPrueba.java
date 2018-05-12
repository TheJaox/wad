package model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Mapeo con anotaciones de la entidad 'users', un simple POJO. 
 * La anotacion 'Entity' define que es una entidad la clase. 
 * La anotacion 'Table' define el nombre de la tabla a mapear.
 * La anotacion de 'Column' define el nombre de la columna en la bd.
 * @author jair_
 */
@Entity
@Table(name = "users")
public class UserPrueba {

    /**
     * Anotaciones para la id que se autogenera cuando se inserta un nuevo
     * elemento
     */
    @Id
    @GeneratedValue
    @Column(name = "id")
    private int id;

    @Column(name = "user_name")
    private String userName;

    @Column(name = "pass")
    private String pass;

    public UserPrueba() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }
}
