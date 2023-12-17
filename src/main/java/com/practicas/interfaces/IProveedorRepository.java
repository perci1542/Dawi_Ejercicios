package com.practicas.interfaces;

import org.springframework.data.jpa.repository.JpaRepository;

import com.practicas.model.Proveedor;

public interface IProveedorRepository extends JpaRepository <Proveedor, Integer> {

}
