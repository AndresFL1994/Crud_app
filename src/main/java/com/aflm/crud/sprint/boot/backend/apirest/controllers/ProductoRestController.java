package com.aflm.crud.sprint.boot.backend.apirest.controllers;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.aflm.crud.sprint.boot.backend.apirest.models.entity.Producto;
import com.aflm.crud.sprint.boot.backend.apirest.models.services.IProductoService;

@CrossOrigin
@RestController
@RequestMapping("/api")
public class ProductoRestController {
	
	@Autowired
	private IProductoService productoService;

	@GetMapping("/productos")
	public List<Producto> index(){
		return productoService.findAll();
	}
	
	@GetMapping("/productos/{idProducto}")
	public Producto show(@PathVariable Long idProducto) {
		return productoService.findById(idProducto);
	}
	
	@PostMapping("/productos")
	@ResponseStatus(HttpStatus.CREATED)
	public Producto create(@RequestBody Producto producto) {
		Double valorTotal = valorTotal(producto.getPrecio(), producto.getCantidad());
		producto.setValorTotal(valorTotal);
		
		return productoService.save(producto);
	}
	
	@PutMapping("/productos/{idProducto}")
	@ResponseStatus(HttpStatus.CREATED)
	public Producto update(@RequestBody Producto producto, @PathVariable Long idProducto) {
		
		Producto productoActual = productoService.findById(idProducto);
		productoActual.setNombre(producto.getNombre());
		productoActual.setDescripcion(producto.getDescripcion());
		productoActual.setPrecio(producto.getPrecio());
		productoActual.setCantidad(producto.getCantidad());
		
		Double valorTotal = valorTotal(producto.getPrecio(), producto.getCantidad());
		productoActual.setValorTotal(valorTotal);
		
		return productoService.save(productoActual);
	}
	
	@DeleteMapping("/productos/{idProducto}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void delete(@PathVariable Long idProducto) {
		productoService.delete(idProducto);
	}
	
	@GetMapping("/findPrecio/{precio}")
	public String combinacionPrecio(@PathVariable Double precio) {
		
		List<Producto> producto = index();
		
		List<String> combinacionProductos = new ArrayList<>() ;
		String nombre = "";
		Double combiPrecio = 0.0;		
		
		for(Producto product : producto) {
			combiPrecio = product.getPrecio()+ combiPrecio;
			if(combiPrecio <= precio ) {				
				if(nombre=="") {
					nombre = product.getNombre();
				}else {
					nombre = product.getNombre()+", "+nombre;
					combinacionProductos.add("["+nombre+"]");
					combiPrecio = 0.0;
					
				}
			}
			
		}
		return combinacionProductos.toString();
	}
	
	public Double valorTotal(Double precio, Integer cantidad) {
		Double valorTotal = precio*cantidad;
		return valorTotal;
	}
}
