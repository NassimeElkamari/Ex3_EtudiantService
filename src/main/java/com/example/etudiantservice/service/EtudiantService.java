package com.example.etudiantservice.service;

import java.util.List;

import com.example.etudiantservice.dto.EtudiantDetailsDto;
import com.example.etudiantservice.dto.RequestDtoEtudiant;
import com.example.etudiantservice.dto.ResponseDtoEtudiant;

public interface EtudiantService {
    ResponseDtoEtudiant save(RequestDtoEtudiant dto);
    List<ResponseDtoEtudiant> findAll();
    EtudiantDetailsDto findById(Long id);
    ResponseDtoEtudiant update(Long id, RequestDtoEtudiant dto);
    void delete(Long id);
}
