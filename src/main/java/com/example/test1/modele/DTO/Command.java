package com.example.test1.modele.DTO;

import com.example.test1.modele.Entity.Lignecommande;
import com.example.test1.modele.Entity.Restaurant;
import com.example.test1.modele.Entity.Utilisateur;
import lombok.NonNull;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import java.sql.Date;
import java.util.List;

public class Command {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "idcom")
    private Long idcom;
    @Column(name = "total")
    private int total;
    @Column(name = "currency")
    private String currency = "USD";
    @Column(name = "intent")
    private String intent = "sale";
    @Column(name = "validite")
    private boolean validite = false;
    @Column(name = "heure")
    @NonNull
    private String heure;
    @Column(name = "mode")
    @NonNull
    private String mode;
    @Column(name = "adresse")
    @NonNull
    private String adresse;
    @Column(name = "ville")
    private String ville = "Yaoundé";
    @Column(name = "datedujour")
    @NonNull
    private Date datedujour;
    @Column(name ="remarque")
    private String remarque;
    @Column(name ="nom_entreprise")
    private String nom_entreprise;
    @ManyToOne(fetch = FetchType.EAGER, optional = true)
    @JoinColumn(name = "user_id", nullable = false)
    @OnDelete(action = OnDeleteAction.NO_ACTION)
    private Utilisateur utilisateur;
    @ManyToOne(fetch = FetchType.EAGER, optional = true)
    @JoinColumn(name = "resto_id", nullable = false)
    @OnDelete(action = OnDeleteAction.NO_ACTION)
    private Restaurant restaurants;
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "commande")
    //une commande peut avoir plusieurs lignes de commandes
    private List<Lignecommande> lignecommandes;

    public Command() {
    }

    public Command(int total, String currency, String intent, boolean validite, @NonNull String heure, @NonNull String mode, @NonNull String adresse, String ville, @NonNull Date datedujour, String remarque, String nom_entreprise, Utilisateur utilisateur, Restaurant restaurants, List<Lignecommande> lignecommandes) {
        this.total = total;
        this.currency = currency;
        this.intent = intent;
        this.validite = validite;
        this.heure = heure;
        this.mode = mode;
        this.adresse = adresse;
        this.ville = ville;
        this.datedujour = datedujour;
        this.remarque = remarque;
        this.nom_entreprise = nom_entreprise;
        this.utilisateur = utilisateur;
        this.restaurants = restaurants;
        this.lignecommandes = lignecommandes;
    }

    public Long getIdcom() {
        return idcom;
    }

    public void setIdcom(Long idcom) {
        this.idcom = idcom;
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

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public String getIntent() {
        return intent;
    }

    public void setIntent(String intent) {
        this.intent = intent;
    }

    public boolean isValidite() {
        return validite;
    }

    public void setValidite(boolean validite) {
        this.validite = validite;
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

    public Date getDatedujour() {
        return datedujour;
    }

    public void setDatedujour(Date datedujour) {
        this.datedujour = datedujour;
    }

    public String getRemarque() {
        return remarque;
    }

    public void setRemarque(String remarque) {
        this.remarque = remarque;
    }

    public String getNom_entreprise() {
        return nom_entreprise;
    }

    public void setNom_entreprise(String nom_entreprise) {
        this.nom_entreprise = nom_entreprise;
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

    public List<Lignecommande> getLignecommandes() {
        return lignecommandes;
    }

    public void setLignecommandes(List<Lignecommande> lignecommandes) {
        this.lignecommandes = lignecommandes;
    }
}
