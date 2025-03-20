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
        info = @Info(title = "Microservicio: Demos",  version = "1.0",
                description = "**Demos** de Microservicios.",
                license = @License(name = "Apache 2.0", url = "https://www.apache.org/licenses/LICENSE-2.0.html"),
                contact = @Contact(name = "Javier Martín", url = "https://github.com/jmagit", email = "support@example.com")
        ),
        externalDocs = @ExternalDocumentation(description = "Documentación del proyecto", url = "https://github.com/jmagit/curso")
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
	

	
//	@Autowired
//	private ActoresService srv;
//	@Autowired
//   private ActoresRepository dao;
	@Autowired
    private IdiomasService dao;
	private void ejemplosDatos() {
		//var actor = new Actor(0,"Pepito","Grillo");
//		var item =dao.findById(204);
//		if(item.isPresent()) {
//			var actor=item.get();
//			actor.setFirstName("Lucas");
//			actor.setLastName(actor.getLastName().toUpperCase());
//			dao.save(actor);
//		}else {System.err.println("No se han encontrado el actor");}
////		System.err.println("Actor:" + item);
////				//dao.save(actor);
		//dao.findAll().forEach(System.err::println);");
//		dao.findTop5ByFirstNameStartingWithOrderByLastNameDesc("P").forEach(System.err::println);
//		dao.findAll((root, query, builder) -> builder.lessThanOrEqualTo(root.get("actorId"), 5))
//		   .forEach(System.err::println);
//		 var actor = new Actor(0, "PEPITO","CaANOVA");
// 		 if(actor.isValid())
// 			 dao.save(actor);
// 		 else {
// 			System.err.println(actor.getErrorsMessage());}
 		 
 	    System.out.println("Imprimiendo todos los idiomas...");
        dao.getAll().forEach(language -> 
            System.out.println("Idioma: " + language.getLanguageId() + " - " + language.getName())
        );
 		 
 		}
//		srv.getAll().forEach(System.err::println);
//		var item =srv.getOne(1);
//		if(item.isPresent()) {
//			var actor =item.get();
//		System.err.println(item +"\nPeliculas");
//		//actor.getFilmActors().forEach(fa -> System.err.println(fa.getFilm().getTitle()));
//		}
//		else {
//		System.err.println("Nose ha encontrado al actor");
//		}}
	
	@Override
	@Transactional
	public void run(String... args) throws Exception {
		System.err.println("Aplicacion arrancada");
		
	
	
		ejemplosDatos();
	}



	

}