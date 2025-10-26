package com.example.etudiantservice.service;

import com.example.etudiantservice.dto.EtudiantDetailsDto;
import com.example.etudiantservice.dto.FiliereDto;
import com.example.etudiantservice.web.FiliereClient;
import feign.FeignException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

import com.example.etudiantservice.dto.RequestDtoEtudiant;
import com.example.etudiantservice.dto.ResponseDtoEtudiant;
import com.example.etudiantservice.entitie.Etudiant;
import com.example.etudiantservice.mappers.EtudiantMapper;
import com.example.etudiantservice.repository.EtudiantRepository;
import org.springframework.web.server.ResponseStatusException;

@Service
@RequiredArgsConstructor
@Transactional
public class EtudiantServiceImpl implements EtudiantService {
    private final EtudiantRepository repository;
    private final EtudiantMapper mapper;
    private final FiliereClient filiereClient;

    @Override
    public ResponseDtoEtudiant save(RequestDtoEtudiant dto) {

        Etudiant e = mapper.toEntity(dto);

        try {
            filiereClient.getFiliere(e.getIdFiliere());
        } catch (FeignException.NotFound nf) {
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST,
                    "La filière avec id="+e.getIdFiliere()+" n'existe pas"
            );
        }

        return mapper.toDto(repository.save(e));
    }


    @Override
    @Transactional(readOnly = true)
    public List<ResponseDtoEtudiant> findAll() {
        return repository.findAll().stream().map(mapper::toDto).collect(Collectors.toList());
    }

    @Override
    @Transactional(readOnly = true)
    public EtudiantDetailsDto findById(Long id) {
        EtudiantDetailsDto dto = repository.findById(id)
                .map(mapper::toEtudiantDetailsDto)
                .orElseThrow();

        if (dto.getIdFiliere() != null) {
            FiliereDto filiere = filiereClient.getFiliere(dto.getIdFiliere());
            dto.setFiliere(filiere);
        }
        return dto;
    }

    @Override
    public ResponseDtoEtudiant update(Long id, RequestDtoEtudiant req) {
        Etudiant e = repository.findById(id).orElseThrow(() -> new IllegalArgumentException("Etudiant non trouvé"));
        if (req.getNom()!=null) e.setNom(req.getNom());
        if (req.getPrenom()!=null) e.setPrenom(req.getPrenom());
        if (req.getCne()!=null) e.setCne(req.getCne());
        if (req.getIdFiliere()!=null) e.setIdFiliere(req.getIdFiliere());
        return mapper.toDto(repository.save(e));
    }

    @Override
    public void delete(Long id) {
        repository.deleteById(id);
    }


}
