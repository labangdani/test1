package com.example.test1.modele.DTO;


import com.example.test1.modele.Entity.Restaurant;

import javax.persistence.*;
import java.util.Set;


    public class UtilisateurDto {
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
        private Set<String> role;
        private Set<String> restaurant;

        public UtilisateurDto() {
        }

        public UtilisateurDto(String username, String mailU, String password, int telU, String sexe) {
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

        public Set<String> getRole() {
            return this.role;
        }

        public void setRole(Set<String> role) {
            this.role = role;
        }

        public Set<String> getRestaurant() {
            return restaurant;
        }

        public void setRestaurant(Set<String> restaurant) {
            this.restaurant = restaurant;
        }
    }
