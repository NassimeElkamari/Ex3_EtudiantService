package com.example.etudiantservice;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.servers.Server;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;


@EnableFeignClients(basePackages = "com.example.etudiantservice.web")
@SpringBootApplication
public class EtudiantserviceApplication {
    public static void main(String[] args) {
        SpringApplication.run(EtudiantserviceApplication.class, args);
    }
}
