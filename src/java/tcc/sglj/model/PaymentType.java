/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package tcc.sglj.model;

import java.util.Collection;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 *
 * @author Leandro
 */
@Entity
@Table(name = "tb_tp_pagamento")
public class PaymentType {
    @Id
    @Column(name = "id_tp_pagamento")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int IdPay;

    @Column(name = "tp_pagamento")
    private String TpPay;

    @OneToMany(cascade = CascadeType.ALL,mappedBy ="PaymentType" )
    private Collection<ServiceOrder> serviceOrder;

    /**
     * @return the IdPay
     */
    public int getIdPay() {
        return IdPay;
    }

    /**
     * @param IdPay the IdPay to set
     */
    public void setIdPay(int IdPay) {
        this.IdPay = IdPay;
    }

    /**
     * @return the TpPay
     */
    public String getTpPay() {
        return TpPay;
    }

    /**
     * @param TpPay the TpPay to set
     */
    public void setTpPay(String TpPay) {
        this.TpPay = TpPay;
    }

    /**
     * @return the serviceOrder
     */
    public Collection<ServiceOrder> getServiceOrder() {
        return serviceOrder;
    }

    /**
     * @param serviceOrder the serviceOrder to set
     */
    public void setServiceOrder(Collection<ServiceOrder> serviceOrder) {
        this.serviceOrder = serviceOrder;
    }

   



}
