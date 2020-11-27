package com.example.test1.modele.DTO;

import com.example.test1.modele.Entity.Role;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

    @Entity
    @Table(name="utilisateur", uniqueConstraints = {@UniqueConstraint(columnNames = "username"),
            @UniqueConstraint(columnNames = "mailU")})
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
        @Column(name = "adresse")
        private String adresse;
        @Column(name = "sexe")
        private String sexe;
        @ManyToMany(fetch = FetchType.LAZY)
        @JoinTable(name = "user_roles",
                joinColumns = @JoinColumn(name = "idU"),
                inverseJoinColumns = @JoinColumn(name = "idresto"))
        private Set<Role> roles = new HashSet<>();

        public UtilisateurDto() {
        }

        public UtilisateurDto(String username, String mailU, String password, int telU, String adresse, String sexe, Set<Role> roles) {
            this.username = username;
            this.mailU = mailU;
            this.password = password;
            this.telU = telU;
            this.adresse = adresse;
            this.sexe = sexe;
            this.roles = roles;
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

        public String getAdresse() {
            return adresse;
        }

        public void setAdresse(String adresse) {
            this.adresse = adresse;
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
    }
