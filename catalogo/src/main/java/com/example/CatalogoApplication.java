package com.example;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;

import com.example.domains.contracts.repositories.ActoresRepository;
import com.example.domains.contracts.services.ActoresService;
import com.example.domains.entities.Actor;


import jakarta.transaction.Transactional;

@SpringBootApplication

public class CatalogoApplication implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(CatalogoApplication.class, args);
	
		
	}
	

	
	@Autowired
	private ActoresService srv;
//	private ActoresRepository dao;
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
		srv.getAll().forEach(System.err::println);
		var item =srv.getOne(1);
		if(item.isPresent()) {
			var actor =item.get();
		System.err.println(item +"\nPeliculas");
		//actor.getFilmActors().forEach(fa -> System.err.println(fa.getFilm().getTitle()));
		}
		else {
		System.err.println("Nose ha encontrado al actor");
		}}
	
	@Override
	@Transactional
	public void run(String... args) throws Exception {
		System.err.println("Aplicacion arrancada");
	
	
		ejemplosDatos();
	}



	

}