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

    @OneToMany(mappedBy = "multiType", cascade = CascadeType.ALL)
    private Set<Multimedia> multimedia;
    
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

    public Set<Multimedia> getMultimedia() {
        return multimedia;
    }

    public void setMultimedia(Set<Multimedia> multimedia) {
        this.multimedia = multimedia;
    }

}
