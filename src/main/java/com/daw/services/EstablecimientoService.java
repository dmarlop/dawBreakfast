package com.daw.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.daw.persistence.crud.EstablecimientoCrudRepository;
import com.daw.persistence.entities.Desayuno;
import com.daw.persistence.entities.Establecimiento;
import com.daw.persistence.entities.Review;

@Service
public class EstablecimientoService {
	
	@Autowired
	public EstablecimientoCrudRepository establecimientoCrudRepository;
	

	
	public List<Establecimiento> findAll(){
		return this.establecimientoCrudRepository.findAll();
	}
	
	public Optional<Establecimiento> findById(int idEstablecimiento){
		
		return this.establecimientoCrudRepository.findById(idEstablecimiento);	
	}
	
	public Establecimiento save (Establecimiento establecimiento){
		Establecimiento est = establecimientoCrudRepository.findById(establecimiento.getId()).get();
		establecimiento.setPuntuacion(est.getPuntuacion());
		return this.establecimientoCrudRepository.save(establecimiento);
		
	}
	
	public Establecimiento create (Establecimiento establecimiento){
		establecimiento.setPuntuacion(0);
		return this.establecimientoCrudRepository.save(establecimiento);
	}
	
	public boolean delete (int idEstablecimiento) {
		boolean result = false;
		
		if(this.findById(idEstablecimiento).isPresent()) {
			this.establecimientoCrudRepository.deleteById(idEstablecimiento);
			result = true;
		}
		return result;
	}
	
	public boolean existsCliente(int idEstablecimiento){
		return this.establecimientoCrudRepository.existsById(idEstablecimiento);
	}

	public List<Establecimiento> findByPuntuacion(){
		return this.establecimientoCrudRepository.findAllByOrderByPuntuacionDesc();
	}
	public List<Establecimiento> findByUbicacion(String nombre){
		return this.establecimientoCrudRepository.findByUbicacionContaining(nombre);
	}
	
	public void actualizarPuntuacion(int idEstablecimiento) {
		 Optional<Establecimiento> optionalEstablecimiento = establecimientoCrudRepository.findById(idEstablecimiento);
		    
		    if (optionalEstablecimiento.isPresent()) {
		        Establecimiento establecimiento = optionalEstablecimiento.get();
		
		
		double puntuacion= 0;
		int contador=0;
		
		for(Desayuno desayuno : establecimiento.getDesayunos()) {
			puntuacion+=desayuno.getPuntuacion();
			contador++;
		}
		if(contador>0) {
			puntuacion= puntuacion/contador;
		}else {
			puntuacion=0;
		}
		establecimiento.setPuntuacion(puntuacion);
		this.establecimientoCrudRepository.save(establecimiento);
		    }
	}
	
	}

