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
public class RequestResponseAgregarTarea {
	private String id_Tarea;
	private String nombre_Tarea;
	private String mes_Entrega;
	
	

}
