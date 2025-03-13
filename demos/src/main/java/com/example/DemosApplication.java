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
import com.example.ioc.Configuracion;
import com.example.ioc.Rango;
import com.example.ioc.Repositorio;
import com.example.ioc.Servicio;
import com.example.util.Calculadora;

import jakarta.transaction.Transactional;

@SpringBootApplication
//@ComponentScan(basePackages = "com.example.ioc")
public class DemosApplication implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(DemosApplication.class, args);
	
		
	}
	
//	@Autowired //(required = false)
//	Servicio srv;
	
	@Autowired //(required = false)
//	@Qualifier("verdad")
	Repositorio repo1;
	@Autowired //(required = false)
//	@Qualifier("mentira")
	Repositorio repo2;
//	@Autowired //(required = false)
//	Repositorio repo;

	@Value("${mi.valor}")
	String valor;
	@Autowired
	Rango rango;
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
		//Servicio srv = new Servicio(new Repositorio(new Configuracion()));
		//AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext();

//		srv.guardar();
//		repo.guardar();
		repo1.guardar();
		repo2.guardar();
		System.err.println("Valor= " + valor);
		ejemplosDatos();
	}
	
	private void ejemplosIOC() {
		//Servicio srv = new Servicio(new Repositorio(new Configuracion()));
		//AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext();

//		srv.guardar();
//		repo.guardar();
		repo1.guardar();
		repo2.guardar();
		System.err.println("Valor: " + valor);

		System.err.println("Rango: " + rango);
	} 
	
	private void ejemplosPruebas() {
	
		var calc = new Calculadora();
		System.err.println("Suma: " + calc.suma(2, 3));
	}
//	@Bean
//  	CommandLineRunner demo() {
//		return (args) -> {
//			System.err.println("Aplicacion arrancadaaaaaaaaaaaaaa");
//		};
//	}
	

}