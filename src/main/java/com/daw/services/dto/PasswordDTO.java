package com.daw.services.dto;

import java.time.LocalDateTime;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class PasswordDTO {

	
	private String contrasenaVieja;
	private String contrasenaNueva;
	
}
