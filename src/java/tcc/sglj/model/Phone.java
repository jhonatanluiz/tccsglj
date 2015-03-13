/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package tcc.sglj.model;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Id;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Column;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

/**
 *
 * @author Jhonatan L S Santos
 */
@Entity
@Table(name = "tb_telefone")
@SuppressWarnings("serial")
public class Phone {

    @Id
    @Column(name = "id_telefone")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idPhone;

    @ManyToOne
    @JoinColumn(name = "id_tp_telefone", nullable = false)
    private TypePhone typePhone;

    @ManyToOne
    @JoinColumn(name = "id_usuario", nullable = false)
    private User userPhone;
    
    @Column(name = "nu_ddd", nullable = false)
    private int DDD;

    @Column(name = "nu_telefone", nullable = false)
    private String numberPhone;

    @Column(name = "status", nullable = false)
    private boolean status;

    /**
     * @return the idPhone
     */
    public int getIdPhone() {
        return idPhone;
    }

    /**
     * @param idPhone the idPhone to set
     */
    public void setIdPhone(int idPhone) {
        this.idPhone = idPhone;
    }

    /**
     * @return the typePhone
     */
    public TypePhone getTypePhone() {
        return typePhone;
    }

    /**
     * @param typePhone the typePhone to set
     */
    public void setTypePhone(TypePhone typePhone) {
        this.typePhone = typePhone;
    }

    /**
     * @return the userPhone
     */
    public User getUserPhone() {
        return userPhone;
    }

    /**
     * @param userPhone the userPhone to set
     */
    public void setUserPhone(User userPhone) {
        this.userPhone = userPhone;
    }

    /**
     * @return the DDD
     */
    public int getDDD() {
        return DDD;
    }

    /**
     * @param DDD the DDD to set
     */
    public void setDDD(int DDD) {
        this.DDD = DDD;
    }

    /**
     * @return the numberPhone
     */
    public String getNumberPhone() {
        return numberPhone;
    }

    /**
     * @param numberPhone the numberPhone to set
     */
    public void setNumberPhone(String numberPhone) {
        this.numberPhone = numberPhone;
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
