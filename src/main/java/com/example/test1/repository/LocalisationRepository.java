package com.example.test1.repository;

import com.example.test1.modele.Entity.Localisation;
import com.example.test1.modele.Entity.Restaurant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface LocalisationRepository extends JpaRepository<Localisation, Long> {
    public Localisation findByIdL(Long idL);

    @Query("select l from localisation l where l.nomL like %?1% order by l.nomL")
    public List<Localisation> listlocalisation(String localisation);

    @Query("select l from localisation l order by l.nomL")
    public List<Localisation> selectall();
}
