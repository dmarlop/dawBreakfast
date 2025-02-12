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

import com.daw.persistence.entities.Desayuno;
import com.daw.services.DesayunoService;
@RestController
@RequestMapping("/desayuno")
public class DesayunoController {

	@Autowired
	private DesayunoService desayunoService;
	
	@GetMapping
	public ResponseEntity<List<Desayuno>> getAll(){
		return ResponseEntity.ok(this.desayunoService.getAll());
	}
	
	@GetMapping("/{idDesayuno}")
	public ResponseEntity<Desayuno> getById(@PathVariable int idDesayuno){
		if(this.desayunoService.exists(idDesayuno)) {
			return ResponseEntity.ok(this.desayunoService.getById(idDesayuno));
		}
		return ResponseEntity.badRequest().build();
	}
	
	@DeleteMapping("/{idDesayuno}")
	public ResponseEntity<Desayuno> delete(@PathVariable int idDesayuno) {
		if(this.desayunoService.exists(idDesayuno)) {
			this.desayunoService.delete(idDesayuno);
			return ResponseEntity.ok().build();
		}
		return ResponseEntity.badRequest().build();
	}
	@PostMapping
	public ResponseEntity<Desayuno> crearDesayuno(@RequestBody Desayuno desayuno){
		
		
		return new ResponseEntity<Desayuno>(this.desayunoService.save(desayuno), HttpStatus.CREATED);
	}
	
	@PutMapping("/{idDesayuno}")
	public ResponseEntity<Desayuno> actualizaDesayuno(@PathVariable int idDesayuno,@RequestBody Desayuno desayuno){
		if(idDesayuno != desayuno.getId()) {
			return ResponseEntity.badRequest().build();
		}
		if(!this.desayunoService.exists(idDesayuno)) {
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.ok(this.desayunoService.save(desayuno));
	}
	@PutMapping("/{idDesayuno}/imagen")
	public ResponseEntity<Desayuno> actualizarImagen(@PathVariable int idDesayuno,@RequestParam String imagen){
		
		if(!this.desayunoService.exists(idDesayuno)) {
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.ok(this.desayunoService.actualizarImagen(idDesayuno,imagen));
	}
	@GetMapping("/puntuacionAsc")
	public ResponseEntity<List<Desayuno>> puntuacionAsc(){
		return ResponseEntity.ok(this.desayunoService.findByPuntuacion());
	}
	@GetMapping("/puntuacionEstablecimiento")
	public ResponseEntity<List<Desayuno>> puntuacionEstablecimiento(@PathVariable int idEstablecimiento){
		return ResponseEntity.ok(this.desayunoService.findByPuntuacionEstablecimiento(idEstablecimiento));
	}
	@GetMapping("/establecimiento")
	public ResponseEntity<List<Desayuno>> establecimiento(@PathVariable int idEstablecimiento){
		return ResponseEntity.ok(this.desayunoService.findByEstablecimiento(idEstablecimiento));
	}
	@GetMapping("/precioEstablecimiento")
	public ResponseEntity<List<Desayuno>> precioEstablecimiento(@PathVariable int idEstablecimiento){
		return ResponseEntity.ok(this.desayunoService.findByPrecioEstablecimiento(idEstablecimiento));
	}
}
