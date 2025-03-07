package com.example.ioc;

import org.springframework.stereotype.Repository;
import org.springframework.context.annotation.Primary;
import org.springframework.beans.factory.annotation.Qualifier;


@Repository
@Qualifier("mentira")
@Primary
public class RepositorioImplMock implements Repositorio {
	public RepositorioImplMock(Configuracion config) {
		// TODO Auto-generated constructor stub
	}
	@Override
	public void guardar() {
		System.err.println("Guardando fake");
	}
}