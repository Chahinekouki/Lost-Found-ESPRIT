package tn.esprit.lostandfound.service.dto;


import lombok.Builder;
import lombok.Data;
import tn.esprit.lostandfound.entity.ImageModel;

import java.awt.*;
import java.sql.Date;

@Data
@Builder
public class ObjetPerduDTO {

    private Long id;

    private String nom;

    private String description;

    private String etat;

    private String type;

    private Date date;

    private ImageModel image;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getEtat() {
        return etat;
    }

    public void setEtat(String etat) {
        this.etat = etat;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public ObjetPerduDTO(Long id , String nom, String description, String etat, String type, Date date, ImageModel img) {
        this.id=id;
        this.nom = nom;
        this.description = description;
        this.etat = etat;
        this.type = type;
        this.date = date;
        this.image=img;
    }
}
