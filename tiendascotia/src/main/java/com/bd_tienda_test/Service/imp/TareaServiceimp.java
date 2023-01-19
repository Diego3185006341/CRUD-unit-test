package com.bd_tienda_test.Service.imp;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.bd_tienda_test.Interfaces.Service.TareaService;
import com.bd_tienda_test.Model.TareasModel;
import com.bd_tienda_test.Model.UsuarioModel;
import com.bd_tienda_test.Repository.TareaRepository;
import com.bd_tienda_test.dto.FiltroDetalle;
import com.bd_tienda_test.dto.FiltrosDto;
import com.bd_tienda_test.dto.RequestResponseAgregar;
import com.bd_tienda_test.dto.RequestResponseAgregarTarea;
import com.bd_tienda_test.dto.ResponseMessage;

import lombok.extern.slf4j.Slf4j;
@Service
@Slf4j
public class TareaServiceimp implements TareaService{
	@Autowired
	TareaRepository tarear;

	@Override
	public ResponseEntity<List<TareasModel>> listarTarea() {
		// TODO Auto-generated method stub
			try {
			List<TareasModel>usuario=new ArrayList<TareasModel>();
			
			tarear.findAll().forEach(usuario ::add);
			return new ResponseEntity<>(usuario,HttpStatus.OK); 
			
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
	}

	@Override
	public ResponseEntity<Object> agregarTarea(RequestResponseAgregarTarea request) {
		// TODO Auto-generated method stub
		try {
			Optional<TareasModel>u=tarear.findById(request.getId_Tarea());
				
			if(u.isPresent()) {
				
				return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
			
			}else {
			tarear.save(TareasModel.builder().id_Tarea(request.getId_Tarea()).nombre_Tarea(request.getNombre_Tarea())
					.mes_Entrega(request.getMes_Entrega()).build());
					
			return new ResponseEntity<>(request,HttpStatus.CREATED);
			
			
			}	
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
	}

	@Override
	public ResponseEntity<RequestResponseAgregarTarea> consultarTareaid(String id) {
		// TODO Auto-generated method stub
		 try {
			   Optional<TareasModel>u=tarear.findById(id);
				
				if(u.isEmpty()) {
					return new ResponseEntity<>(HttpStatus.NOT_FOUND);
					
				}
				else {
					TareasModel consulta=u.get();
					RequestResponseAgregarTarea respuesta=new RequestResponseAgregarTarea();
					respuesta.setId_Tarea(consulta.getId_Tarea());
					respuesta.setNombre_Tarea(consulta.getNombre_Tarea());
					respuesta.setMes_Entrega(consulta.getMes_Entrega());
					
					return new ResponseEntity<>(respuesta,HttpStatus.OK);
				}
			
		} catch (Exception e) {
			return  new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
			
	}

	@Override
	public ResponseEntity<Object> modificarTarea(String id,RequestResponseAgregarTarea request) {
		// TODO Auto-generated method stub
		try {
			Optional<TareasModel>u=tarear.findById(id);
				
			if(u.isEmpty()) {
				
				return new ResponseEntity<>(HttpStatus.NOT_FOUND);
			
			}else {
				TareasModel usuario=u.get();
				usuario.setId_Tarea(request.getId_Tarea());
				usuario.setNombre_Tarea(request.getNombre_Tarea());
				usuario.setMes_Entrega(request.getMes_Entrega());

					
			return new ResponseEntity<>(tarear.save(usuario),HttpStatus.OK);
			
			
			}	
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@Override
	public ResponseEntity<ResponseMessage> deleteTarea(String id) {
		// TODO Auto-generated method stub
		try {
			ResponseMessage response=new ResponseMessage();
			tarear.deleteById(id);
			response.setMessage("se elimino Tarea");
			return new ResponseEntity<>(response,HttpStatus.OK);
			
			
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);		
			}
	}

	@Override
	public ResponseEntity<Object> consultafiltros(FiltrosDto request) {
		// TODO Auto-generated method stub
		try {

			String  nombre_tarea = null;
			String mes_entrega = null;
			if (request.getFiltros() == null || request.getFiltros().isEmpty()) {
				return new ResponseEntity<>(HttpStatus.NO_CONTENT);

			} else {
				Map<String, String> obtenerFiltros = filtrosUsuario(request.getFiltros());

				nombre_tarea = obtenerFiltros.get("nombre_tarea") != null?(obtenerFiltros.get("nombre_tarea"))
						: null;
				mes_entrega = obtenerFiltros.get("mes_entrega")!= null?(obtenerFiltros.get("mes_entrega"))
						: null; ;
			}

			List<TareasModel> consultaData = tarear.consultarTarea(nombre_tarea,mes_entrega);
			
			List<RequestResponseAgregarTarea> respuesta = new ArrayList<>();
			consultaData.forEach(value -> respuesta.add(RequestResponseAgregarTarea.builder().id_Tarea(value.getId_Tarea())
					.nombre_Tarea(value.getNombre_Tarea()).mes_Entrega(value.getMes_Entrega())
					.build()));

					

			return ResponseEntity.ok(respuesta);
		
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}

	}
	private Map<String, String> filtrosUsuario(List<FiltroDetalle> filtros) {
		Map<String, String> returnFiltros = new HashMap<>();

		for (FiltroDetalle filtro : filtros) {
			returnFiltros.put(filtro.getParametro(), filtro.getValor());
		}

		return returnFiltros;
	}
	
	



	

	
	
	
	
	

}
