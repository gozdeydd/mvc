
package es.mvc.control;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

/**
 *
 * @author Gozde Yazganoglu
 * 
 * Articulos (Clase de Entidad):

Representa la entidad o modelo de objeto de un "Articulo" en la aplicación. 
 Normalmente refleja la estructura de la tabla "Articulos" en la base de datos,
 con atributos como ID, categoría, descripción y precio.
 Esta clase se genera automáticamente por NetBeans en función del 
 esquema de la base de datos y se utiliza para mapear registros de la 
 base de datos a objetos Java y viceversa.
 * 
 */
@Entity
@Table(name = "ARTICULOS")
@NamedQueries({
    @NamedQuery(name = "Articulos.findAll", query = "SELECT a FROM Articulos a")})
public class Articulos implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "IDPROD")
    private Integer idprod;
    @Basic(optional = false)
    @Column(name = "CATEGORIA")
    private String categoria;
    @Basic(optional = false)
    @Column(name = "DESCRIPCION")
    private String descripcion;
    @Basic(optional = false)
    @Column(name = "PRECIO")
    private double precio;

    public Articulos() {
    }

    public Articulos(Integer idprod) {
        this.idprod = idprod;
    }

    public Articulos(Integer idprod, String categoria, String descripcion, double precio) {
        this.idprod = idprod;
        this.categoria = categoria;
        this.descripcion = descripcion;
        this.precio = precio;
    }

    public Integer getIdprod() {
        return idprod;
    }

    public void setIdprod(Integer idprod) {
        this.idprod = idprod;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idprod != null ? idprod.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        
        if (!(object instanceof Articulos)) {
            return false;
        }
        Articulos other = (Articulos) object;
        if ((this.idprod == null && other.idprod != null) || (this.idprod != null && !this.idprod.equals(other.idprod))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "es.mvc.control.Articulos[ idprod=" + idprod + " ]";
    }

}
