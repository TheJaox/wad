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
@Table(name = "excerciseType")
public class ExcerciseType implements Serializable {
    
    @Id
    @GeneratedValue
    @Column(name = "idExcerciseType")
    private int id;
    
    @Column(name = "excerciseTypeName")
    private String name;

    @OneToMany(mappedBy = "excerciseType", cascade = CascadeType.ALL)
    private Set<Excercise> excercises;
    
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

    public Set<Excercise> getExcercises() {
        return excercises;
    }

    public void setExcercises(Set<Excercise> excercises) {
        this.excercises = excercises;
    }
    
}
