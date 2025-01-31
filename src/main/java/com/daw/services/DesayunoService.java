package com.daw.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.daw.persistence.crud.DesayunoCrudRepository;
import com.daw.persistence.crud.ReviewCrudRepository;
import com.daw.persistence.entities.Desayuno;
import com.daw.persistence.entities.Review;

@Service
public class DesayunoService {

	
	@Autowired
	private DesayunoCrudRepository desayunoRepository;
	@Autowired
	private ReviewCrudRepository reviewRepository;
	
	public List<Desayuno> getAll(){
		


		return this.desayunoRepository.findAll();
	}
	public Desayuno getById(int id) {
		return this.desayunoRepository.findById(id).get();
	}

	public boolean exists(int id) {
		return this.desayunoRepository.existsById(id);
	}
	public boolean delete(int id) {
		if(this.desayunoRepository.existsById(id)) {
			this.desayunoRepository.deleteById(id);
			this.actualizarPuntuacion(id);
			return true;
		}
		 return false;
	}
	public Desayuno save(Desayuno desayuno) {
		desayuno.setPuntuacion(0);
		if(desayuno.getImagen()==null) {
			desayuno.setImagen("https://i.pinimg.com/736x/6d/7a/43/6d7a43e03c4a75a218a47bb6fd5bfcd0.jpg");
		}
		
			this.actualizarPuntuacion(desayuno.getId());
		return this.desayunoRepository.save(desayuno);
	}
	public Desayuno update(Desayuno desayuno) {
		this.actualizarPuntuacion(desayuno.getId());
		return  this.desayunoRepository.save(desayuno);
	}
	
	public Desayuno actualizarImagen(int id,String imagen) {
		Desayuno desayuno = this.desayunoRepository.findById(id).get();
		desayuno.setImagen(imagen);
		Desayuno actualizado = desayunoRepository.save(desayuno);
		return actualizado;
		
	}
	public void actualizarPuntuacion(int idDesayuno) {
		 Optional<Desayuno> optionalDesayuno = desayunoRepository.findById(idDesayuno);
		    
		    if (optionalDesayuno.isPresent()) {
		        Desayuno desayuno = optionalDesayuno.get();
		List<Review> reviews=reviewRepository.findByIdDesayuno(idDesayuno);
		
		double puntuacion= 0;
		int contador=0;
		
		for(Review review : reviews) {
			puntuacion+=review.getPuntuacion();
			contador++;
		}
		if(contador>0) {
			puntuacion= puntuacion/contador;
		}else {
			puntuacion=0;
		}
		desayuno.setPuntuacion(puntuacion);
		this.desayunoRepository.save(desayuno);
		    }
	}
	public List<Desayuno> findByPuntuacion(){
		return this.desayunoRepository.findAllByOrderByPuntuacionAsc();
	}
	public List<Desayuno> findByPuntuacionEstablecimiento(int idEstablecimiento){
		return this.desayunoRepository.findByIdEstablecimientoOrderByPuntuacionAsc(idEstablecimiento);
	}
	public List<Desayuno> findByEstablecimiento (int idEstablecimiento){
		return this.desayunoRepository.findByIdEstablecimiento(idEstablecimiento);
		
	}
	public List<Desayuno> findByPrecioEstablecimiento(int idEstablecimiento){
		return this.desayunoRepository.findByIdEstablecimientoOrderByPrecioAsc(idEstablecimiento);
	}
}
