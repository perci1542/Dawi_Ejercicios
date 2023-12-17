package com.practicas.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.practicas.interfaces.IUsuarioRepository;
import com.practicas.model.Usuarios;

@Controller

public class Practica_Controller {
    
	@GetMapping ("/cargarLogin")
	
	public String abrirLogin() {
		return "login";
	}
	
	@Autowired
	private IUsuarioRepository repoUsi;
	
	
	@PostMapping("/login")
	public String ValidarAcceso(@RequestParam("txt_Usuario") String usuario,
			                    @RequestParam("txt_Contraseña") String contraseña,
			                    Model model) 
	{		
		
		Usuarios u = repoUsi.findByCorreoAndClave(usuario, contraseña);
		
		if(u != null ) {
			model.addAttribute("Mensaje","Bienvenido : " + u.getNom_usua() );
			model.addAttribute("cssMensaje","alert alert-success");
			return "redirect:/cargar";
		}
		
		else {
			model.addAttribute("Mensaje","Usuario o Clave Incorrecto" );
			model.addAttribute("cssMensaje","alert alert-danger");
		}
		
		return "login";
	}
	
	
}
