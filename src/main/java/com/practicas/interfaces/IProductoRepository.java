package com.practicas.interfaces;

import org.springframework.data.jpa.repository.JpaRepository;

import com.practicas.model.Producto;

public interface IProductoRepository extends JpaRepository <Producto, String>{

}
