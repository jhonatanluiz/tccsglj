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
 * @author Leandro
 */@Entity
   @Table(name = "tb_ordem_servico")
public class ServiceOrder {
     @Id
     @Column(name = "id_ordem_servico")
     @GeneratedValue(strategy = GenerationType.IDENTITY)
     private int IdService;

     @ManyToOne
     @JoinColumn(name = "id_tp_pagamento")
     private PaymentType PaymentType;

     @ManyToOne
     @JoinColumn(name = "id_tp_servico")
     private Service productType;

     @ManyToOne
     @JoinColumn(name = "id_usuario")
     private User user;

     @ManyToOne
     @JoinColumn(name = "id_funcionario")
     private Employee employee;

     @Column(name = "dt_data")
     @Temporal(TemporalType.DATE)
     private Date DtData;

     @Column(name =  "observacao")
     private String observation;
     
     @Column(name = "pagamento")
     private boolean payment;

    /**
     * @return the IdService
     */
    public int getIdService() {
        return IdService;
    }

    /**
     * @param IdService the IdService to set
     */
    public void setIdService(int IdService) {
        this.IdService = IdService;
    }

    /**
     * @return the PaymentType
     */
    public PaymentType getPaymentType() {
        return PaymentType;
    }

    /**
     * @param PaymentType the PaymentType to set
     */
    public void setPaymentType(PaymentType PaymentType) {
        this.PaymentType = PaymentType;
    }

    /**
     * @return the productType
     */
    public Service getProductType() {
        return productType;
    }

    /**
     * @param productType the productType to set
     */
    public void setProductType(Service productType) {
        this.productType = productType;
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
     * @return the employee
     */
    public Employee getEmployee() {
        return employee;
    }

    /**
     * @param employee the employee to set
     */
    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

    /**
     * @return the DtData
     */
    public Date getDtData() {
        return DtData;
    }

    /**
     * @param DtData the DtData to set
     */
    public void setDtData(Date DtData) {
        this.DtData = DtData;
    }

    /**
     * @return the observation
     */
    public String getObservation() {
        return observation;
    }

    /**
     * @param observation the observation to set
     */
    public void setObservation(String observation) {
        this.observation = observation;
    }

    /**
     * @return the payment
     */
    public boolean isPayment() {
        return payment;
    }

    /**
     * @param payment the payment to set
     */
    public void setPayment(boolean payment) {
        this.payment = payment;
    }

    /**
     * @return the IdService
     */
   


}
