/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package tcc.sglj.model;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;

/**
 *
 * @author Leandro Ferreira Lima
 * @author Jhonatan L S Santos
 * 
 */
@Entity
@Table (name = "tb_servico_c")
public class Service {
    @Id
    @Column(name = "id_servico")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
        
    @Column(name = "no_servico")
    private String name;
    
    @Column(name = "ds_servico")
    private String description;
    
    @Column(name = "nu_preco")
    private double price;
    
    @Column(name = "status")
    private Boolean status = true;

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
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * @param name the name to set
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return the description
     */
    public String getDescription() {
        return description;
    }

    /**
     * @param description the description to set
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * @return the price
     */
    public double getPrice() {
        return price;
    }

    /**
     * @param price the price to set
     */
    public void setPrice(double price) {
        this.price = price;
    }
    /**
     * @return the Status
     */
    public Boolean getStatus() {
        return status;
    }

    /**
     * @param Status the Status to set
     */
    public void setStatus(Boolean status) {
        this.status = status;
    }

}