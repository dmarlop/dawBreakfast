package com.daw.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.daw.persistence.crud.UsuarioCrudRepository;
import com.daw.persistence.entities.Usuario;

@Service
public class UsuarioService {

	@Autowired
	private UsuarioCrudRepository usuarioRepository;
	
	public List<Usuario> getAll(){
		return this.usuarioRepository.findAll();
	}
	public Optional<Usuario> getById(int id) {
		return this.usuarioRepository.findById(id);
	}

	public boolean exists(int id) {
		return this.usuarioRepository.existsById(id);
	}
	public boolean delete(int id) {
		if(this.usuarioRepository.existsById(id)) {
			this.usuarioRepository.deleteById(id);
			return true;
		}
		 return false;
	}
	public Usuario save(Usuario usuario) {
		return this.usuarioRepository.save(usuario);
	}
	public Usuario update(Usuario usuario) {
		return this.usuarioRepository.save(usuario);
	}
}
