/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package tcc.sglj.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import tcc.sglj.model.User;

/**
 *
 * @author jhonatan
 */
@Entity
@Table(name = "tb_recupera_senha")
public class LostPassword {

    @Id
    @Column(name = "id_recupera_senha")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    
    @Column(name = "ds_chave")
    private String key;
    
    @Column(name = "dt_expiracao")
    @Temporal(TemporalType.DATE)
    private Date dateExpire;
    
    @Column(name = "dt_alterado")
    @Temporal(TemporalType.DATE)
    private Date dateAlter;
    
    @ManyToOne
    @JoinColumn(name = "id_usuario")
    private User user;
    
    @Column(name = "status")
    private boolean active;

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
     * @return the key
     */
    public String getKey() {
        return key;
    }

    /**
     * @param key the key to set
     */
    public void setKey(String key) {
        this.key = key;
    }

    /**
     * @return the dateExpire
     */
    public Date getDateExpire() {
        return dateExpire;
    }

    /**
     * @param dateExpire the dateExpire to set
     */
    public void setDateExpire(Date dateExpire) {
        this.dateExpire = dateExpire;
    }

    /**
     * @return the dateAlter
     */
    public Date getDateAlter() {
        return dateAlter;
    }

    /**
     * @param dateAlter the dateAlter to set
     */
    public void setDateAlter(Date dateAlter) {
        this.dateAlter = dateAlter;
    }

    /**
     * @return the user
     */
    public User getUser() {
        return user;
    }

    /**
     * @param user the user to set
     */
    public void setUser(User user) {
        this.user = user;
    }

    /**
     * @return the active
     */
    public boolean isActive() {
        return active;
    }

    /**
     * @param active the active to set
     */
    public void setActive(boolean active) {
        this.active = active;
    }
}
