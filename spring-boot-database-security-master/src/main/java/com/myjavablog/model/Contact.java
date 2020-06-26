package com.myjavablog.model;


import javax.persistence.*;
import javax.validation.constraints.NotBlank;

@Entity
public class Contact {
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @NotBlank(message = "Nombre es Obligatorio")
    @Column(name = "name")
    private String name;

    @NotBlank(message = "Apellido es Obligatorio")
    @Column(name = "surname")
    private String surname;

    @Column(name = "nameAndSurname")
    private String nameAndSurname;

    @Column(name = "type")
    private String type;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @NotBlank(message = "Informacion es obligatoria")
    @Column(name = "information")
    private String information;

    public String getNameAndSurname() {
        return nameAndSurname;
    }

    public void setNameAndSurname(String nameAndSurname) {
        this.nameAndSurname = nameAndSurname;
    }

    @NotBlank(message = "Descripcion es obligatoria")
    @Column(name = "description")
    private String description;

    public int getUserid() {
        return userid;
    }

    public void setUserid(int userid) {
        this.userid = userid;
    }

    @Column(name = "state")
    private String state;

    @Column(name = "userid")
    private int userid;


    public String getInformation() {
        return information;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public void setInformation(String information) {
        this.information = information;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }



    public Contact() {}

    public Contact(String name, String surname, String type, String description, String information, String state, int userid) {
        this.name = name;
        this.surname = surname;
        this.type = type;
        this.description = description;
        this.information= information;
        this.nameAndSurname = name+" "+surname;
        this.state = state;
        this.userid= userid;
    }

    public void setId(long id) {
        this.id = id;
    }
    
    public long getId() {
        return id;
    }






}