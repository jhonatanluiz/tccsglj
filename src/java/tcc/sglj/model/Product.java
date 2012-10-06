/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package tcc.sglj.model;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Entity;
import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
/**
 *
 * @author Leandro
 */

@Entity
@Table(name = "tb_produto")
public class Product {
    @Id
    @Column(name = "id_produto")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int IdProduct;

    @Column(name ="nm_produto")
    private String Product;

    @Column(name = "nm_fabricante")
    private String Manufacturer;

    @Column(name = "status")
    private Boolean Status;

    /**
     * @return the IdProduct
     */
    public int getIdProduct() {
        return IdProduct;
    }

    /**
     * @param IdProduct the IdProduct to set
     */
    public void setIdProduct(int IdProduct) {
        this.IdProduct = IdProduct;
    }

    /**
     * @return the Product
     */
    public String getProduct() {
        return Product;
    }

    /**
     * @param Product the Product to set
     */
    public void setProduct(String Product) {
        this.Product = Product;
    }

    /**
     * @return the Manufacturer
     */
    public String getManufacturer() {
        return Manufacturer;
    }

    /**
     * @param Manufacturer the Manufacturer to set
     */
    public void setManufacturer(String Manufacturer) {
        this.Manufacturer = Manufacturer;
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
