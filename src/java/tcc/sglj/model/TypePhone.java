/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package tcc.sglj.model;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.CascadeType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Column;

import java.util.Collection;

/**
 *
 * @author Jhonatan L S Santos
 */
@Entity
@Table(name="tb_tp_telefone")
@SuppressWarnings("serial")
public class TypePhone {

    @Id
    @Column(name = "id_tp_telefone")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idTypePhone;

    @Column(name="nm_tipo")
    private String type;

    @OneToMany(cascade = CascadeType.ALL, mappedBy="typePhone")
    private Collection<Phone> phones;

    /**
     * @return the idTypePhone
     */
    public int getIdTypePhone() {
        return idTypePhone;
    }

    /**
     * @param idTypePhone the idTypePhone to set
     */
    public void setIdTypePhone(int idTypePhone) {
        this.idTypePhone = idTypePhone;
    }

    /**
     * @return the type
     */
    public String getType() {
        return type;
    }

    /**
     * @param type the type to set
     */
    public void setType(String type) {
        this.type = type;
    }

    /**
     * @return the phones
     */
    public Collection<Phone> getPhones() {
        return phones;
    }

    /**
     * @param phones the phones to set
     */
    public void setPhones(Collection<Phone> phones) {
        this.phones = phones;
    }
}
