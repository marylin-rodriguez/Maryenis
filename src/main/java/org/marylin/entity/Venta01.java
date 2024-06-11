package org.marylin.entity;

import jakarta.persistence.*;
import forms.Venta;

@Entity
@Table(name = "Venta")
public class Venta01 extends Venta {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column (name = "id")
    private int id;

    @Column(name = "Fecha")
    private String Fecha;

    @Column(name = "Producto")
    private String Producto;

    @Column(name = "Cantidad")
    private String Cantidad;

    @Column(name = "Precio_Unitario")
    private String Precio_Unitario;

    @Column(name = "Id_Cliente")
    private  int Id_Cliente;

    @Column(name = "Nombre_Cliente")
    private String Nombre_Cliente;




    public Venta01(String producto, int id, String fecha, String cantidad, String precio_Unitario, int id_Cliente, String nombre_Cliente) {
        Producto = producto;
        this.id = id;
        Fecha = fecha;
        Cantidad = cantidad;
        Precio_Unitario = precio_Unitario;
        Id_Cliente = id_Cliente;
        Nombre_Cliente = nombre_Cliente;
    }

    public Venta01(String fecha, String producto, String cantidad, String precioUnitario, String idCliente, String nombreCliente) {

    }

    public Venta01() {

    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setFecha(String fecha) {
        Fecha = fecha;
    }

    public String getProducto() {
        return Producto;
    }

    public void setProducto(String producto) {
        Producto = producto;
    }

    public String getCantidad() {
        return Cantidad;
    }

    public void setCantidad(String cantidad) {
        Cantidad = cantidad;
    }

    public String getPrecio_Unitario() {
        return Precio_Unitario;
    }

    public void setPrecio_Unitario(String precio_Unitario) {
        Precio_Unitario = precio_Unitario;
    }

    public int getId_Cliente() {
        return Id_Cliente;
    }

    public void setId_Cliente(int id_Cliente) {
        Id_Cliente = id_Cliente;
    }

    public String getNombre_Cliente() {
        return Nombre_Cliente;
    }

    public void setNombre_Cliente(String nombre_Cliente) {
        Nombre_Cliente = nombre_Cliente;
    }

    @Override
    public String toString() {
        return "Venta01{" +
                "id=" + id +
                ", Fecha=" + Fecha +
                ", Producto='" + Producto + '\'' +
                ", Cantidad='" + Cantidad + '\'' +
                ", Precio_Unitario='" + Precio_Unitario + '\'' +
                ", Id_Cliente=" + Id_Cliente +
                ", Nombre_Cliente='" + Nombre_Cliente + '\'' +
                '}';
    }

    public char[] getFecha() {
        return new char[0];
    }

    public char[] getPrecioUnitario() {
        return new char[0];
    }

    public char[] getIdCliente() {
        return new char[0];
    }

    public void setNombreCliente(String text) {
    }

    public char[] getNombreCliente() {
        return new char[0];
    }

    public void setPrecioUnitario(String text) {
    }

    public void setIdCliente(String text) {
    }
}
