package com.example.etudiantservice.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;


@Getter @Setter @NoArgsConstructor @AllArgsConstructor
public class ResponseDtoEtudiant {
    private Long idEtudiant;
    private String nom;
    private String prenom;
    private String cne;
    private Long idFiliere;

}
