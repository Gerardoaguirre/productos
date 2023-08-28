package com.informacionbdi.springboot.app.productos.controllers;

import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.informacionbdi.springboot.app.productos.models.entity.Producto;
import com.informacionbdi.springboot.app.productos.models.service.IProductoService;

@RestController
public class ProductoController {
	
	@Autowired
	private Environment env;
	
	@Value("${server.port}")
	private Integer port;
	
	@Autowired
	private IProductoService productoService;
	
	@GetMapping("/listar")
	public List<Producto> listar(){
		return productoService.findAll().stream().map(producto ->{
			producto.setPort(Integer.parseInt(env.getProperty("local.server.port")));
			//producto.setPort(port);
			return producto;
		}).collect(Collectors.toList());
	}
	
	@GetMapping("/ver/{id}")
	public Producto detalle(@PathVariable Long id) throws InterruptedException {
		if(id.equals(10L)) {
			throw new IllegalStateException("producto no encontrado"); //para simular otro error y probar circuit breaker implementado en servicio item que es quien consume este micro
		}
		if(id.equals(7L)) {
			TimeUnit.SECONDS.sleep(5L);
		}
		Producto producto = productoService.findById(id);
		producto.setPort(Integer.parseInt(env.getProperty("local.server.port")));
		//producto.setPort(port);

		return producto;
	}

}