package com.example.test1.modele.Entity;


import javax.persistence.*;
import java.util.List;

@Entity(name = "localisation")
@Table(name = "localisation")
public class Localisation {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "idL")
    private Long idL;
    @Column(name = "nomL")
    private String nomL;
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "localisations")
    //un restaurant peut avoir plusieur commandes
    private List<Restaurant> restaurant;

    public Localisation() {
    }

    public Localisation(String nomL) {
        this.nomL = nomL;
    }

    public Long getIdL() {
        return idL;
    }

    public void setIdL(Long idL) {
        this.idL = idL;
    }

    public String getNomL() {
        return nomL;
    }

    public void setNomL(String nomL) {
        this.nomL = nomL;
    }

    public List<Restaurant> getRestaurant() {
        return restaurant;
    }

    public void setRestaurant(List<Restaurant> restaurant) {
        this.restaurant = restaurant;
    }
}
