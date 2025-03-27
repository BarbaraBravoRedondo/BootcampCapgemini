package com.example;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;

import java.util.TreeMap;

import org.springdoc.core.customizers.OpenApiCustomizer;

import io.swagger.v3.oas.annotations.ExternalDocumentation;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.info.License;

import com.example.domains.contracts.repositories.ActoresRepository;
import com.example.domains.contracts.services.ActoresService;
import com.example.domains.contracts.services.IdiomasService;
import com.example.domains.entities.Actor;


import jakarta.transaction.Transactional;

@OpenAPIDefinition(
        info = @Info(title = "Microservicio: Catalogo",  version = "1.0",
                description = "Catologo Project",
                license = @License(name = "Apache 2.0", url = "https://www.apache.org/licenses/LICENSE-2.0.html"),
                contact = @Contact(name = "Barbara Bravo", url = "https://github.com/BarbaraBravoRedondo/BootcampCapgemini")
        ),
        externalDocs = @ExternalDocumentation(description = "Documentación del proyecto", url = "https://github.com/BarbaraBravoRedondo/BootcampCapgemini")
)

@SpringBootApplication

public class CatalogoApplication implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(CatalogoApplication.class, args);
	
		
	}
	@Bean
 	OpenApiCustomizer sortSchemasAlphabetically() {
 	    return openApi -> {
 	        var schemas = openApi.getComponents().getSchemas();
 	        openApi.getComponents().setSchemas(new TreeMap<>(schemas));
 	    };
 	}
	

	

	@Autowired
    private IdiomasService dao;
	private void ejemplosDatos() {

 		 
 	    System.out.println("Imprimiendo todos los idiomas...");
        dao.getAll().forEach(language -> 
            System.out.println("Idioma: " + language.getLanguageId() + " - " + language.getName())
        );
 		 
 		}

	
	@Override
	@Transactional
	public void run(String... args) throws Exception {
		System.err.println("Aplicacion arrancada");
		
	
	
		ejemplosDatos();
	}



	

}