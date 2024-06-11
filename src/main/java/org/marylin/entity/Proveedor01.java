package org.marylin.entity;

import jakarta.persistence.*;

    @Entity
    @Table(name = "Proveedor01")
    public class Proveedor01 {

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        @Column(name = "ID")
        private int ID;

        @Column(name = "Nombre")
        private String Nombre;

        @Column(name = "Direccion")
        private String Direccion;

        @Column(name = "Contacto")
        private String Contacto;

        public Proveedor01() {
        }

        public Proveedor01( String nombre, String direccion, String contacto) {
            Nombre = nombre;
            Direccion = direccion;
            Contacto = contacto;
        }

        public int getID() {
            return ID;
        }

        public void setID(int ID) {
            this.ID = ID;
        }

        public String getNombre() {
            return Nombre;
        }

        public void setNombre(String nombre) {
            Nombre = nombre;
        }

        public String getDireccion() {
            return Direccion;
        }

        public void setDireccion(String direccion) {
            Direccion = direccion;
        }

        public String getContacto() {
            return Contacto;
        }

        public void setContacto(String contacto) {
            Contacto = contacto;
        }

        @Override
        public String toString() {
            return "Proveedor01{" +
                    "ID=" + ID +
                    ", Nombre='" + Nombre + '\'' +
                    ", Direccion='" + Direccion + '\'' +
                    ", Contacto='" + Contacto + '\'' +
                    '}';
        }
    }



