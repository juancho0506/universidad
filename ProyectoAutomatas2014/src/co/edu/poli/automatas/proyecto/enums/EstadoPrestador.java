package co.edu.poli.automatas.proyecto.enums;

import java.util.HashMap;
import java.util.Map;

/**
 * Estados del Prestador
 * @author jpicon
 *
 */
public enum EstadoPrestador {

	CARGARDO_MINISTERIO			(1, "Cargado desde Ministerio"),
	CREADO_APLICACION			(2, "Creado desde Aplicaci\u00F3n");

	private Integer id;
	private String description;
	private static Map<Integer, EstadoPrestador> map = null;
	private static Map<String, EstadoPrestador> mapDescripcion = null;
	 
	static {
		EstadoPrestador[] estadosPrestador = EstadoPrestador.values();
		map = new HashMap<Integer, EstadoPrestador>();
		mapDescripcion = new HashMap<String, EstadoPrestador>();
		for (int i = 0; i < estadosPrestador.length; i++) {
			map.put(estadosPrestador[i].id, estadosPrestador[i]);
			mapDescripcion.put(estadosPrestador[i].description, estadosPrestador[i]);
		}
	}
	

	private EstadoPrestador(Integer id, String description) {
		this.id = id;
		this.description = description;
	}

	
	public static EstadoPrestador getById(Integer id) {
		return map.get(id);
	}
	
	public static EstadoPrestador getByDescripcion(String descripcion) {
		return mapDescripcion.get(descripcion);
	}
	
	/**
	 * Devuelve el valor de id.
	 *
	 * @return El valor de id.
	 */
	public Integer getId() {
		return id;
	}


	/**
	 * Asigna un nuevo valor a id.
	 *
	 * @param id El valor a asignar a id.
	 */
	public void setId(Integer id) {
		this.id = id;
	}


	/**
	 * Devuelve el valor de description.
	 *
	 * @return El valor de description.
	 */
	public String getDescription() {
		return description;
	}


	/**
	 * Asigna un nuevo valor a description.
	 *
	 * @param description El valor a asignar a description.
	 */
	public void setDescription(String description) {
		this.description = description;
	}
	
}
