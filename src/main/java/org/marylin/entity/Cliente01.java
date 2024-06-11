package org.marylin.entity;

import jakarta.persistence.*;

@Entity
    @Table(name = "Cliente01")
    public class Cliente01 {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column (name = "id")
    private int id;

    @Column(name = "Nombre_Cliente")
    private String Nombre_Cliente;




}

