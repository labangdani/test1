package com.example.test1.modele.DTO;


import com.example.test1.modele.Entity.Restaurant;
import com.example.test1.modele.Entity.Utilisateur;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import java.time.LocalDateTime;

public class CommandeDto {
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
    @ManyToOne(fetch = FetchType.LAZY, optional = true)
    @JoinColumn(name = "user_id", nullable = false)
    @OnDelete(action = OnDeleteAction.NO_ACTION)
    private Utilisateur user;
    @ManyToOne(fetch = FetchType.LAZY, optional = true)
    @JoinColumn(name = "resto_id", nullable = false)
    @OnDelete(action = OnDeleteAction.NO_ACTION)
    private Restaurant restaurant;

    public CommandeDto() {
    }

    public CommandeDto(int nbrplat, int total, int fraislivraison, int sousmontant, LocalDateTime date, Utilisateur user, Restaurant restaurant) {
        this.nbrplat = nbrplat;
        this.total = total;
        this.fraislivraison = fraislivraison;
        this.sousmontant = sousmontant;
        this.date = date;
        this.user = user;
        this.restaurant = restaurant;
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
        return user;
    }

    public void setUser(Utilisateur user) {
        this.user = user;
    }

    public Restaurant getRestaurant() {
        return restaurant;
    }

    public void setRestaurant(Restaurant restaurant) {
        this.restaurant = restaurant;
    }

}
