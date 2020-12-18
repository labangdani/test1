package com.example.test1.security;

import com.example.test1.modele.DTO.UserDetailsImpl;
import com.example.test1.modele.Entity.Utilisateur;
import com.example.test1.repository.UtilisateurRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {
    @Autowired
    UtilisateurRepository utilisateurRepository;

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException{
        Utilisateur utilisateur = utilisateurRepository.findByUsername(username);
        return UserDetailsImpl.build(utilisateur);
    }
}
