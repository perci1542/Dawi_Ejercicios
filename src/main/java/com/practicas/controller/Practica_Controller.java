package com.practicas.controller;

import org.springframework.stereotype.Controller;
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
			                    @RequestParam("txt_Contraseña") String contraseña) 
	{
		
		System.out.println(usuario + " " + contraseña);
		
		return "login";
	}
	
	
}
