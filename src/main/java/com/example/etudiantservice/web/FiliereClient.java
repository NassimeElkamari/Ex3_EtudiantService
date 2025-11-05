package com.example.etudiantservice.web;

import com.example.etudiantservice.dto.FiliereDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "filieres", url = "${filiereservice.url}")
public interface FiliereClient {
    @GetMapping("/v2/filieres/{id}")
    FiliereDto getFiliere(@PathVariable("id") Long id);
}
