package model;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 *
 * @author jair_
 */
@Entity
@Table(name = "multiExcercise")
public class MultiExcercise implements Serializable {
    
    @Id
    @Column(name = "idExcercise")
    private int idExcercise;
    
    @Column(name = "idMultimedia")
    private int idMultimedia;

    public int getIdExcercise() {
        return idExcercise;
    }

    public void setIdExcercise(int idExcercise) {
        this.idExcercise = idExcercise;
    }

    public int getIdMultimedia() {
        return idMultimedia;
    }

    public void setIdMultimedia(int idMultimedia) {
        this.idMultimedia = idMultimedia;
    }
    
}
