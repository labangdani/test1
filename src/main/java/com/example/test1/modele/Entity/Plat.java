package com.example.test1.modele.Entity;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import java.util.Objects;

@Entity
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

    public Plat() {
    }

    public Plat(String nomP, int prix, String description, String image, Restaurant restaurant) {
        this.nomP = nomP;
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

    public Restaurant getRestaurant() {
        return restaurant;
    }

    public void setRestaurant(Restaurant restaurant) {
        this.restaurant = restaurant;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Plat)) return false;
        Plat plat = (Plat) o;
        return getPrix() == plat.getPrix() &&
                Objects.equals(getIdplat(), plat.getIdplat()) &&
                Objects.equals(getNomP(), plat.getNomP()) &&
                Objects.equals(getDescription(), plat.getDescription()) &&
                Objects.equals(getImage(), plat.getImage()) &&
                Objects.equals(getRestaurant(), plat.getRestaurant());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getIdplat(), getNomP(), getPrix(), getDescription(), getImage(), getRestaurant());
    }

    @Override
    public String toString() {
        return "Plat{" +
                "idplat=" + idplat +
                ", nomP='" + nomP + '\'' +
                ", prix=" + prix +
                ", description='" + description + '\'' +
                ", image='" + image + '\'' +
                ", restaurant=" + restaurant +
                '}';
    }
}
