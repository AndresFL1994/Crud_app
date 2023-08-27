package com.aflm.crud.sprint.boot.backend.apirest.models.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.aflm.crud.sprint.boot.backend.apirest.models.dao.IProductoDao;
import com.aflm.crud.sprint.boot.backend.apirest.models.entity.Producto;

@Service
public class ProductoServiceImpl implements IProductoService {

	@Autowired
	private IProductoDao productoDao;
	@Override
	@Transactional (readOnly = true)
	public List<Producto> findAll() {
		return (List<Producto>) productoDao.findAll();
	}
	
	@Override
	@Transactional (readOnly = true)
	public Producto findById(Long idProducto) {
		return productoDao.findById(idProducto).orElse(null);
	}
	
	@Override
	@Transactional
	public Producto save(Producto producto) {
		return productoDao.save(producto);
	}
	
	@Override
	@Transactional
	public void delete(Long idProducto) {
		productoDao.deleteById(idProducto);	
	}
}
