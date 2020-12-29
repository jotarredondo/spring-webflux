package com.udemy.webflux.controller;

import java.time.Duration;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.thymeleaf.spring5.context.webflux.ReactiveDataDriverContextVariable;

import com.udemy.webflux.dao.ProductoDao;
import com.udemy.webflux.models.documents.Producto;

import reactor.core.publisher.Flux;

@Controller
public class ProductoController {
	
	@Autowired
	private ProductoDao dao;
	
	private static final Logger log = LoggerFactory.getLogger(ProductoController.class);
	
	@GetMapping({"/listar", "/"})
	public String listar(Model model) {
		
		Flux<Producto> productos = dao.findAll().map(producto -> {
			producto.setNombre(producto.getNombre().toUpperCase());
			return producto;
		});
		
		productos.subscribe(prod -> log.info(prod.getNombre()));
		
		model.addAttribute("productos", productos);
		model.addAttribute("titulo", "Lista de Productos");
		return "listar";
		
	}
	
	@GetMapping("/listar-datadriver")
	public String listarDataDriver (Model model) {
		
		Flux<Producto> productos = dao.findAll().map(producto -> {
			producto.setNombre(producto.getNombre().toUpperCase());
			return producto;
		}).delayElements(Duration.ofSeconds(1));
		
		productos.subscribe(prod -> log.info(prod.getNombre()));
		
		model.addAttribute("productos", new ReactiveDataDriverContextVariable(productos, 1));
		model.addAttribute("titulo", "Lista de Productos");
		return "listar";
		
	}

}