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
@Table(name = "answer")
public class Answer implements Serializable {
    
    @Id
    @GeneratedValue
    @Column(name = "idAnswer")
    private int id;
    
    @Column(name = "answerText")
    private String answerText;
     
    @ManyToOne
    @JoinColumn(name = "idExcercise")
    private Excercise excercise;   

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getAnswerText() {
        return answerText;
    }

    public void setAnswerText(String answerText) {
        this.answerText = answerText;
    }

    public Excercise getExcercise() {
        return excercise;
    }

    public void setExcercise(Excercise excercise) {
        this.excercise = excercise;
    }
        
}
