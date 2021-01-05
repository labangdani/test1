package com.example.test1.repository;


import com.example.test1.modele.Entity.Commande;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface
CommandeRepository extends JpaRepository<Commande, Long> {


}
