package com.practicas.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.practicas.interfaces.ICategoriaRepository;
import com.practicas.interfaces.IProductoRepository;
import com.practicas.model.Producto;

@Controller
public class ProductoController {
	
	@Autowired
	private ICategoriaRepository repCat;
	
	@Autowired
	private IProductoRepository repProd;

	@GetMapping("/cargar")
	public String cargarPagCrud(Model model) {
		
		model.addAttribute("lstCategorias", repCat.findAll());
		model.addAttribute("lstProductos", repProd.findAll());
		model.addAttribute("producto", new Producto());
		
		return "Crud_Productos";
	}
	
	
	@GetMapping("/editar/{id_prod}")
	public String Editar(Model model, @PathVariable String id_prod) {
		
		
		Producto p = repProd.findById(id_prod).get();
		
		model.addAttribute("producto", p);
		
		model.addAttribute("lstCategorias", repCat.findAll());
		model.addAttribute("lstProductos", repProd.findAll());
		
		return "Crud_Productos";
	}
	
	@GetMapping("/eliminar/{id_prod}")
	public String Eliminar(Model model, @PathVariable String id_prod) {
		
		
	    repProd.deleteById(id_prod);
		
		model.addAttribute("producto", new Producto());
		
		model.addAttribute("lstCategorias", repCat.findAll());
		model.addAttribute("lstProductos", repProd.findAll());
		
		return "Crud_Productos";
	}
	
	
	@PostMapping("/grabar")
	public String Grabar (@ModelAttribute Producto producto, Model model) {
		
		try {
			repProd.save(producto);
			model.addAttribute("Mensaje","Registro Ok ");
			model.addAttribute("cssMensaje","alert alert-success");
		}
		
		catch(Exception e) 
		{
			model.addAttribute("Mensaje","Error al Registrar" );
			model.addAttribute("cssMensaje","alert alert-danger");
		}
		
		return "Crud_Productos";
	}
}
