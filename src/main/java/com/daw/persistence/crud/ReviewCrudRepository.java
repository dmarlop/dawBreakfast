package com.daw.persistence.crud;

import java.util.List;

import org.springframework.data.repository.ListCrudRepository;

import com.daw.persistence.entities.Review;

public interface ReviewCrudRepository extends ListCrudRepository<Review, Integer>{

	List<Review> findByIdDesayuno(int idDesayuno);
	List<Review> findAllByOrderByFechaDesc();
	List<Review> findAllByOrderByFechaAsc();
	List<Review> findAllByOrderByPuntuacionDesc();
	List<Review> findByIdDesayunoOrderByFechaDesc(int idDesayuno);
	List<Review> findByIdDesayunoOrderByPuntuacionDesc(int idDesayuno);
}
