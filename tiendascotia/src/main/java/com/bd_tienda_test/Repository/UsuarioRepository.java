package com.bd_tienda_test.Repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.bd_tienda_test.Model.UsuarioModel;

@Repository
public interface UsuarioRepository extends JpaRepository<UsuarioModel, String> {
	
	@Query(value="select * from tabla_Usuarios where (fecha_ingreso between '2023-01-14' and '2023-01-15')",nativeQuery = true	
			)

	List<UsuarioModel> consultaUsuarios(@Param("fecha_ingreso")String nombre_usuario);

	/*@Query(value="select cm from tabla_Usuarios cm"
			+ " where (cm.cedula_usuario=:cedula_usuario or :cedula_usuario is null)"
			+ "and (cm.nombre_usuario=:nombre_usuario or :nombre_usuario is null)"
			)*/

	
	
	
}
