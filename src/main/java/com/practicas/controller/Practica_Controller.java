package com.practicas.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller

public class Practica_Controller {
    
	@GetMapping ("/cargarLogin")
	
	public String abrirLogin() {
		return "login";
	}
	
	@PostMapping("/login")
	public String ValidarAcceso(@RequestParam("txt_Usuario") String usuario,
			                    @RequestParam("txt_Contraseña") String contraseña,
			                    Model model) 
	{		
		
		
		if(usuario.equals("razemaster426@gmail.com") && contraseña.equals("raze1542")) {
			model.addAttribute("Mensaje","Bienvenido : " + usuario );
			model.addAttribute("cssMensaje","alert alert-success");
		}
		
		else {
			model.addAttribute("Mensaje","Usuario o Clave Incorrecto" );
			model.addAttribute("cssMensaje","alert alert-danger");
		}
		
		return "login";
	}
	
	
}
