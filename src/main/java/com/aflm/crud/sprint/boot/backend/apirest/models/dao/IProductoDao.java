package com.aflm.crud.sprint.boot.backend.apirest.models.dao;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.aflm.crud.sprint.boot.backend.apirest.models.entity.Producto;

public interface IProductoDao extends CrudRepository<Producto, Long>{
	List<Producto> findAll();
	
//	@Query ("SELECT * FROM ")
//	public List<Producto> findAll(Double precio);
}
