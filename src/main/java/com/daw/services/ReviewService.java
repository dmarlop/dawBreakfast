package com.daw.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.daw.persistence.crud.ReviewCrudRepository;
import com.daw.persistence.entities.Review;
import com.daw.services.dto.ReviewDTO;
import com.daw.services.mapper.ReviewMapper;

@Service
public class ReviewService {

	@Autowired
	private ReviewCrudRepository reviewRepository;
	
	public List<ReviewDTO> getAll(){	
		List<ReviewDTO> reviewDTO = new ArrayList<ReviewDTO>();

		for (Review r : this.reviewRepository.findAll()) {
			reviewDTO.add(ReviewMapper.toDto(r));
		}

		return reviewDTO;
	}
	public ReviewDTO getById(int id) {
		return ReviewMapper.toDto(this.reviewRepository.findById(id).get());
	}

	public boolean exists(int id) {
		return this.reviewRepository.existsById(id);
	}
	public boolean delete(int id) {
		if(this.reviewRepository.existsById(id)) {
			 this.reviewRepository.deleteById(id);
			 return true;
		}
		return false;
	}
	public ReviewDTO save(Review review) {
		if(review.getPuntuacion()>5 ||review.getPuntuacion()<0) {
			throw new IllegalArgumentException("La puntuación será entera entre 0 y cinco");
		}
		Review save = this.reviewRepository.save(review);
	    return ReviewMapper.toDto(save);
	}
	public ReviewDTO update(Review review) {
		return ReviewMapper.toDto(this.reviewRepository.save(review));
	}
	public List<Review> orderByFechaDesc(){
		return this.reviewRepository.findAllByOrderByFechaDesc();
	}

	public List<Review> orderByFechaAsc(){
		return this.reviewRepository.findAllByOrderByFechaAsc();
	}
	public List<Review> orderByPuntuacionDesc(){
		return this.reviewRepository.findAllByOrderByPuntuacionDesc();
	}

	public List<Review> orderByPuntuacionAscDesayuno(int idDesayuno){
		return this.reviewRepository.findByIdDesayunoOrderByPuntuacionDesc(idDesayuno);
	}
	public List<Review> orderByFechaDesayuno(int idDesayuno){
		return this.reviewRepository.findByIdDesayunoOrderByFechaDesc(idDesayuno);
	}
}
