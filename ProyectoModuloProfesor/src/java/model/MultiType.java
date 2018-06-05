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
@Table(name = "multiType")
public class MultiType implements Serializable {
    
    @Id
    @GeneratedValue
    @Column(name = "idMultiType")
    private int id;
    
    @Column(name = "multiTypeName")
    private String name;

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
        
}
