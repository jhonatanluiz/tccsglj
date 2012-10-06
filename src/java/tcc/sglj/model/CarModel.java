/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package tcc.sglj.model;

import java.util.Collection;
import javax.annotation.Generated;
import javax.persistence.CascadeType;
import javax.persistence.Id;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.ManyToOne;
import javax.persistence.JoinColumn;
import javax.persistence.Table;

/**
 *
 * @author Jhonatan
 */
@Entity
@Table(name = "tb_marca_modelo")
public class CarModel {

    @Id
    @Column(name = "id_marca_modelo")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    
    @Column(name = "nm_modelo")
    private String nameModel;
    
    @ManyToOne
    @JoinColumn(name = "id_marca")
    private CarMark carMark;

    /**
     * @return the id
     */
    public int getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(int id) {
        this.id = id;
    }

    
}
