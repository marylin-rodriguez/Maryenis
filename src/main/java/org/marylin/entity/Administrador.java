package org.marylin.entity;

import jakarta.persistence.*;

import javax.annotation.processing.Generated;

@Entity
@Table(name = "Administrador")
public class Administrador {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
            @Column(name = "id")
            private int id;

            @Column(name = "primer_nombre")
            private String primerNombre;

            @Column(name = "apellido")
            private String apellido;

            @Column(name = "direccion")
            private String direccion;

            @Column(name = "telefono")
            private  String telefono;


            public Administrador(){

            }


    public Administrador(String primerNombre, String apellido, String direccion, String telefono) {
        this.primerNombre = primerNombre;
        this.apellido = apellido;
        this.direccion = direccion;
        this.telefono = telefono;
    }

        public String getPrimerNombre() {
            return primerNombre;
        }

        public void setPrimerNombre(String primerNombre) {
            this.primerNombre = primerNombre;
        }

        public String getApellido() {
            return apellido;
        }

        public void setApellido(String apellido) {
            this.apellido = apellido;
        }

        public String getDireccion() {
            return direccion;
        }

        public void setDireccion(String direccion) {
            this.direccion = direccion;
        }

        public String getTelefono() {return telefono;


    }

    @Override
    public String toString() {
        return "Administrador{" +
                "id=" + id +
                ", primerNombre='" + primerNombre + '\'' +
                ", apellido='" + apellido + '\'' +
                ", direccion='" + direccion + '\'' +
                ", telefono='" + telefono + '\'' +
                '}';
    }


}





