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
	private EstablecimientoService establecimientoService;

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
		if (review.getFecha() == null) {
			review.setFecha(LocalDateTime.now());
		}
		save.setUsuario(u);
		save.setDesayuno(d);
		this.desayunoService.actualizarPuntuacion(review.getIdDesayuno());
		this.establecimientoService.actualizarPuntuacion(review.getDesayuno().getIdEstablecimiento());
		return ReviewMapper.toDto(save);
	}

	public ReviewDTO update(Review review) {
		
		Review Review = this.reviewRepository.findById(review.getId()).get();

		
		if (review.getPuntuacion() > 5 || review.getPuntuacion() < 0) {
			throw new IllegalArgumentException("La puntuación será entera entre 0 y cinco");
		}
		Review.setPuntuacion(review.getPuntuacion());
		Review.setComentarios(review.getComentarios());
		Review.setPrecio(review.getPrecio());

		if (review.getFecha() != null) {
			Review.setFecha(review.getFecha());
		}

		if (review.getImagen() != null) {
			Review.setImagen(review.getImagen());
		} else {
			Review.setImagen("https://i.pinimg.com/736x/6d/7a/43/6d7a43e03c4a75a218a47bb6fd5bfcd0.jpg");
		}

		
		Desayuno d = this.desayunoService.getById(review.getIdDesayuno());
		Usuario u = this.usuarioService.getById(review.getIdUsuario()).get();

		Review.setDesayuno(d);
		Review.setUsuario(u);

		this.desayunoService.actualizarPuntuacion(review.getIdDesayuno());
		this.establecimientoService.actualizarPuntuacion(review.getDesayuno().getIdEstablecimiento());
		return ReviewMapper.toDto(this.reviewRepository.save(Review));
	}

	
	
	
	
	public List<ReviewDTO> orderByFechaDesc() {
		List<Review> reviews = this.reviewRepository.findAllByOrderByFechaDesc();

		List<ReviewDTO> reviewDTOs = new ArrayList<>();

		for (Review review : reviews) {

			ReviewDTO dto = ReviewMapper.toDto(review);
			reviewDTOs.add(dto);
		}

		return reviewDTOs;
	}

	public List<ReviewDTO> orderByFechaAsc() {
		List<Review> reviews = this.reviewRepository.findAllByOrderByFechaAsc();
		List<ReviewDTO> reviewDTOs = new ArrayList<>();

		for (Review review : reviews) {

			ReviewDTO dto = ReviewMapper.toDto(review);
			reviewDTOs.add(dto);
		}

		return reviewDTOs;
	}

	public List<ReviewDTO> orderByPuntuacionDesc() {
		List<Review> reviews = this.reviewRepository.findAllByOrderByPuntuacionDesc();
		List<ReviewDTO> reviewDTOs = new ArrayList<>();

		for (Review review : reviews) {

			ReviewDTO dto = ReviewMapper.toDto(review);
			reviewDTOs.add(dto);
		}

		return reviewDTOs;
	}

	public List<ReviewDTO> orderByPuntuacionAscDesayuno(int idDesayuno) {
		List<Review> reviews = this.reviewRepository.findByIdDesayunoOrderByPuntuacionDesc(idDesayuno);
		List<ReviewDTO> reviewDTOs = new ArrayList<>();

		for (Review review : reviews) {

			ReviewDTO dto = ReviewMapper.toDto(review);
			reviewDTOs.add(dto);
		}

		return reviewDTOs;
	}

	public List<ReviewDTO> orderByFechaDesayuno(int idDesayuno) {
		List<Review> reviews = this.reviewRepository.findByIdDesayunoOrderByFechaDesc(idDesayuno);
		List<ReviewDTO> reviewDTOs = new ArrayList<>();

		for (Review review : reviews) {

			ReviewDTO dto = ReviewMapper.toDto(review);
			reviewDTOs.add(dto);
		}

		return reviewDTOs;
	}
	
	public List<Review> findByIdDesayuno(int idDesayuno){
		return this.reviewRepository.findByIdDesayuno(idDesayuno);
	};
}
