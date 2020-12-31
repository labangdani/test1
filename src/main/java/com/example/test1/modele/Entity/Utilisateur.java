package com.example.test1.modele.Entity;

import lombok.Data;

import javax.persistence.*;
import java.util.HashSet;
import java.util.List;
import java.util.Set;


@Entity
@Table(name="utilisateur", uniqueConstraints = {@UniqueConstraint(columnNames = "username"),
        @UniqueConstraint(columnNames = "mailU")})
public class Utilisateur {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "idU")
    private Long idU;
    @Column(name = "username")
    private String username;
    @Column(name = "mailU")
    private String mailU;
    @Column(name = "password")
    private String password;
    @Column(name = "telU")
    private int telU;
    @Column(name = "sexe")
    private String sexe;
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER, mappedBy = "utilisateur")
    //un sommaire peut avoir plusieur matieres
    private List<Commande> commande;
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "user_roles",
            joinColumns = @JoinColumn(name = "idU"),
            inverseJoinColumns = @JoinColumn(name = "idrole"))
    private Set<Role> roles = new HashSet<>();
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "user_resto",
            joinColumns = @JoinColumn(name = "idU"),
            inverseJoinColumns = @JoinColumn(name = "idresto"))
    private Set<Restaurant> restaurants = new HashSet<>();


    public Utilisateur() {
    }

    public Utilisateur(String username, String mailU, String password, int telU, String sexe) {
        this.username = username;
        this.mailU = mailU;
        this.password = password;
        this.telU = telU;
        this.sexe = sexe;
    }


    public Long getIdU() {
        return idU;
    }

    public void setIdU(Long idU) {
        this.idU = idU;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getMailU() {
        return mailU;
    }

    public void setMailU(String mailU) {
        this.mailU = mailU;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getTelU() {
        return telU;
    }

    public void setTelU(int telU) {
        this.telU = telU;
    }

    public String getSexe() {
        return sexe;
    }

    public void setSexe(String sexe) {
        this.sexe = sexe;
    }

    public Set<Role> getRoles() {
        return roles;
    }

    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }

    public Set<Restaurant> getRestaurants() {
        return restaurants;
    }

    public void setRestaurants(Set<Restaurant> restaurants) {
        this.restaurants = restaurants;
    }

    public List<Commande> getCommande() {
        return commande;
    }

    public void setCommande(List<Commande> commande) {
        this.commande = commande;
    }
}
