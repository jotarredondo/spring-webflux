package com.udemy.webflux;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.udemy.webflux.dao.ProductoDao;
import com.udemy.webflux.models.documents.Producto;

import reactor.core.publisher.Flux;

@SpringBootApplication
public class SpringWebfluxApplication implements CommandLineRunner{
	
	@Autowired
	private ProductoDao dao;
	
	private static final Logger log = LoggerFactory.getLogger(SpringWebfluxApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(SpringWebfluxApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		
		
		Flux.just(new Producto("Audífonos Shure srh750D", 56.230),
				new Producto("Interfaz de Audio Focusrite", 45.000),
				new Producto("Monitor Referencia JBL", 145.000),
				new Producto("Atril Micrófono Samson", 25.000),
				new Producto("Power Mixer JBL 1500", 135.000),
				new Producto("Guitarra Squier Stratocaster", 150.000),
				new Producto("Set Uñeta PickGard", 5.000),
				new Producto("Correa Guitarra Ibanez", 15.000),
				new Producto("Palanca de Trémolo", 8.000)
				)
			.flatMap(producto -> dao.save(producto))
			.subscribe(producto -> log.info("Insert: " + producto.getId() + " " + producto.getNombre()));
		
	}

}
