/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package tcc.sglj.model;

import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Entity;
import javax.persistence.Column;
import javax.persistence.GenerationType;
import javax.persistence.GeneratedValue;
import javax.persistence.OneToMany;
import javax.persistence.CascadeType;

import java.util.Collection;

/**
 *
 * @author Jhonatan
 */
@Entity
@Table(name = "tb_estado")
public class State {

    @Id
    @Column(name = "id_estado")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "sg_estado")
    private String uf;

    @Column(name = "nm_estado")
    private String state;

    @OneToMany(cascade = CascadeType.ALL, mappedBy="state")
    private Collection<City> city;

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
     * @return the uf
     */
    public String getUf() {
        return uf;
    }

    /**
     * @param uf the uf to set
     */
    public void setUf(String uf) {
        this.uf = uf;
    }

    /**
     * @return the state
     */
    public String getState() {
        return state;
    }

    /**
     * @param state the state to set
     */
    public void setState(String state) {
        this.state = state;
    }

    /**
     * @return the city
     */
    public Collection<City> getCity() {
        return city;
    }

    /**
     * @param city the city to set
     */
    public void setCity(Collection<City> city) {
        this.city = city;
    }
}