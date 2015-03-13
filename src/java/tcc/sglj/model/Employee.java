///*
// * To change this template, choose Tools | Templates
// * and open the template in the editor.
// */
//
package tcc.sglj.model;
//
//
//import java.util.Collection;
//import javax.persistence.CascadeType;
//import javax.persistence.Entity;
//import javax.persistence.ManyToOne;
//import javax.persistence.JoinColumn;
//import javax.persistence.PrimaryKeyJoinColumn;
//import javax.persistence.Column;
//import javax.persistence.OneToMany;
//import javax.persistence.Table;
//
///**
// *
// * @author sled
// */
//
//@Entity
//@Table(name="tb_funcionario")
//@PrimaryKeyJoinColumn(name="id_funcionario")
//public class Employee extends User{
//    
//    @Column(name="nu_pis_pasep", nullable=false)
//    private String pisPasep;
//
//    @Column(name="nu_ctps", nullable=false)
//    private String ctps;
//
//    @ManyToOne
//    @JoinColumn(name = "id_cargo", nullable = false)
//    private EmployeeRole role;
//
//    @OneToMany(cascade = CascadeType.ALL,mappedBy = "employee")
//    private Collection<ServiceOrder> serviceOrder;
//
//    /**
//     * @return the pisPasep
//     */
//    public String getPisPasep() {
//        return pisPasep;
//    }
//
//    /**
//     * @param pisPasep the pisPasep to set
//     */
//    public void setPisPasep(String pisPasep) {
//        this.pisPasep = pisPasep;
//    }
//
//    /**
//     * @return the ctps
//     */
//    public String getCtps() {
//        return ctps;
//    }
//
//    /**
//     * @param ctps the ctps to set
//     */
//    public void setCtps(String ctps) {
//        this.ctps = ctps;
//    }
//
//    /**
//     * @return the role
//     */
//    public EmployeeRole getRole() {
//        return role;
//    }
//
//    /**
//     * @param role the role to set
//     */
//    public void setRole(EmployeeRole role) {
//        this.role = role;
//    }
//
//    /**
//     * @return the serviceOrder
//     */
//    public Collection<ServiceOrder> getServiceOrder() {
//        return serviceOrder;
//    }
//
//    /**
//     * @param serviceOrder the serviceOrder to set
//     */
//    public void setServiceOrder(Collection<ServiceOrder> serviceOrder) {
//        this.serviceOrder = serviceOrder;
//    }
//   
//    
//}


public class Employee {
    private int alguma;
}