package com.example.test1.modele.DTO;

import com.example.test1.modele.Entity.Restaurant;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;


public class PlatDto {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "idplat")
    private Long idplat;
    @Column(name = "nomP")
    private String nomP;
    @Column(name = "prix")
    private int prix;
    @Column(name = "description")
    private String description;
    @Column(name = "image")
    private String image;
    @ManyToOne(fetch = FetchType.EAGER, optional = true)
    @JoinColumn(name = "idresto", nullable = false)
    @OnDelete(action = OnDeleteAction.NO_ACTION)
    private Restaurant restaurants;

    public PlatDto() {
    }

    public PlatDto(String nomP, int prix, String description) {
        this.nomP = nomP;
        this.prix = prix;
        this.description = description;
    }

    public Long getIdplat() {
        return idplat;
    }

    public void setIdplat(Long idplat) {
        this.idplat = idplat;
    }

    public String getNomP() {
        return nomP;
    }

    public void setNomP(String nomP) {
        this.nomP = nomP;
    }

    public int getPrix() {
        return prix;
    }

    public void setPrix(int prix) {
        this.prix = prix;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public Restaurant getRestaurants() {
        return restaurants;
    }

    public void setRestaurants(Restaurant restaurant) {
        this.restaurants = restaurant;
    }

    @Transient
    public String getPhotosImagePath() {
        if (image == null) return null;

        return "/images/" + image;
    }

    @Override
    public String toString() {
        return "Plat{" +
                "idplat=" + idplat +
                ", nomP='" + nomP + '\'' +
                ", prix=" + prix +
                ", description='" + description + '\'' +
                ", image='" + image + '\'' +
                ", restaurants=" + restaurants +
                '}';
    }

}
