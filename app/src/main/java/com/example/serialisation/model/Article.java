package com.example.serialisation.model;

import java.io.Serializable;

import lombok.Data;

@Data
public class Article implements Serializable {
    Integer code;
    String  libelle;
    Double  pu;

    public Article(Integer code, String libelle, Double pu) {
        super();
        this.code = code;
        this.libelle = libelle;
        this.pu = pu;
    }
}
