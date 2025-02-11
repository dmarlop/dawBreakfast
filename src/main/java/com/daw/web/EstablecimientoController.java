package com.daw.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.daw.persistence.entities.Establecimiento;
import com.daw.services.EstablecimientoService;

@RestController
@RequestMapping("/establecimientos")
public class EstablecimientoController {

	@Autowired
	public EstablecimientoService establecimientoService;
	
	
	@GetMapping
	public ResponseEntity<List<Establecimiento>> getAll(){
		return ResponseEntity.ok(establecimientoService.findAll());
	}
	
	@GetMapping("/{idEstablecimiento}")
	public ResponseEntity<Establecimiento> getEstablecimiento(@PathVariable int idEstablecimiento){
		
		if(this.establecimientoService.findById(idEstablecimiento).isEmpty()) {
			return ResponseEntity.notFound().build();
		}else {
			return ResponseEntity.ok(this.establecimientoService.findById(idEstablecimiento).get());
		}
		
	
		
	}
	
	@PostMapping
	public ResponseEntity<Establecimiento> establecimiento(@RequestBody Establecimiento establecimiento){
		return new ResponseEntity<Establecimiento>(this.establecimientoService.create(establecimiento), HttpStatus.CREATED);
	}
	
	@PutMapping("/{idEstablecimiento}")
	public ResponseEntity<Establecimiento> establecimiento(@PathVariable int idEstablecimiento, @RequestBody Establecimiento establecimiento){
		if(idEstablecimiento != establecimiento.getId()) {
			return ResponseEntity.notFound().build();
		} else if(this.establecimientoService.findById(idEstablecimiento).isEmpty()) {
			return ResponseEntity.notFound().build();
		}else {
			return ResponseEntity.ok(this.establecimientoService.save(establecimiento));
		}
		
	}
	
	
	@DeleteMapping("/{idCliente}")
	public ResponseEntity<Establecimiento> delete(@PathVariable int idCliente){
		if(this.establecimientoService.delete(idCliente)) {
			return ResponseEntity.ok().build();
		}
		else {
			return ResponseEntity.notFound().build();
		}
		
	}
	
	@GetMapping("/puntuacion")
	public ResponseEntity<List<Establecimiento>> getByPuntuacion(){
		return ResponseEntity.ok(this.establecimientoService.findByPuntuacion());
	}
	@GetMapping("/ubicacion")
	public ResponseEntity<List<Establecimiento>> getByUbicacion(@RequestParam String ubicacion){
		return ResponseEntity.ok(this.establecimientoService.findByUbicacion(ubicacion));
	}
}
