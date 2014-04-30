/**
 * 
 */
package co.edu.poli.ingesoft2.granreto;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Luis Castillo
 * Clase que administra los procesos y datos relacionados con el torneo del Gran Reto.
 */
public class AdministradorTorneo {
	
	public static String KEY_SEPARATOR = "***";
	
	/** Mapa que guarda el total de partidos almacenados por la app.
	 *  La <b>llave</b> para encontrar dicho partido esta compuesta por el nombre del 
	 *  equipo local seguido del separador '***' y luego el nombre del equipo visitante.
	 *  Por ejemplo: equipo 1***equipo 2, seria una llave.
	 **/
	private Map<String, Partido> partidos = new HashMap<String, Partido>();
	
	/** Guarda el estado de los equipos para generar la tabla de posiciones. **/
	private Map<String, Equipo> equipos = new HashMap<String, Equipo>();
	
	
	
	public void registrarPartido(Partido partido) throws GranRetoException{
		partidos.put(partido.getLocal()+this.KEY_SEPARATOR+partido.getVisitante(), partido);
	}
	
	public void generarResultadosEquipos(){
		
	}
	
}
