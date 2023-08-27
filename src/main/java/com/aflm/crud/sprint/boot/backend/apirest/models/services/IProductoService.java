package com.aflm.crud.sprint.boot.backend.apirest.models.services;

import java.util.List;

import com.aflm.crud.sprint.boot.backend.apirest.models.entity.Producto;

public interface IProductoService {
	
	public List<Producto> findAll();
	
	public Producto findById(Long idProducto);
	
	public Producto save(Producto producto);
	
	public void delete(Long idProducto);
	
}
