/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package tcc.sglj.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author jhonatan
 */
@Entity
@Table(name = "tb_log_acesso")
public class LogAccess {
    
    @Id
    @Column(name = "id_log_acesso")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "dt_acesso")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dateAccess;
    
    @ManyToOne
    @JoinColumn(name = "id_usuario")
    private User user;

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
     * @return the dateAccess
     */
    public Date getDateAccess() {
        return dateAccess;
    }

    /**
     * @param dateAccess the dateAccess to set
     */
    public void setDateAccess(Date dateAccess) {
        this.dateAccess = dateAccess;
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
}
