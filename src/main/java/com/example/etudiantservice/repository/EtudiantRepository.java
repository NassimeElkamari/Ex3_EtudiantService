package com.example.etudiantservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.etudiantservice.entitie.Etudiant;

public interface EtudiantRepository extends JpaRepository<Etudiant, Long> {
}
