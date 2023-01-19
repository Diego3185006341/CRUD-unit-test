package bd_tienda_testController;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.bd_tienda_test.Controlador.TareaController;
import com.bd_tienda_test.Controlador.UsuarioControlador;
import com.bd_tienda_test.Interfaces.Service.IUsuarioService;
import com.bd_tienda_test.Interfaces.Service.TareaService;
import com.bd_tienda_test.Model.TareasModel;
import com.bd_tienda_test.Model.UsuarioModel;
import com.bd_tienda_test.dto.FiltroDetalle;
import com.bd_tienda_test.dto.FiltrosDto;
import com.bd_tienda_test.dto.RequestResponseAgregar;
import com.bd_tienda_test.dto.RequestResponseAgregarTarea;
import com.bd_tienda_test.dto.ResponseMessage;

public class TareaTestController {
	@Mock
	TareaService service;
	@Mock
	ResponseEntity<TareasModel> usuariom;


	@InjectMocks
	TareaController controller;
	
	@BeforeEach
	public void setUp()throws Exception {
		MockitoAnnotations.initMocks(this);
			
	}
	@Test
	 void agregarTarea(){
		RequestResponseAgregarTarea request = new RequestResponseAgregarTarea();
	    request.setId_Tarea("123");
	    request.setNombre_Tarea("proxy");
	    request.setMes_Entrega("marzo");
	    
	    Mockito.when(service.agregarTarea(request)).thenReturn(ResponseEntity.ok().body(request));
	    ResponseEntity<Object>respuesta=controller.AgregarTarea(request);
	    assertEquals(respuesta.getStatusCode(), HttpStatus.OK);
	    assertEquals(respuesta.getBody().getClass(),RequestResponseAgregarTarea.class);
	    assertEquals(request, respuesta.getBody());
	}
	@Test
	void modificarTarea(){
	RequestResponseAgregarTarea request = new RequestResponseAgregarTarea();
	String Cedula="01233";
	Mockito.when(service.modificarTarea(Cedula,request)).thenReturn(ResponseEntity.ok().body(RequestResponseAgregarTarea.builder().build()));
	ResponseEntity<Object>respuesta=controller.modificarTarea(Cedula,request);
	assertEquals(HttpStatus.OK, respuesta.getStatusCode());
	assertEquals(RequestResponseAgregarTarea.class, respuesta.getBody().getClass());
	}
	@Test
	void consultarTarea(){
		String cedula="00001";
		RequestResponseAgregarTarea requestResponseAgregar = new RequestResponseAgregarTarea();
		Mockito.when(service.consultarTareaid(cedula)).thenReturn(ResponseEntity.ok(requestResponseAgregar));
		ResponseEntity<RequestResponseAgregarTarea> respuesta=controller.consultarTareaPorid(cedula);
		assertEquals(respuesta.getBody(), requestResponseAgregar);
		}
	@Test
	void deleteTarea(){

	String Cedula="00001";
	Mockito.when(service.deleteTarea(Cedula)).thenReturn(ResponseEntity.ok().body(ResponseMessage.builder().build()));
	ResponseEntity<ResponseMessage> respuesta=controller.deleteTarea(Cedula);
	assertEquals(respuesta.getBody().getClass(),ResponseMessage.class);
	}
	@Test
	void testConsultafiltrosTarea() {
	    // Given
	    FiltrosDto request = new FiltrosDto();
	    request.setFiltros(Arrays.asList(new FiltroDetalle("nombre_tarea", "diego")));

	    List<TareasModel> expectedData = Arrays.asList(
	        new TareasModel("123", "diego", "marzo"));

	    // When
	    Mockito.when(service.consultafiltros(request)).thenReturn(ResponseEntity.ok().body(expectedData));
	    ResponseEntity<Object> result = controller.consultafiltrosTarea(request);

	    // Then
	    assertEquals(HttpStatus.OK, result.getStatusCode());
	    assertEquals(expectedData, result.getBody());
	}
	
}
