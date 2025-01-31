package com.daw.services.mapper;

import com.daw.persistence.entities.Review;
import com.daw.services.dto.ReviewDTO;

public class ReviewMapper {

	public static ReviewDTO toDto(Review review) {
		ReviewDTO dto = new ReviewDTO();
		dto.setComentarios(review.getComentarios());
		dto.setFecha(review.getFecha());
		dto.setId(review.getId());
		dto.setImagen(review.getImagen());
		dto.setPrecio(review.getPrecio());
		dto.setPuntuacion(review.getPuntuacion());
		dto.setNombreDesayuno(review.getDesayuno().getNombre());
		dto.setNombreUsuario(review.getUsuario().getUsername());
		return dto;

	}
}
