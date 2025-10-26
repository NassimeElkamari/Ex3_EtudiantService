package com.example.etudiantservice.dto;

import lombok.*;

@Data
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class FiliereDto {
    private Long idFiliere;
    private String code;
    private String libelle;
}
