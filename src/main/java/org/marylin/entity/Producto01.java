package org.marylin.entity;

import jakarta.persistence.*;
import forms.Producto;

@Entity
@Table(name = "Producto01")
public class Producto01 extends Producto {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column (name = "idproducto")
    private int idproducto;

    @Column(name = "Nombre")
    private String Nombre;

    @Column(name = "Descripcion")
    private String Descripcion;

    @Column(name = "Precio")
    private double precio;

    @Column(name = "Costo")
    private double Costo;

    @Column(name = "Stock")
    private  int Stock;

    public Producto01() {

    }

    public Producto01(int idproducto, String nombre, String descripcion, double precio, double costo, int stock) {
        this.idproducto = idproducto;
        Nombre = nombre;
        Descripcion = descripcion;
        this.precio = precio;
        Costo = costo;
        Stock = stock;
    }


    public int getidproductod() { return idproducto;}

    public void setidproducto(int idproducto) { this.idproducto = idproducto; }

    public String getNombre() {
        return Nombre;
    }

    public void setNombre(String nombre) {
        Nombre = nombre;
    }

    public String getDescripcion() {
        return Descripcion;
    }

    public void setDescripcion(String descripcion) {
        Descripcion = descripcion;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    public double getCosto() {
        return Costo;
    }

    public void setCosto(double costo) {
        Costo = costo;
    }

    public int getStock() {
        return Stock;
    }

    public void setStock(int stock) {
        Stock = stock;
    }

    @Override
    public String toString() {
        return "Producto01{" +
                "idProducto=" + idproducto +
                ", Nombre=" + Nombre +
                ", Descripcion='" + Descripcion + '\'' +
                ", precio='" + precio + '\'' +
                ", Costo='" + Costo + '\'' +
                ", Stock=" + Stock +
                '}';
    }
}
