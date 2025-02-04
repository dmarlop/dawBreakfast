package com.daw.services.dto;

import java.time.LocalDateTime;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class ReviewDTO {

	private int id;
	private String nombreUsuario;
	private String nombreDesayuno;
	private LocalDateTime fecha;
	private double precio;
	private String imagen;
	private int puntuacion;
	private String comentarios;
	
}
