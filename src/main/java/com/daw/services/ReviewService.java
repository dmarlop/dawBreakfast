package com.daw.services;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.daw.persistence.crud.ReviewCrudRepository;
import com.daw.persistence.entities.Desayuno;
import com.daw.persistence.entities.Review;
import com.daw.persistence.entities.Usuario;
import com.daw.services.dto.ReviewDTO;
import com.daw.services.mapper.ReviewMapper;

@Service
public class ReviewService {
	@Autowired
	private ReviewCrudRepository reviewRepository;

	@Autowired
	private DesayunoService desayunoService;

	@Autowired
	private UsuarioService usuarioService;

	public List<ReviewDTO> getAll() {
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
		if (this.reviewRepository.existsById(id)) {
			this.reviewRepository.deleteById(id);
			this.desayunoService.actualizarPuntuacion(id);
			return true;
			
		}
		return false;
	}

	public ReviewDTO save(Review review) {
		if (review.getPuntuacion() > 5 || review.getPuntuacion() < 0) {
			throw new IllegalArgumentException("La puntuación será entera entre 0 y cinco");
		}
		Review save = this.reviewRepository.save(review);

		Desayuno d = this.desayunoService.getById(review.getIdDesayuno());
		Usuario u = this.usuarioService.getById(review.getIdUsuario()).get();
		if (review.getImagen() == null) {
			review.setImagen("https://i.pinimg.com/736x/6d/7a/43/6d7a43e03c4a75a218a47bb6fd5bfcd0.jpg");
		}
		if(review.getFecha()==null) {
			review.setFecha(LocalDateTime.now());
		}
		save.setUsuario(u);
		save.setDesayuno(d);
		this.desayunoService.actualizarPuntuacion(review.getIdDesayuno());
		return ReviewMapper.toDto(save);
	}

	public ReviewDTO update(Review review) {
		this.desayunoService.actualizarPuntuacion(review.getId());
		return ReviewMapper.toDto(this.reviewRepository.save(review));
	}

	public List<Review> orderByFechaDesc() {
		return this.reviewRepository.findAllByOrderByFechaDesc();
	}

	public List<Review> orderByFechaAsc() {
		return this.reviewRepository.findAllByOrderByFechaAsc();
	}

	public List<Review> orderByPuntuacionDesc() {
		return this.reviewRepository.findAllByOrderByPuntuacionDesc();
	}

	public List<Review> orderByPuntuacionAscDesayuno(int idDesayuno) {
		return this.reviewRepository.findByIdDesayunoOrderByPuntuacionDesc(idDesayuno);
	}

	public List<Review> orderByFechaDesayuno(int idDesayuno) {
		return this.reviewRepository.findByIdDesayunoOrderByFechaDesc(idDesayuno);
	}
}
