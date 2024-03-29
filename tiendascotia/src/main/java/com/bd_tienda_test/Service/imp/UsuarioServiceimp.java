package com.bd_tienda_test.Service.imp;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.bd_tienda_test.Interfaces.Service.IUsuarioService;
import com.bd_tienda_test.Model.UsuarioModel;
import com.bd_tienda_test.Repository.UsuarioRepository;

import com.bd_tienda_test.dto.FiltrosDto;
import com.bd_tienda_test.dto.RequestResponseAgregar;
import com.bd_tienda_test.dto.ResponseMessage;


import lombok.extern.slf4j.Slf4j;


@Service
@Slf4j
public class UsuarioServiceimp implements IUsuarioService {


    @Autowired
    private UsuarioRepository usuarioR;



    @Override
    public ResponseEntity<List<UsuarioModel>> listarUsuarios() {

		try {
			List<UsuarioModel> usuarios = usuarioR.findAll();

			return new ResponseEntity<>(usuarios, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}

    }

    @Override
    public List<UsuarioModel> listarUsuariosMapper() {
        List<UsuarioModel> usuarios = usuarioR.findAll();
        List<UsuarioModel> usuarios1 = MapeerUser(usuarios);

        return usuarios1;
    }

    List<UsuarioModel> MapeerUser(List<UsuarioModel> usuarios) {
        List<UsuarioModel> sa = new ArrayList<>();

        usuarios.stream().map(use -> {
            return sa.add(use.builder().usuario(use.getUsuario()).clave_Usuario(use.getClave_Usuario()).build());

        }).collect(Collectors.toList());

        return  sa;
    }

    @Override
    public ResponseEntity<ResponseMessage> deleteUsuario(String Cedula) {

        try {
            ResponseMessage response = new ResponseMessage();
            usuarioR.deleteById(Cedula);
            response.setMessage("se elimino usuario");
            return new ResponseEntity<>(response, HttpStatus.OK);


        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

    }



	@Override
	public ResponseEntity<Object> agregarUsuario(RequestResponseAgregar request) {
		try {
			Optional<UsuarioModel>u=usuarioR.findById(request.getCedula_Usuario());

			if(u.isPresent()) {

				return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);

			}else {
			usuarioR.save(UsuarioModel.builder().cedula_Usuario(request.getCedula_Usuario()).nombre_Usuario(request.getNombre_Usuario())
					.correo_Usuario(request.getCorreo_Usuario()).usuario(request.getUsuario()).clave_Usuario(request.getClave_Usuario()).
					fecha_Ingreso((LocalDate.now())).build());

			return new ResponseEntity<>(request,HttpStatus.CREATED);


			}
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}

	}

    @Override
    public ResponseEntity<Object> modificarUsuario(String id, RequestResponseAgregar request) {
        try {
            Optional<UsuarioModel> u = usuarioR.findById(id);

            if (u.isEmpty()) {

                return new ResponseEntity<>(HttpStatus.NOT_FOUND);

            } else {
                UsuarioModel usuario = u.get();
                usuario.setCedula_Usuario(request.getCedula_Usuario());
                usuario.setClave_Usuario(request.getClave_Usuario());
                usuario.setCorreo_Usuario(request.getCorreo_Usuario());
                usuario.setNombre_Usuario(request.getNombre_Usuario());
                usuario.setUsuario(request.getUsuario());


                return new ResponseEntity<>(usuarioR.save(usuario), HttpStatus.OK);


            }
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
	/*public UsuarioModel consultarusuario(String Cedula,RequestConsultar request) {
		Optional<UsuarioModel> u=usuariodb.findById(Cedula);
		try {
			
		
		
		
	} catch (Exception e) {
		System.out.println("error");
	}
		return u.get();
		
	}

}
*/

    public ResponseEntity<RequestResponseAgregar> consultarusuario(String Cedula) {

        try {
            Optional<UsuarioModel> u = usuarioR.findById(Cedula);

            if (u.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);

            } else {
                UsuarioModel consulta = u.get();
                RequestResponseAgregar respuesta = new RequestResponseAgregar();
                respuesta.setCedula_Usuario(consulta.getCedula_Usuario());
                respuesta.setClave_Usuario(consulta.getClave_Usuario());
                respuesta.setCorreo_Usuario(consulta.getCorreo_Usuario());
                respuesta.setNombre_Usuario(consulta.getNombre_Usuario());
                respuesta.setUsuario(consulta.getUsuario());
                respuesta.setFecha_Ingreso(consulta.getFecha_Ingreso());
                return new ResponseEntity<>(respuesta, HttpStatus.OK);
            }

        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }


    }


    @Override
    public Optional<List<UsuarioModel>> consultafiltros(FiltrosDto request) {
        try {
            Optional<List<UsuarioModel>>consultaData = usuarioR.consultaUsuariosFiltros(
                    request.getFiltros().getFecha_Ingreso(),request.getFiltros().getFecha_salida(),
                    request.getFiltros().getNombre_Usuario());

            return consultaData;

        } catch (Exception e) {
            System.out.println("Exception occurred: " + e.getMessage());
        }

        return null;
    }




}