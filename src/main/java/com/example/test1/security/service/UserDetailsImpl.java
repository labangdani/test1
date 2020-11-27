package com.example.test1.security.service;

import com.example.test1.modele.Entity.Utilisateur;
import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class UserDetailsImpl implements UserDetails {
    private static final long serialVersionUID = 1L;

    private Long idU;

    private String username;

    private String mailU;

    @JsonIgnore
    private String password;

    private Collection<? extends GrantedAuthority> authorities;

    public UserDetailsImpl() {
    }

    public UserDetailsImpl(Long idU, String username, String mailU, String password, Collection<? extends GrantedAuthority> authorities){
        this.idU= idU;
        this.username= username;
        this.mailU= mailU;
        this.password= password;
        this.authorities= authorities;
    }

    public static UserDetailsImpl build(Utilisateur utilisateur){
        List<GrantedAuthority> authorities = utilisateur.getRoles().stream()
                .map(role -> new SimpleGrantedAuthority(role.getName().name()))
                .collect(Collectors.toList());

        return new UserDetailsImpl(
                utilisateur.getIdU(),
                utilisateur.getUsername(),
                utilisateur.getMailU(),
                utilisateur.getPassword(),
                authorities);
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    public Long getIdU(){
        return idU;
    }

    public String getMailU(){
        return mailU;
    }

    public String getPassword(){
        return password;
    }

    @Override
    public String getUsername(){
        return username;
    }

    @Override
    public boolean isAccountNonExpired(){
        return true;
    }


    @Override
    public boolean isAccountNonLocked(){
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired(){
        return true;
    }

    @Override
    public boolean isEnabled(){
        return true;
    }

    @Override
    public boolean equals(Object o){
        if(this == o)
            return true;
        if(o == null || getClass() != o.getClass())
            return false;
        UserDetailsImpl utilisateur = (UserDetailsImpl) o;
        return Objects.equals(idU, utilisateur.idU);
    }
}
