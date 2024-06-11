package org.marylin.entity;

import jakarta.persistence.*;
import org.hibernate.boot.registry.selector.spi.StrategyCreator;

@Entity
@Table
public class user {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private Long  id;

    @Column
    private String user;

    @Column
    private String password;

    public user(String user, String password) {
        this.user = user;
        this.password = password;
    }

    public user() {
    }

    public Long getId(){
        return id =  id;
    }

    public String getUser(){
        return user;
    }

    public void setUser(String user){
        this.user = user;
    }

    public String getPassword(){
        return password;
    }

    public void setPassword(String password){
        this.password = password;
    }

}
