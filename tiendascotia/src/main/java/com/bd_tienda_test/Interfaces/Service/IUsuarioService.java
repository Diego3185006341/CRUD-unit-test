package com.bd_tienda_test.Interfaces.Service;

import java.util.List;
import java.util.Optional;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.bd_tienda_test.Model.UsuarioModel;
import com.bd_tienda_test.dto.FiltrosDto;
import com.bd_tienda_test.dto.RequestConsultar;
import com.bd_tienda_test.dto.RequestResponseAgregar;
import com.bd_tienda_test.dto.ResponseMessage;

public interface IUsuarioService {


	public ResponseEntity<List<UsuarioModel>>listarUsuarios();
	public List<UsuarioModel>listarUsuariosMapper();
	
	//public ResponseEntity<ResponseUsuario> deleteUsuario (RequestResponseAgregar request);
	public ResponseEntity<ResponseMessage> deleteUsuario (String Cedula);
	public ResponseEntity<Object> agregarUsuario(RequestResponseAgregar request);
	public ResponseEntity<Object> modificarUsuario(String id,RequestResponseAgregar request);
	public ResponseEntity<RequestResponseAgregar> consultarusuario(String Cedula);
	public ResponseEntity<Object> consultafiltros(FiltrosDto request);
	
}
