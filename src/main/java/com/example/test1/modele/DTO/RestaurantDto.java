package com.example.test1.modele.DTO;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

public class RestaurantDto {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "idresto")
    private Long idresto;
    @Column(name = "nomR")
    private String nomR;
    @Column(name = "localisation")
    private String localisation;
    @Column(name = "image")
    private String image;
    @Column(name = "description")
    private String description;
    @Column(name = "mailR")
    private String mailR;
    @Column(name = "tel")
    private int tel;
    @Column(name = "type")
    private String type;
    @Column(name = "lundi")
    private Boolean lundi;
    @Column(name = "mardi")
    private Boolean mardi;
    @Column(name = "mercredi")
    private Boolean mercredi;
    @Column(name = "jeudi")
    private Boolean jeudi;
    @Column(name = "vendredi")
    private Boolean vendredi;
    @Column(name = "samedi")
    private Boolean samedi;
    @Column(name = "dimanche")
    private Boolean dimanche;
   /* @OneToMany(mappedBy = "restaurant", cascade = CascadeType.ALL)
    @JoinColumn(name = "idcom")
    private Set<Commande> commandes;*/

    public RestaurantDto() {
    }

    public RestaurantDto(String nomR, String localisation, String image, String description, String mailR, int tel, String type, Boolean lundi, Boolean mardi, Boolean mercredi, Boolean jeudi, Boolean vendredi, Boolean samedi, Boolean dimanche) {
        this.nomR = nomR;
        this.localisation = localisation;
        this.image = image;
        this.description = description;
        this.mailR = mailR;
        this.tel = tel;
        this.type = type;
        this.lundi = lundi;
        this.mardi = mardi;
        this.mercredi = mercredi;
        this.jeudi = jeudi;
        this.vendredi = vendredi;
        this.samedi = samedi;
        this.dimanche = dimanche;
    }

    public Long getIdresto() {
        return idresto;
    }

    public void setIdresto(Long idresto) {
        this.idresto = idresto;
    }

    public String getNomR() {
        return nomR;
    }

    public void setNomR(String nomR) {
        this.nomR = nomR;
    }

    public String getLocalisation() {
        return localisation;
    }

    public void setLocalisation(String localisation) {
        this.localisation = localisation;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getMailR() {
        return mailR;
    }

    public void setMailR(String mailR) {
        this.mailR = mailR;
    }

    public int getTel() {
        return tel;
    }

    public void setTel(int tel) {
        this.tel = tel;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Boolean getLundi() {
        return lundi;
    }

    public void setLundi(Boolean lundi) {
        this.lundi = lundi;
    }

    public Boolean getMardi() {
        return mardi;
    }

    public void setMardi(Boolean mardi) {
        this.mardi = mardi;
    }

    public Boolean getMercredi() {
        return mercredi;
    }

    public void setMercredi(Boolean mercredi) {
        this.mercredi = mercredi;
    }

    public Boolean getJeudi() {
        return jeudi;
    }

    public void setJeudi(Boolean jeudi) {
        this.jeudi = jeudi;
    }

    public Boolean getVendredi() {
        return vendredi;
    }

    public void setVendredi(Boolean vendredi) {
        this.vendredi = vendredi;
    }

    public Boolean getSamedi() {
        return samedi;
    }

    public void setSamedi(Boolean samedi) {
        this.samedi = samedi;
    }

    public Boolean getDimanche() {
        return dimanche;
    }

    public void setDimanche(Boolean dimanche) {
        this.dimanche = dimanche;
    }
}