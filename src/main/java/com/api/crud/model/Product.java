package com.api.crud.model;
import jakarta.persistence.*;

import org.antlr.v4.runtime.misc.NotNull;
import org.apache.juli.logging.Log;
//@entity esta falando para nossa IDE que estamos usando uma anotacao
//especificar a utilidade de algo
//espficiando que essa classe se trata de uma entidade
@Entity(name = "product")
@Table(name = "product")

public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String name;
    private Long price;

    public Product() {
    }

    public Product(Integer id, String name, Long price) {
        this.id = id;
        this.name = name;
        this.price = price;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getPrice() {
        return price;
    }

    public void setPrice(Long price) {
        this.price = price;
    }
}
