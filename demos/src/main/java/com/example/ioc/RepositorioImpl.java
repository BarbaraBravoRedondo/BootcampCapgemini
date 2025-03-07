package com.example.ioc;

import org.springframework.stereotype.Repository;
import org.springframework.beans.factory.annotation.Qualifier;

@Repository
@Qualifier("verdad")
public class RepositorioImpl implements Repositorio {
	public RepositorioImpl(Configuracion config) {
		// TODO Auto-generated constructor stub
	}
	@Override
	public void guardar() {
		System.err.println("Guardando");
	}
}