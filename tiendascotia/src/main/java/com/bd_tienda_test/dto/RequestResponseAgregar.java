package com.bd_tienda_test.dto;

import java.time.LocalDate;
import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
@AllArgsConstructor
@Data
@NoArgsConstructor
@Builder
public class RequestResponseAgregar {
	private String cedula_Usuario;
	private String nombre_Usuario;
	private String correo_Usuario;
	private String usuario;
	private String clave_Usuario;
	private LocalDate fecha_Ingreso;
	

}
