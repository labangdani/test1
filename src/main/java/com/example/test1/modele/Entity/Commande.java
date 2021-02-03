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
    @Column(name = "currency")
    private String currency;
    @Column(name = "intent")
    private String intent;
    @Column(name = "validité")
    private boolean validité = false;
     @Column(name = "heure")
    private String heure;
    @Column(name = "mode")
    private String mode;
    @Column(name = "adresse")
    private String adresse;
    @Column(name = "ville")
    private String ville = "Yaoundé";
    @Column(name = "date")
    private LocalDateTime date;
    @Column(name ="remarque")
    private String remarque;
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

    public Commande(int nbrplat, int total, String currency, boolean validité, String heure, String mode, String intent, String adresse, String ville, LocalDateTime date, String remarque, Utilisateur utilisateur, Restaurant restaurants, Set<Plat> plats) {
        this.nbrplat = nbrplat;
        this.total = total;
        this.currency = currency;
        this.intent = intent;
        this.validité = validité;
        this.heure = heure;
        this.mode = mode;
        this.adresse = adresse;
        this.ville = ville;
        this.date = date;
        this.remarque = remarque;
        this.utilisateur = utilisateur;
        this.restaurants = restaurants;
        this.plats = plats;
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

    public String getCurrency() {
        return currency;
    }

    public String getIntent() {
        return intent;
    }

    public void setIntent(String intent) {
        this.intent = intent;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public boolean isValidité() {
        return validité;
    }

    public void setValidité(boolean validité) {
        this.validité = validité;
    }

    public String getHeure() {
        return heure;
    }

    public void setHeure(String heure) {
        this.heure = heure;
    }

    public String getMode() {
        return mode;
    }

    public void setMode(String mode) {
        this.mode = mode;
    }

    public String getAdresse() {
        return adresse;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    public String getVille() {
        return ville;
    }

    public void setVille(String ville) {
        this.ville = ville;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }

    public String getRemarque() {
        return remarque;
    }

    public void setRemarque(String remarque) {
        this.remarque = remarque;
    }

    public Utilisateur getUtilisateur() {
        return utilisateur;
    }

    public void setUtilisateur(Utilisateur utilisateur) {
        this.utilisateur = utilisateur;
    }

    public Restaurant getRestaurants() {
        return restaurants;
    }

    public void setRestaurants(Restaurant restaurants) {
        this.restaurants = restaurants;
    }

    public Set<Plat> getPlats() {
        return plats;
    }

    public void setPlats(Set<Plat> plats) {
        this.plats = plats;
    }

}