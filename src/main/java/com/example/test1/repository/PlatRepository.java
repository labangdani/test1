package com.example.test1.repository;


import com.example.test1.modele.Entity.Plat;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface PlatRepository extends JpaRepository<Plat, Long> {
    public Plat findAllByNomP(String nomP);
    public Plat findByIdplat(Long idplat);

    /*@Query(value="select p from plat p where p.idresto=?1")
    public List<Plat> rechercherplats(Long idresto);
*/
    void deleteByIdplat(Long idplat);
}
