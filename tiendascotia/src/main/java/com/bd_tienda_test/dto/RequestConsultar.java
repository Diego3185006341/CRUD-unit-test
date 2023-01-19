package com.bd_tienda_test.dto;

import java.util.List;

import com.bd_tienda_test.Model.UsuarioModel;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RequestConsultar {

	/*private List<Filtros>filtros;
	private String numeroPagina;
	private String numeroRegistros;
	private String orden;
	private String parametroOrdenamiento;
	*/
	
	private String cedula_Usuario;
}
