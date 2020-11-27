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
    @Column(name = "nomR")
    private String nomR;
    @Column(name = "prix")
    private int prix;
    @Column(name = "description")
    private String description;
    @Column(name = "image")
    private String image;
    @ManyToOne(fetch = FetchType.LAZY, optional = true)
    @JoinColumn(name = "idresto", nullable = false)
    @OnDelete(action = OnDeleteAction.NO_ACTION)
    private Restaurant restaurant;
    /*@ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
    @JoinTable(name = "plat_commande",
            joinColumns = {
                    @JoinColumn(name = "idplat", referencedColumnName = "id",
                            nullable = false, updatable = false)},
            inverseJoinColumns = {
                    @JoinColumn(name = "idcom", referencedColumnName = "id",
                            nullable = false, updatable = false)})
    private Collection<Commande> commandes;*/

    public PlatDto() {
    }

    public PlatDto(String nomR, int prix, String description, String image, Restaurant restaurant) {
        this.nomR = nomR;
        this.prix = prix;
        this.description = description;
        this.image = image;
        this.restaurant = restaurant;
    }

    public Long getIdplat() {
        return idplat;
    }

    public void setIdplat(Long idplat) {
        this.idplat = idplat;
    }

    public String getNomR() {
        return nomR;
    }

    public void setNomR(String nomR) {
        this.nomR = nomR;
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

    public Restaurant getRestaurant() {
        return restaurant;
    }

    public void setRestaurant(Restaurant restaurant) {
        this.restaurant = restaurant;
    }
}
