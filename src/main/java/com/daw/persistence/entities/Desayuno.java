package com.daw.persistence.entities;


import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "desayuno")
@Getter
@Setter
@NoArgsConstructor
public class Desayuno {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	@Column(length = 30, nullable = false)
	private String nombre;
	
	@Column(name="id_establecimiento")
	private int idEstablecimiento;
	
	private double precio;
	
	@Column(length = 255)
	private String imagen;
	
	private double puntuacion;
	
	@JoinColumn(name = "id_establecimiento", referencedColumnName = "id", insertable = false, updatable = false)
	@ManyToOne
	private Establecimiento establecimiento;
	
	
	@OneToMany(mappedBy= "desayuno")
	private List<Review> reviews;

}
