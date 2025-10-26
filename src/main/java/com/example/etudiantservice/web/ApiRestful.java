package com.example.etudiantservice.web;

import com.example.etudiantservice.dto.EtudiantDetailsDto;
import com.example.etudiantservice.dto.RequestDtoEtudiant;
import com.example.etudiantservice.dto.ResponseDtoEtudiant;
import com.example.etudiantservice.service.EtudiantService;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.servers.Server;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@OpenAPIDefinition(
        info = @Info(
                title = "Etudiant Service API",
                version = "1.0.0",
                description = "CRUD des étudiants + enrichissement filière via Feign"
        ),
        servers = @Server(url = "http://localhost:8082/")
)

@RestController
@RequestMapping("/v1/etudiants")
@RequiredArgsConstructor
public class ApiRestful {

    private final EtudiantService service;

    @Operation(
            summary = "Créer un étudiant",
            description = "Crée un nouvel étudiant avec nom, prénom, CNE et idFiliere",
            responses = {
                    @ApiResponse(responseCode = "201", description = "Étudiant créé",
                            content = @Content(mediaType = "application/json",
                                    schema = @Schema(implementation = ResponseDtoEtudiant.class))),
                    @ApiResponse(responseCode = "400", description = "Requête invalide")
            }
    )
    @PostMapping
    public ResponseEntity<Map<String, Object>> create(@Valid @RequestBody RequestDtoEtudiant dto) {
        ResponseDtoEtudiant saved = service.save(dto);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(Map.of(
                        "message", "Étudiant créé avec succès ✅",
                        "data", saved
                ));
    }


    @Operation(
            summary = "Lister les étudiants",
            description = "Retourne tous les étudiants enregistrés"
    )
    @GetMapping
    public ResponseEntity<Map<String, Object>> all() {
        List<ResponseDtoEtudiant> list = service.findAll();
        return ResponseEntity.ok(Map.of(
                "message", "Liste des étudiants récupérée avec succès ✅",
                "data", list
        ));
    }


    @Operation(
            summary = "Obtenir un étudiant par id",
            description = "Retourne un étudiant enrichi avec sa filière (si existante)"
    )
    @GetMapping(value = "/{id}")
    public ResponseEntity<EtudiantDetailsDto> byId(@PathVariable Long id) {
        return ResponseEntity.ok(service.findById(id));
    }


    @Operation(
            summary = "Mettre à jour un étudiant",
            description = "Modifie les informations d'un étudiant existant"
    )
    @PutMapping(value = "/{id}")
    public ResponseEntity<Map<String, Object>> update(@PathVariable Long id, @Valid @RequestBody RequestDtoEtudiant dto) {
        ResponseDtoEtudiant updated = service.update(id, dto);
        return ResponseEntity.ok(Map.of(
                "message", "Étudiant mis à jour avec succès ✅",
                "data", updated
        ));
    }


    @Operation(
            summary = "Supprimer un étudiant",
            description = "Supprime définitivement un étudiant par son identifiant"
    )
    @DeleteMapping("/{id}")
    public ResponseEntity<Map<String, Object>> delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.ok(Map.of(
                "message", "Étudiant supprimé avec succès ✅"
        ));
    }
}
