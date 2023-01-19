package bd_tienda_testImp;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.bd_tienda_test.Interfaces.Service.TareaService;
import com.bd_tienda_test.Model.TareasModel;
import com.bd_tienda_test.Model.UsuarioModel;
import com.bd_tienda_test.Repository.TareaRepository;
import com.bd_tienda_test.Repository.UsuarioRepository;
import com.bd_tienda_test.Service.imp.TareaServiceimp;
import com.bd_tienda_test.Service.imp.UsuarioServiceimp;
import com.bd_tienda_test.dto.FiltroDetalle;
import com.bd_tienda_test.dto.FiltrosDto;
import com.bd_tienda_test.dto.RequestResponseAgregar;
import com.bd_tienda_test.dto.RequestResponseAgregarTarea;
import com.bd_tienda_test.dto.ResponseMessage;

public class TareaTestimp {
	@Mock
	TareaRepository mockrepo;

	@InjectMocks
	TareaServiceimp mocktareaimp;
	
	@Mock
	 TareasModel tareamodel;
	@Mock
	 UsuarioModel mockusuariomodel;


	@BeforeEach
		void contextLoads() {
		MockitoAnnotations.initMocks(this);		
	}
	 @Test
	void agregarTarea() {
		 //RequestResponseAgregar rq=new RequestResponseAgregar("2323","dad","2","#","dsd");
		 /* Mockito.when(mockrepo.save(Mockito.anyObject())).thenReturn(usuariomodel);
		  ResponseEntity<Object>response=mockusuarioimp.agregarUsuario(RequestResponseAgregar.builder().build());
		  assertEquals(response.getBody().getClass(),RequestResponseAgregar.class);
*/	
        RequestResponseAgregarTarea request = new RequestResponseAgregarTarea();
        request.setId_Tarea("04");
        request.setNombre_Tarea("proyectoz");
        request.setMes_Entrega("abril");


        when(mockrepo.findById("123")).thenReturn(Optional.empty());

        // When
        ResponseEntity<Object> response = mocktareaimp.agregarTarea(request);

        // Then
        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals(request, response.getBody());
    }
	 
	 
	 
	 @Test 
		void modificarUsuario() {
			 /* Mockito.when(mockrepo.findById(anyString())).thenReturn(Optional.ofNullable(mockusuariomodel));	
			  Mockito.when(mockrepo.save(Mockito.anyObject())).thenReturn(usuariomodel);
			  ResponseEntity<Object>response=mockusuarioimp.agregarUsuario(RequestResponseAgregar.builder().build());
			  assertEquals(response.getBody().getClass(),RequestResponseAgregar.class);*/
			RequestResponseAgregarTarea request = new RequestResponseAgregarTarea();
			String cedula="123";
	        request.setId_Tarea("123");
	        request.setNombre_Tarea("proyecto32");
	        request.setMes_Entrega("diciembre");
	        
	        TareasModel tarea = new TareasModel();
	        when(mockrepo.findById(cedula)).thenReturn(Optional.of(tarea));
	        when(mockrepo.save(tarea)).thenReturn(tarea);

	        // When
	        ResponseEntity<Object> response = mocktareaimp.modificarTarea(cedula,request);

	        // Then
	        assertEquals(HttpStatus.OK, response.getStatusCode());
	        assertEquals(tarea, response.getBody());
		    
		}
		@Test 
		void consultarTarea() {
		       String cedula = "123";

		        TareasModel tarea = new TareasModel();
		        tarea.setId_Tarea("123");
		        tarea.setNombre_Tarea("Protect");
		        tarea.setMes_Entrega("enero");
		     

		        when(mockrepo.findById(cedula)).thenReturn(Optional.of(tarea));

		        // When
		        ResponseEntity<RequestResponseAgregarTarea> response = mocktareaimp.consultarTareaid(cedula);

		        // Then
		        assertEquals(HttpStatus.OK, response.getStatusCode());
		        RequestResponseAgregarTarea requestResponseAgregarTarea = response.getBody();
		        assertEquals(tarea.getId_Tarea(), requestResponseAgregarTarea.getId_Tarea());
		        assertEquals(tarea.getNombre_Tarea(), requestResponseAgregarTarea.getNombre_Tarea());
		        assertEquals(tarea.getMes_Entrega(), requestResponseAgregarTarea.getMes_Entrega());
		        
		
		}
		@Test 
		void deleteTarea() {
			String cedula="00001";

		        // When
		        ResponseEntity<ResponseMessage> response = mocktareaimp.deleteTarea(cedula);

		        // Then
		        verify(mockrepo).deleteById(cedula);
		        assertEquals(HttpStatus.OK, response.getStatusCode());
		        assertEquals("se elimino Tarea", response.getBody().getMessage());
		}
		@Test
		public void testConsultafiltros_filtrosExist() {
			// Given
			FiltrosDto request = new FiltrosDto();
			List<FiltroDetalle> filtros = new ArrayList<>();
			FiltroDetalle filtro = new FiltroDetalle();
			filtro.setParametro("mes_entrega");
			filtro.setValor("enero");
			filtros.add(filtro);
			request.setFiltros(filtros);

			
			    TareasModel tareas = new TareasModel();
			    tareas.setId_Tarea("123");
			    tareas.setNombre_Tarea("proxy");
			    tareas.setMes_Entrega("marzo");
			   
			    
			    //mocking the repository behavior
			    when(mockrepo.consultarTarea(filtro.getValor(), null)).thenReturn(Arrays.asList(tareas));
			    
			    // When
			    ResponseEntity<Object> result = mocktareaimp.consultafiltros(request);

			    // Then
			    assertEquals(HttpStatus.OK, result.getStatusCode());
			    List<RequestResponseAgregarTarea> list = (List<RequestResponseAgregarTarea>) result.getBody();
			    assertEquals(1, list.size());
			    assertEquals("123", list.get(0).getId_Tarea());
			    assertEquals("proxy", list.get(0).getNombre_Tarea());
			    assertEquals("marzo", list.get(0).getMes_Entrega());
			    
		}
}
