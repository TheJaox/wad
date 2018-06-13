package model;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 *
 * @author jair_
 */
@Entity
@Table(name = "options")
public class Option implements Serializable {
    
    @Id
    @GeneratedValue
    @Column(name = "idOption")
    private int id;
    
    @Column(name = "optionText")
    private String text;
    
    @Column(name = "stCorrect")
    private int correcto;
        
    @ManyToOne
    @JoinColumn(name = "idExcercise")
    private Excercise excercise;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public int getCorrecto() {
        return correcto;
    }

    public void setCorrecto(int correcto) {
        this.correcto = correcto;
    }

    public Excercise getExcercise() {
        return excercise;
    }

    public void setExcercise(Excercise excercise) {
        this.excercise = excercise;
    }
    
}
