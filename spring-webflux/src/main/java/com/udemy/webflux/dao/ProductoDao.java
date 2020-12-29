package com.udemy.webflux.dao;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

import com.udemy.webflux.models.documents.Producto;

public interface ProductoDao extends ReactiveMongoRepository<Producto, String> {

}
