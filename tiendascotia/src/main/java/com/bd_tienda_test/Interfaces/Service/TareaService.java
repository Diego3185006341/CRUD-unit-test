package com.bd_tienda_test.Interfaces.Service;

import java.util.List;

import org.springframework.http.ResponseEntity;

import com.bd_tienda_test.Model.TareasModel;
import com.bd_tienda_test.Model.UsuarioModel;
import com.bd_tienda_test.dto.FiltrosDto;
import com.bd_tienda_test.dto.RequestResponseAgregar;
import com.bd_tienda_test.dto.RequestResponseAgregarTarea;
import com.bd_tienda_test.dto.ResponseMessage;

public interface TareaService {



	ResponseEntity<List<TareasModel>> listarTarea();

	ResponseEntity<Object> agregarTarea(RequestResponseAgregarTarea request);



	ResponseEntity<RequestResponseAgregarTarea> consultarTareaid(String id);

	ResponseEntity<Object> modificarTarea(String id,RequestResponseAgregarTarea request);

	ResponseEntity<ResponseMessage> deleteTarea(String id);

	ResponseEntity<Object> consultafiltros(FiltrosDto request);

}
