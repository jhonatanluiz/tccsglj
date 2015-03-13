            /*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package tcc.sglj.model;

import java.util.Date;
import java.util.Collection;

import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Entity;
import javax.persistence.Column;
import javax.persistence.Temporal;
import javax.persistence.OneToMany;
import javax.persistence.ManyToOne;
import javax.persistence.JoinColumn;
import javax.persistence.TemporalType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.UniqueConstraint;

import javax.validation.constraints.NotNull;
import org.hibernate.validator.constraints.Email;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;

import tcc.sglj.util.SystemMessage;

/**
 *
 * @author jhonatan L S Santos
 */
@Entity
@Table(name = "tb_usuario", uniqueConstraints = {
    @UniqueConstraint(columnNames = {"ds_email", "nu_cpf"})
})
public class User {

    @Id
    @Column(name = "id_usuario")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    
    @NotNull(message = "MSG022")    
    @Column(name = "nm_usuario")
    private String name;
    
    @NotNull(message = "MSG023")    
    @Column(name = "nu_cpf")
    private String cpf;
    
    @Email(message = "MSG020" )
    @NotNull(message = "MSG021")
    @Column(name = "ds_email")
    private String email;
    
    @NotNull(message = "MSG024")
    @Column(name = "cd_senha")
    private String password;
    
    @Column(name = "status")
    private boolean status = true;
    
    @Column(name = "dt_cadastro")
    @Temporal(TemporalType.TIMESTAMP)
    private Date sign;
    
    @Column(name = "nu_telefone")
    private String phone;
        
    @OneToMany(cascade = javax.persistence.CascadeType.ALL, mappedBy="user")
    @Cascade(CascadeType.SAVE_UPDATE)
    private Collection<Address> address;

    @NotNull(message = "MSG025")
    @ManyToOne
    @JoinColumn(name = "id_perfil")    
    private Perfil perfil;
        
    @OneToMany(cascade = javax.persistence.CascadeType.ALL, mappedBy="user")
    private Collection<LogAccess> logAccess;
    
    @OneToMany(cascade = javax.persistence.CascadeType.ALL, mappedBy="user")
    private Collection<LostPassword> lostPassword;

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
     * @return the cpf
     */
    public String getCpf() {
        return cpf;
    }

    /**
     * @param cpf the cpf to set
     */
    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    /**
     * @return the email
     */
    public String getEmail() {
        return email;
    }

    /**
     * @param email the email to set
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * @return the password
     */
    public String getPassword() {
        return password;
    }

    /**
     * @param password the password to set
     */
    public void setPassword(String password) {
        this.password = password;
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

    /**
     * @return the sign
     */
    public Date getSign() {
        return sign;
    }

    /**
     * @param sign the sign to set
     */
    public void setSign(Date sign) {
        this.sign = sign;
    }

    /**
     * @return the phone
     */
    public String getPhone() {
        return phone;
    }

    /**
     * @param phone the phone to set
     */
    public void setPhone(String phone) {
        this.phone = phone;
    }

    /**
     * @return the address
     */
    public Collection<Address> getAddress() {
        return address;
    }

    /**
     * @param address the address to set
     */
    public void setAddress(Collection<Address> address) {
        this.address = address;
    }

    /**
     * @return the perfil
     */
    public Perfil getPerfil() {
        return perfil;
    }

    /**
     * @param perfil the perfil to set
     */
    public void setPerfil(Perfil perfil) {
        this.perfil = perfil;
    }

    /**
     * @return the logAccess
     */
    public Collection<LogAccess> getLogAccess() {
        return logAccess;
    }

    /**
     * @param logAccess the logAccess to set
     */
    public void setLogAccess(Collection<LogAccess> logAccess) {
        this.logAccess = logAccess;
    }

    /**
     * @return the lostPassword
     */
    public Collection<LostPassword> getLostPassword() {
        return lostPassword;
    }

    /**
     * @param lostPassword the lostPassword to set
     */
    public void setLostPassword(Collection<LostPassword> lostPassword) {
        this.lostPassword = lostPassword;
    }
    
    public boolean equals(User u) {
        return this.getId() == u.getId();
    }

  
}