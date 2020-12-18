package com.example.test1.modele.Entity;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import java.util.Optional;

@Entity(name = "plat")
@Table(name = "plat")
public class Plat {
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
    @ManyToOne(fetch = FetchType.LAZY, optional = true)
    @JoinColumn(name = "idresto", nullable = false)
    @OnDelete(action = OnDeleteAction.NO_ACTION)
    private Restaurant restaurants;
    /*@ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
    @JoinTable(name = "plat_commande",
            joinColumns = {
                    @JoinColumn(name = "idplat", referencedColumnName = "id",
                            nullable = false, updatable = false)},
            inverseJoinColumns = {
                    @JoinColumn(name = "idcom", referencedColumnName = "id",
                            nullable = false, updatable = false)})
    private Collection<Commande> commandes;*/

    public Plat() {
    }

    public Plat(String nomP, int prix, String description, String image) {
        this.nomP = nomP;
        this.prix = prix;
        this.description = description;
        this.image = image;
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

}
