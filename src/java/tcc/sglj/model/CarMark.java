/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package tcc.sglj.model;

import java.util.Collection;
import javax.persistence.CascadeType;
import javax.persistence.Id;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 *
 * @author Leandro
 */
@Entity
@Table(name = "tb_marca")
public class CarMark {

    @Id
    @Column(name = "id_marca")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    
    @Column(name = "nm_marca")
    private String nameMark;
    
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "carMark")
    private Collection<CarModel> carModels;

    @Column(name = "status")
    private boolean status = true;
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

    /**
     * @return the nameMark
     */
    public String getNameMark() {
        return nameMark;
    }

    /**
     * @param nameMark the nameMark to set
     */
    public void setNameMark(String nameMark) {
        this.nameMark = nameMark;
    }

    /**
     * @return the carModels
     */
    public Collection<CarModel> getCarModels() {
        return carModels;
    }

    /**
     * @param carModels the carModels to set
     */
    public void setCarModels(Collection<CarModel> carModels) {
        this.carModels = carModels;
    }

    /**
     * @return the status
     */
    public boolean isStatus() {
        return status;
    }

    /**
     * @param status the status to set
     */
    public void setStatus(boolean status) {
        this.status = status;
    }

}
