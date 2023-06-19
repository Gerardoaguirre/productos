package com.informacionbdi.springboot.app.productos.models.dao;

import org.springframework.data.repository.CrudRepository;

import com.informacionbdi.springboot.app.productos.models.entity.Producto;

public interface ProductoDao extends CrudRepository <Producto, Long> {//CRUDREPOSITORY YA INCLULYE METODOS PAARA BUSCAR, BORRRAR, INSERTA
//como ya extiende, este ya es un componente
}
