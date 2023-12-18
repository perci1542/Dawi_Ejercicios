package com.practicas.controller;

import java.io.OutputStream;
import java.util.HashMap;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.practicas.interfaces.ICategoriaRepository;
import com.practicas.interfaces.IProductoRepository;
import com.practicas.model.Producto;

import jakarta.servlet.http.HttpServletResponse;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;

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
	public String Grabar (@ModelAttribute Producto producto, Model model, @RequestParam("btn_Opcion") String opcion, RedirectAttributes atributos ) {
		
		if(opcion.equals("fil")) {
			atributos.addAttribute("producto", producto);
			return "redirect:/filtros";
		} 
		
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
	
	@GetMapping("/enviar")
	public String Obtener(Model model) {
		model.addAttribute("lstCategorias", repCat.findAll());
		model.addAttribute("lstProductos", repProd.findAll());
		
		return "Crud_Productos";
	}
	
	@Autowired
	private DataSource dataSource; // javax.sql
	@Autowired
	private ResourceLoader resourceLoader; // core.io
	@GetMapping("/reportes")
	public void reportes(HttpServletResponse response) {
		//response.setHeader("Content-Disposition", "attachment; filename=\"reporte.pdf\";");
		response.setHeader("Content-Disposition", "inline;");
		response.setContentType("application/pdf");
		try {
			String ru = resourceLoader.getResource("classpath:prueba02.jasper").getURI().getPath();
			JasperPrint jasperPrint = JasperFillManager.fillReport(ru, null, dataSource.getConnection());
			OutputStream outStream = response.getOutputStream();
			JasperExportManager.exportReportToPdfStream(jasperPrint, outStream);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@GetMapping("/graficos")
	public void graficos(HttpServletResponse response) {
		//response.setHeader("Content-Disposition", "attachment; filename=\"reporte.pdf\";");
		response.setHeader("Content-Disposition", "inline;");
		response.setContentType("application/pdf");
		try {
			String ru = resourceLoader.getResource("classpath:grafico02.jasper").getURI().getPath();
			JasperPrint jasperPrint = JasperFillManager.fillReport(ru, null, dataSource.getConnection());
			OutputStream outStream = response.getOutputStream();
			JasperExportManager.exportReportToPdfStream(jasperPrint, outStream);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
	@GetMapping("/filtros")
	public void Reportefiltros(HttpServletResponse response, @ModelAttribute Producto producto) {
		//response.setHeader("Content-Disposition", "attachment; filename=\"reporte.pdf\";");
		response.setHeader("Content-Disposition", "inline;");
		response.setContentType("application/pdf");
		try {
			
			HashMap<String, Object> parametros = new HashMap<>();
			
			parametros.put("codigo", producto.getId_prod());
			parametros.put("usuario","ADMIN");
			
			
			String ru = resourceLoader.getResource("classpath:filtro02.jasper").getURI().getPath();
			JasperPrint jasperPrint = JasperFillManager.fillReport(ru, parametros, dataSource.getConnection());
			OutputStream outStream = response.getOutputStream();
			JasperExportManager.exportReportToPdfStream(jasperPrint, outStream);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
}
