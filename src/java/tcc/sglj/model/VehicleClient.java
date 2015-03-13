/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package tcc.sglj.model;

import javax.persistence.CascadeType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Entity;
import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

/**
 *
 * @author Leandro
 */
@Entity
@Table(name = "tb_veiculo_cliente")
public class VehicleClient {
    @Id
    @Column(name = "id_veiculo_cliente")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idVehicle;

    @ManyToOne
    @JoinColumn(name = "id_usuario")
    private User idUser;

    @Column(name = "placa_veiculo")
    private String vehicleBoard;

    @Column(name = "cor")
    private String color;

    @Column(name = "modelo_veiculo")
    private String vehicleModel;
    
    @ManyToOne
    @JoinColumn(name = "id_marca")
    private CarMark idMark;

    @Column(name = "status")
    private Boolean status;

    /**
     * @return the idVehicle
     */
    public int getIdVehicle() {
        return idVehicle;
    }

    /**
     * @param idVehicle the idVehicle to set
     */
    public void setIdVehicle(int idVehicle) {
        this.idVehicle = idVehicle;
    }

    /**
     * @return the idUser
     */
    public User getIdUser() {
        return idUser;
    }

    /**
     * @param idUser the idUser to set
     */
    public void setIdUser(User idUser) {
        this.idUser = idUser;
    }

    /**
     * @return the vehicleBoard
     */
    public String getVehicleBoard() {
        return vehicleBoard;
    }

    /**
     * @param vehicleBoard the vehicleBoard to set
     */
    public void setVehicleBoard(String vehicleBoard) {
        this.vehicleBoard = vehicleBoard;
    }

    /**
     * @return the color
     */
    public String getColor() {
        return color;
    }

    /**
     * @param color the color to set
     */
    public void setColor(String color) {
        this.color = color;
    }

    /**
     * @return the vehicleModel
     */
    public String getVehicleModel() {
        return vehicleModel;
    }

    /**
     * @param vehicleModel the vehicleModel to set
     */
    public void setVehicleModel(String vehicleModel) {
        this.vehicleModel = vehicleModel;
    }

    /**
     * @return the idMark
     */
    public CarMark getIdMark() {
        return idMark;
    }

    /**
     * @param idMark the idMark to set
     */
    public void setIdMark(CarMark idMark) {
        this.idMark = idMark;
    }

    /**
     * @return the status
     */
    public Boolean getStatus() {
        return status;
    }

    /**
     * @param status the status to set
     */
    public void setStatus(Boolean status) {
        this.status = status;
    }

   

}
