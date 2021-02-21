package com.example.test1.modele.Entity;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;

@Entity(name="lignecommande")
@Table(name= "lignecommande")
public class Lignecommande {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long id;
    @Column(name = "qty")
    private int qty;
    @ManyToOne(fetch = FetchType.EAGER, optional = true)
    @JoinColumn(name = "plat_id", nullable = false)
    @OnDelete(action = OnDeleteAction.NO_ACTION)
    private Plat plat;
    @ManyToOne(fetch = FetchType.EAGER, optional = true)
    @JoinColumn(name = "com_id", nullable = false)
    @OnDelete(action = OnDeleteAction.NO_ACTION)
    private Commande commande;

    public Lignecommande() {
    }

    public Lignecommande(int qty) {
        this.qty = qty;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getQty() {
        return qty;
    }

    public void setQty(int qty) {
        this.qty = qty;
    }

    public Plat getPlat() {
        return plat;
    }

    public void setPlat(Plat plat) {
        this.plat = plat;
    }

    public Commande getCommande() {
        return commande;
    }

    public void setCommande(Commande commande) {
        this.commande = commande;
    }
}
