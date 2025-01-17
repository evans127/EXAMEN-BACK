package com.mx.Moneda.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.mx.Moneda.Dominio.Moneda;
import com.mx.Moneda.Service.MonedasService;


@RestController
@RequestMapping(path="/moneda")
@CrossOrigin


public class MonedasWS {
@Autowired
private MonedasService service;
	
//LISTAR
	@GetMapping
	public ResponseEntity<?>listar(){
		List<Moneda>moneda=service.listar();
		if(moneda.isEmpty()) {
			return ResponseEntity.noContent().build();
		}else {
			return ResponseEntity.ok(moneda);
		}
	}
	
	//GUARDAR
	@PostMapping(path = "/guardar")
	public ResponseEntity<?> guardar(@RequestBody Moneda moneda) {
	    Moneda existente = service.buscar(moneda.getNumCia());
	    if (existente != null) {
	        return ResponseEntity.status(HttpStatus.CONFLICT).body("{\"Mensaje\":\"Ya existe una moneda\"}");
	    } else {
	       service.guardar(moneda);
	        return ResponseEntity.status(HttpStatus.CREATED).body("{\"Mensaje\":\"se guardo la  moneda\"}");
	    }
	}
	 //EDITAR
 
	 @PostMapping(path="/editar")
	 public ResponseEntity<?>editar(@RequestBody Moneda moneda){
		 Moneda monedas=service.buscar(moneda.getNumCia());
		 if(monedas !=null) {
			 service.guardar(moneda);
			 return ResponseEntity.status(HttpStatus.CREATED).body(monedas);
		 }else {
			 return ResponseEntity.status(HttpStatus.NOT_FOUND).body("{\"Mensaje\":\"la moneda que intenta editar no existe\"}");
		 }
	 }
	 
	 //ELIMINAR
	 @DeleteMapping(path="/eliminar/{numCia}")
	public ResponseEntity<?>eliminar(@PathVariable int numCia){
		 service.eliminar(numCia);
		 return ResponseEntity.noContent().build();
	 }
	 
	 //BUCAR POR NUMCIA
	 @GetMapping(path="/{numCia}")
	 public ResponseEntity<?>buscarPorNumCia(@PathVariable int numCia){
		 Moneda monedas=service.buscar(numCia);
		 return ResponseEntity.ok(monedas);
	 }
	 //BUSCAR POR STATUS
	 @GetMapping(path="/porStatus")
	 public ResponseEntity<?>byStatus(@RequestParam String status){
		 List<Moneda>monedas=service.byStatus(status);
		 if(monedas.isEmpty()) {
			 return ResponseEntity.notFound().build();
		 }else {
			 return ResponseEntity.ok(monedas);
		 }
	 }
	 
	}

	
