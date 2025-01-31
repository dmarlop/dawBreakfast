package com.daw.persistence.crud;

import java.util.List;

import org.springframework.data.repository.ListCrudRepository;

import com.daw.persistence.entities.Desayuno;

public interface DesayunoCrudRepository extends ListCrudRepository<Desayuno, Integer> {

	List<Desayuno> findAllByOrderByPuntuacionAsc();
	List<Desayuno> findByIdEstablecimientoOrderByPuntuacionAsc(int idEstablecimiento);
	List<Desayuno> findByIdEstablecimientoOrderByPrecioAsc(int idEstablecimiento);
	List<Desayuno> findByIdEstablecimiento(int idEstablecimiento);
}
