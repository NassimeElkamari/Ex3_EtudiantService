package com.example.etudiantservice.mappers;

import com.example.etudiantservice.dto.EtudiantDetailsDto;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;
import com.example.etudiantservice.dto.RequestDtoEtudiant;
import com.example.etudiantservice.dto.ResponseDtoEtudiant;
import com.example.etudiantservice.entitie.Etudiant;

@Component
public class EtudiantMapper {
    public Etudiant toEntity(RequestDtoEtudiant dto) {
        Etudiant e = new Etudiant();
        BeanUtils.copyProperties(dto, e);
        return e;
    }
    
    public ResponseDtoEtudiant toDto(Etudiant e) {
        ResponseDtoEtudiant dto = new ResponseDtoEtudiant();
        BeanUtils.copyProperties(e, dto);

        return dto;
    }
    public EtudiantDetailsDto toEtudiantDetailsDto(Etudiant e) {
        EtudiantDetailsDto dto = new EtudiantDetailsDto();
        BeanUtils.copyProperties(e, dto);
        return dto;
    }
}
