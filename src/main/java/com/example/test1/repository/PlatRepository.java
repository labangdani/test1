package com.example.test1.repository;


import com.example.test1.modele.Entity.Plat;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface PlatRepository extends JpaRepository<Plat, Long> {
    public Plat findAllByNomP(String nomP);
    public Plat findByIdplat(Long idplat);

    void deleteByIdplat(Long idplat);
}
