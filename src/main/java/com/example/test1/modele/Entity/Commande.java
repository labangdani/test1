package com.example.test1.modele.Entity;


import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name="commande")
public class Commande {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "idcom")
    private Long idcom;
    @Column(name = "nbrplat")
    private int nbrplat;
    @Column(name = "total")
    private int total;
    @Column(name = "fraislivraison")
    private int fraislivraison;
    @Column(name = "sousmontant")
    private int sousmontant;
    @Column(name = "date")
    private LocalDateTime date;
    @ManyToOne(fetch = FetchType.EAGER, optional = true)
    @JoinColumn(name = "user_id", nullable = false)
    @OnDelete(action = OnDeleteAction.NO_ACTION)
    private Utilisateur utilisateur;
    @ManyToOne(fetch = FetchType.EAGER, optional = true)
    @JoinColumn(name = "resto_id", nullable = false)
    @OnDelete(action = OnDeleteAction.NO_ACTION)
    private Restaurant restaurants;
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "plat_commande",
            joinColumns = @JoinColumn(name = "idcom"),
            inverseJoinColumns = @JoinColumn(name = "idplat"))
    private Set<Plat> plats = new HashSet<>();

    public Commande() {
    }

    public Commande(int nbrplat, int total, int fraislivraison, int sousmontant, LocalDateTime date) {
        this.nbrplat = nbrplat;
        this.total = total;
        this.fraislivraison = fraislivraison;
        this.sousmontant = sousmontant;
        this.date = date;
    }

    public Long getIdcom() {
        return idcom;
    }

    public void setIdcom(Long idcom) {
        this.idcom = idcom;
    }

    public int getNbrplat() {
        return nbrplat;
    }

    public void setNbrplat(int nbrplat) {
        this.nbrplat = nbrplat;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public int getFraislivraison() {
        return fraislivraison;
    }

    public void setFraislivraison(int fraislivraison) {
        this.fraislivraison = fraislivraison;
    }

    public int getSousmontant() {
        return sousmontant;
    }

    public void setSousmontant(int sousmontant) {
        this.sousmontant = sousmontant;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }

    public Utilisateur getUser() {
        return utilisateur;
    }

    public void setUser(Utilisateur user) {
        this.utilisateur = user;
    }

    public Restaurant getRestaurant() {
        return restaurants;
    }

    public void setRestaurant(Restaurant restaurant) {
        this.restaurants = restaurant;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Commande)) return false;
        Commande commande = (Commande) o;
        return getNbrplat() == commande.getNbrplat() &&
                getTotal() == commande.getTotal() &&
                getFraislivraison() == commande.getFraislivraison() &&
                getSousmontant() == commande.getSousmontant() &&
                Objects.equals(getIdcom(), commande.getIdcom()) &&
                Objects.equals(getDate(), commande.getDate()) &&
                Objects.equals(getUser(), commande.getUser()) &&
                Objects.equals(getRestaurant(), commande.getRestaurant());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getIdcom(), getNbrplat(), getTotal(), getFraislivraison(), getSousmontant(), getDate(), getUser(), getRestaurant());
    }

    @Override
    public String toString() {
        return "Commande{" +
                "idcom=" + idcom +
                ", nbrplat=" + nbrplat +
                ", total=" + total +
                ", fraislivraison=" + fraislivraison +
                ", sousmontant=" + sousmontant +
                ", date=" + date +
                '}';
    }
}