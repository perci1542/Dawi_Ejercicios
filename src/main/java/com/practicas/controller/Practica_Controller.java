package com.practicas.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller

public class Practica_Controller {
    
	@GetMapping ("/cargarLogin")
	
	public String abrirLogin() {
		return "login";
	}
	
}
