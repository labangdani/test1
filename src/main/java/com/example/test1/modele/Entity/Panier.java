package com.example.test1.modele.Entity;

import javax.persistence.*;

@Entity(name="panier")
@Table(name= "panier")
public class Panier {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "idpanier")
    private Long idpanier;
    @Column(name = "nomplat")
    private String nomplat;
    @Column(name = "prix")
    private int prix;
    @Column(name = "qty")
    private int qty;
    @Column(name = "sousmontant")
    private int sousmontant;

    public Panier() {
    }

    public Panier(String nomplat, int prix, int qty, int sousmontant) {
        this.nomplat = nomplat;
        this.prix = prix;
        this.qty = qty;
        this.sousmontant = sousmontant; 
    }

    public Long getIdpanier() {
        return idpanier;
    }

    public void setIdpanier(Long idpanier) {
        this.idpanier = idpanier;
    }

    public String getNomplat() {
        return nomplat;
    }

    public void setNomplat(String nomplat) {
        this.nomplat = nomplat;
    }

    public int getPrix() {
        return prix;
    }

    public void setPrix(int prix) {
        this.prix = prix;
    }

    public int getQty() {
        return qty;
    }

    public void setQty(int qty) {
        this.qty = qty;
    }

        public int getSousmontant() {
        return sousmontant;
    }

    public void setSousmontant(int sousmontant) {
        this.sousmontant = sousmontant;
    }
}
