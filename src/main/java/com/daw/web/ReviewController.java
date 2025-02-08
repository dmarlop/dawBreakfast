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
import org.springframework.web.bind.annotation.RestController;

import com.daw.persistence.entities.Desayuno;
import com.daw.persistence.entities.Review;
import com.daw.services.DesayunoService;
import com.daw.services.ReviewService;
import com.daw.services.dto.ReviewDTO;

@RestController
@RequestMapping("/review")
public class ReviewController {
	
	@Autowired
	public ReviewService reviewService;
	@Autowired
	public DesayunoService desayunoService;
	
	
	@GetMapping
	public ResponseEntity<List<ReviewDTO>> getAll(){
		return ResponseEntity.ok(this.reviewService.getAll());
	}
	
	@GetMapping("/{idReview}")
	public ResponseEntity <ReviewDTO> getbyId(@PathVariable int idReview){
		return ResponseEntity.ok(this.reviewService.getById(idReview));
	}
	
	@DeleteMapping("/{idReview}")
	public ResponseEntity<Desayuno> delete(@PathVariable int idReview) {
		if(this.reviewService.exists(idReview)) {
			this.reviewService.delete(idReview);
			return ResponseEntity.ok().build();
		}
		return ResponseEntity.badRequest().build();
	}
	
	@PostMapping
	public ResponseEntity<ReviewDTO> crearReview(@RequestBody Review review){
		desayunoService.actualizarPuntuacion(review.getIdDesayuno());
		return new ResponseEntity<ReviewDTO>(this.reviewService.save(review), HttpStatus.CREATED);
	}
	
	
	
	@PutMapping("/{idReview}")
	public ResponseEntity<ReviewDTO> actualiza(@PathVariable int idReview,@RequestBody Review review){
		if(idReview != review.getId()) {
			return ResponseEntity.badRequest().build();
		}
		if(!this.reviewService.exists(idReview)) {
			return ResponseEntity.notFound().build();
		}
		
		desayunoService.actualizarPuntuacion(review.getIdDesayuno());
		return ResponseEntity.ok(this.reviewService.update(review));
	}
	
	@GetMapping("/recientes")
	public ResponseEntity<List<ReviewDTO>> recientes(){
		return ResponseEntity.ok(this.reviewService.orderByFechaDesc());
	}
	
	@GetMapping("/antiguas")
	public ResponseEntity<List<ReviewDTO>> antiguas(){
		return ResponseEntity.ok(this.reviewService.orderByFechaAsc());
	}
	
	@GetMapping("/puntuacion")
	public ResponseEntity<List<ReviewDTO>> puntuacion(){
		return ResponseEntity.ok(this.reviewService.orderByPuntuacionDesc());
	}

	@GetMapping("/fecha/{idDesayuno}")
	public ResponseEntity<List<ReviewDTO>> getFechaDesayuno(@PathVariable int idDesayuno){
		return ResponseEntity.ok(this.reviewService.orderByFechaDesayuno(idDesayuno));
	}

	@GetMapping("/puntuacion/{idDesayuno}")
	public ResponseEntity<List<ReviewDTO>> getPuntuacionDesayuno(@PathVariable int idDesayuno){
		return ResponseEntity.ok(this.reviewService.orderByPuntuacionAscDesayuno(idDesayuno));
	}

	
}
