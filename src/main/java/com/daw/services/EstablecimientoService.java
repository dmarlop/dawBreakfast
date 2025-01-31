package com.daw.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.daw.persistence.crud.EstablecimientoCrudRepository;

import com.daw.persistence.entities.Establecimiento;

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
		
		return this.establecimientoCrudRepository.save(establecimiento);
	}
	
	public Establecimiento create (Establecimiento establecimiento){
		
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

}
