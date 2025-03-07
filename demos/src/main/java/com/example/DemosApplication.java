package com.example;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.beans.factory.annotation.Qualifier;
//import org.springframework.context.annotation.AnnotationConfigApplicationContext;

//import com.example.ioc.Configuracion;
import com.example.ioc.Repositorio;
import com.example.ioc.Servicio;

@SpringBootApplication
public class DemosApplication implements CommandLineRunner {
 public static void main(String[] args) {
		SpringApplication.run(DemosApplication.class, args);

	}
	
	@Autowired
	@Qualifier("verdad")
	Repositorio repo1;
	@Autowired
	@Qualifier("mentira")
	Repositorio repo2;

	@Override
	public void run(String... args) throws Exception {
		System.err.println("Aplicacion arrancada");
		//Servicio srv = new Servicio(new Repositorio(new Configuracion()));
		//AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext();

		//srv.guardar();
		repo1.guardar();
		repo2.guardar();
	}

	@Bean
  	CommandLineRunner demo() {
		return (args) -> {
			System.err.println("Aplicacion arrancadaaaaaaaaaaaaaa");
		
		};
	}
//	@Bean
//  	CommandLineRunner demo() {
//		return (args) -> {
//			System.err.println("Aplicacion arrancadaaaaaaaaaaaaaa");
//		};
//	}

}