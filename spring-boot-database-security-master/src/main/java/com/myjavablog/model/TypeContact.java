package com.myjavablog.model;

public enum TypeContact {


    TELEFONO("TELEFONO"),
    EMAIL("E-MAIL"),
    DIRECCION("DIRECCION"),
    OTRO("OTRO");

    private final String name;

    TypeContact(String name){
        this.name = name;

    }

    public String getName() {
        return name;
    }
}
