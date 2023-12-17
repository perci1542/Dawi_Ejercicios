package com.practicas.interfaces;

import org.springframework.data.jpa.repository.JpaRepository;

import com.practicas.model.Categoria;

public interface ICategoriaRepository extends JpaRepository<Categoria, Integer> {

}
