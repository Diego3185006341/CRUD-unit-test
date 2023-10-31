package com.bd_tienda_test.Controlador;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.bd_tienda_test.Interfaces.Service.IUsuarioService;
import com.bd_tienda_test.Model.UsuarioModel;
import com.bd_tienda_test.dto.FiltrosDto;
import com.bd_tienda_test.dto.RequestResponseAgregar;
import com.bd_tienda_test.dto.ResponseMessage;





@CrossOrigin(origins = "*")
@RestController
//@RequestMapping
@RequestMapping("/api")
public class UsuarioControlador {

	@Autowired
	private IUsuarioService serviceUsuario;


	@GetMapping("/listarUsuario")
	public ResponseEntity<List<UsuarioModel>> listarUsuario()
	{
		return serviceUsuario.listarUsuarios();
	}
	@GetMapping("/listarUsuarioMapper")
	public List<UsuarioModel> listarUsuarioMapper()
	{
		return serviceUsuario.listarUsuariosMapper();
	}
	
		

	@DeleteMapping ("eliminarUsuario/{Cedula}")
	public ResponseEntity<ResponseMessage> deleteUsuario (@PathVariable String Cedula)
	{
		return serviceUsuario.deleteUsuario(Cedula);
		
	}
	@PostMapping("/agregarUsuario")
	public ResponseEntity<Object> AgregarUsuario (@RequestBody RequestResponseAgregar request) {
		
		return serviceUsuario.agregarUsuario(request);
	}	

	@PutMapping("/modificarUsuario/{id}")
	public ResponseEntity<Object> modificarUsuario (@PathVariable String id,@RequestBody RequestResponseAgregar request) {
		
		return serviceUsuario.modificarUsuario(id,request );
	}
	

	@GetMapping ("/consultarUsuario/{Cedula}")
	public ResponseEntity<RequestResponseAgregar> consultarUsuarioPorCedula (@PathVariable String Cedula)
	{
		return  serviceUsuario.consultarusuario(Cedula);
	}
	
	@GetMapping("/filtros")
	public  Optional<List<UsuarioModel>> consultafiltros (@RequestBody FiltrosDto request ) {
		return serviceUsuario.consultafiltros(request);
		
	}
}
