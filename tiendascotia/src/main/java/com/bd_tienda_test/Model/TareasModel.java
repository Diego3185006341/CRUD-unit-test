package com.bd_tienda_test.Model;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
@Entity
@Table(schema="bd_tienda_test", name="tabla_Tareas")
public class TareasModel {
	@Id
	@Column(name="id_tarea",unique=true) 
	public String id_Tarea;

	@Column(name="nombre_tarea") 
	public String nombre_Tarea;
	
	@Column(name="mes_entrega") 
	public String mes_Entrega;
}
