package com.practicas.interfaces;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.practicas.model.Usuarios;

public interface IUsuarioRepository extends JpaRepository<Usuarios, Integer>{

	
	Usuarios findByCorreoAndClave(String correo, String clave);
	
	List<Usuarios> findByIdTipoEquals(int idtipo);
}
