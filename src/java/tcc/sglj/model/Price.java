/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package tcc.sglj.model;
import javax.persistence.CascadeType;
import javax.persistence.OneToMany;
import java.util.Collection;
import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import javax.persistence.Column;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author Leandro
 */
@Entity
@Table(name = "tb_preco")

public class Price {
    @Id
    @Column(name = "id_preco")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int IdPrice;

    @Column(name = "preco",precision = 2)
    private Double NuPrice;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "Price")
    private Collection < Service> productType ;

    @Column(name = "dt_preco")
    @Temporal(TemporalType.DATE)
    private Date DtPrice;

    @Column(name = "status")
    private Boolean Status;

    /**
     * @return the IdPrice
     */
    public int getIdPrice() {
        return IdPrice;
    }

    /**
     * @param IdPrice the IdPrice to set
     */
    public void setIdPrice(int IdPrice) {
        this.IdPrice = IdPrice;
    }

    /**
     * @return the NuPrice
     */
    public Double getNuPrice() {
        return NuPrice;
    }

    /**
     * @param NuPrice the NuPrice to set
     */
    public void setNuPrice(Double NuPrice) {
        this.NuPrice = NuPrice;
    }

    /**
     * @return the productType
     */
    public Collection<Service> getProductType() {
        return productType;
    }

    /**
     * @param productType the productType to set
     */
    public void setProductType(Collection<Service> productType) {
        this.productType = productType;
    }

    /**
     * @return the DtPrice
     */
    public Date getDtPrice() {
        return DtPrice;
    }

    /**
     * @param DtPrice the DtPrice to set
     */
    public void setDtPrice(Date DtPrice) {
        this.DtPrice = DtPrice;
    }

    /**
     * @return the Status
     */
    public Boolean getStatus() {
        return Status;
    }

    /**
     * @param Status the Status to set
     */
    public void setStatus(Boolean Status) {
        this.Status = Status;
    }

   
  }
