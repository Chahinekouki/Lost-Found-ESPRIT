package tn.esprit.lostandfound.entity;

import javax.persistence.*;
import java.sql.Date;

@Entity
public class ObjetPerdu {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nom;

    private String description;

    private String etat;

    private String type;

    private Date date;

    @OneToOne
    @JoinColumn(name = "image_id", referencedColumnName = "id")
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

    public ImageModel getImage() {
        return image;
    }

    public void setImage(ImageModel image) {
        this.image = image;
    }

    @Override
    public String toString() {
        return "ObjetPerdu{" +
                "id=" + id +
                ", nom='" + nom + '\'' +
                ", description='" + description + '\'' +
                ", etat='" + etat + '\'' +
                ", type='" + type + '\'' +
                ", date=" + date +
                '}';
    }


}
