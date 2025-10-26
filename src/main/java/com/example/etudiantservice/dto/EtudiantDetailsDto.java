package com.example.etudiantservice.dto;

import lombok.*;

@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class EtudiantDetailsDto {
    private Long idEtudiant;
    private String nom;
    private String prenom;
    private String cne;
    private Long idFiliere;
    private FiliereDto filiere;
}
