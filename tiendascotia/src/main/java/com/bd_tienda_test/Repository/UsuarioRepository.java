package com.bd_tienda_test.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;

import com.bd_tienda_test.Model.UsuarioModel;

@Repository
public interface UsuarioRepository extends JpaRepository<UsuarioModel, String> {
	
	@Query(value = "SELECT U FROM UsuarioModel U " +
			"WHERE (U.fecha_Ingreso BETWEEN :Fecha_Ingreso AND :Fecha_Salida) "+
	        "OR (U.nombre_Usuario = :Nombre_Usuario) " )
	Optional<List<UsuarioModel>> consultaUsuariosFiltros(LocalDate Fecha_Ingreso, LocalDate Fecha_Salida,String Nombre_Usuario);

	/*@Query(value="select cm from tabla_Usuarios cm"8
			+ " where (cm.cedula_usuario=:cedula_usuario or :cedula_usuario is null)"
			+ "and (cm.nombre_usuario=:nombre_usuario or :nombre_usuario is null)"
			)*/

	
	
	
}
