package com.bd_tienda_test.Controlador;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bd_tienda_test.Interfaces.Service.TareaService;
import com.bd_tienda_test.Model.TareasModel;
import com.bd_tienda_test.dto.FiltrosDto;
import com.bd_tienda_test.dto.RequestResponseAgregar;
import com.bd_tienda_test.dto.RequestResponseAgregarTarea;
import com.bd_tienda_test.dto.ResponseMessage;
@RestController
@RequestMapping("/api")
public class TareaController {
	@Autowired
	TareaService servicetarea;
	
	@GetMapping("/listarTarea")
	public ResponseEntity<List<TareasModel>> listartarea()
	{
		return servicetarea.listarTarea();
	}
	@PostMapping("/agregarTarea")
	public ResponseEntity<Object> AgregarTarea(@RequestBody RequestResponseAgregarTarea request) {
		
		return servicetarea.agregarTarea(request);
	}
	@PutMapping("/modificarTarea/{id}")
	public ResponseEntity<Object> modificarTarea (@PathVariable String id,@RequestBody RequestResponseAgregarTarea request) {
		
		return servicetarea.modificarTarea(id,request );
	}
	@GetMapping ("/consultarTarea/{id}")
	public ResponseEntity<RequestResponseAgregarTarea> consultarTareaPorid (@PathVariable String id)
	{
		return  servicetarea.consultarTareaid(id);
	}
	@DeleteMapping ("eliminarTarea/{id}")
	public ResponseEntity<ResponseMessage> deleteTarea(@PathVariable String id)
	{
		return servicetarea.deleteTarea(id);
		
	}
	@GetMapping("/filtrosTarea")
	public  ResponseEntity<Object> consultafiltrosTarea (@RequestBody FiltrosDto request ) {
		return servicetarea.consultafiltros(request);
		
	}
}
