/**
 * 
 */
package co.edu.poli.ingesoft2.granreto;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Luis Castillo
 * Clase que administra los procesos y datos relacionados con el torneo del Gran Reto.
 */
public class AdministradorTorneo {
	
	public static String KEY_SEPARATOR = "***";
	
	private IArmadorPartido armador = new DefaultArmadorEquipo();
	
	/** Mapa que guarda el total de partidos almacenados por la app.
	 *  La <b>llave</b> para encontrar dicho partido esta compuesta por el nombre del 
	 *  equipo local seguido del separador '***' y luego el nombre del equipo visitante.
	 *  Por ejemplo: equipo 1***equipo 2, seria una llave.
	 **/
	private Map<String, Partido> partidos = new HashMap<String, Partido>();
	
	/** Guarda el estado de los equipos para generar la tabla de posiciones. **/
	private Map<String, Equipo> equipos = new HashMap<String, Equipo>();
	
	
	public void registrarPartido(String[] partido) throws GranRetoException{
		Partido nuevo = new Partido();
		try {
			nuevo.setLocal(new Equipo(partido[0]));
			nuevo.setMarcadorLocal(Integer.parseInt(partido[1]));
			nuevo.setVisitante(new Equipo(partido[2]));
			nuevo.setMarcadorVisitante(Integer.parseInt(partido[3]));
			//Caso en que sea el mismo equipo contra el mismo...
			if(nuevo.getLocal().getNombre().toLowerCase().equals(nuevo.getVisitante().getNombre().toLowerCase())){ 
				throw new GranRetoException("Error: Al registrar el partido: "+partido[0]+" vs " + partido[2]+".\n"
						+"No es válido que un equipo juegue contra el mismo!");
			}
		} catch (NumberFormatException e) {
			throw new GranRetoException("Error al registrar uno de los marcadores para el partido: "+partido[0]+" vs " + partido[2]+".\n"
					+"Por favor revise si el archivo contenia líneas vacías y los datos quedaron inconsistentes.");
		}
		this.registrarPartido(nuevo);
	}
	
	private void registrarPartido(Partido partido) throws GranRetoException{
		partidos.put(partido.getLocal()+KEY_SEPARATOR+partido.getVisitante(), partido);
		//Se registran los equipos si el equipo ya existe se remplaza.
		Equipo local = partido.getLocal();
		Equipo visit = partido.getVisitante();
		if(!equipos.containsKey(local.getNombre().toLowerCase())){
			equipos.put(local.getNombre().toLowerCase(), local);
		}else{
			//lógica de actualización del equipo si se corren varios archivos.
			Equipo temp = equipos.get(local.getNombre().toLowerCase());
			local.setGolesContra(temp.getGolesContra());
			local.setGolesFavor(temp.getGolesFavor());
			local.setPartidosEmpatados(temp.getPartidosEmpatados());
			local.setPartidosGanados(temp.getPartidosGanados());
			local.setPartidosJugados(temp.getPartidosJugados());
			local.setPartidosPerdidos(temp.getPartidosPerdidos());
			local.setPuntos(temp.getPuntos());
			equipos.put(local.getNombre().toLowerCase(), local);
		}
		
		if(!equipos.containsKey(visit.getNombre().toLowerCase())){
			equipos.put(visit.getNombre().toLowerCase(), visit);
		}else{
			//lógica de actualización del equipo si se corren varios archivos.
			Equipo temp = equipos.get(visit.getNombre().toLowerCase());
			visit.setGolesContra(temp.getGolesContra());
			visit.setGolesFavor(temp.getGolesFavor());
			visit.setPartidosEmpatados(temp.getPartidosEmpatados());
			visit.setPartidosGanados(temp.getPartidosGanados());
			visit.setPartidosJugados(temp.getPartidosJugados());
			visit.setPartidosPerdidos(temp.getPartidosPerdidos());
			visit.setPuntos(temp.getPuntos());
			equipos.put(visit.getNombre().toLowerCase(), visit);
		}
	}
	
	public void imprimirPartidosRegistrados(){
		System.out.println("*** Partidos Actuales:   ****");
		for (Partido partido : partidos.values()) {
			System.out.println("* " + partido.getLocal().getNombre()+" vs " + partido.getVisitante().getNombre()+
					" : "+partido.getMarcadorLocal()+ "-" + partido.getMarcadorVisitante());
		}
	}
	
	public void imprimirEquiposRegistrados(){
		System.out.println("*** Equipos Actuales:   ****");
		for (Equipo equipo : equipos.values()) {
			System.out.println("\n* " + equipo.getNombre());
		}
	}
	
	public String generarTablaResultados() throws GranRetoException{
		//Se generan los resultados de los equipos de acuerdo a los partidos.
		this.generarResultadosEquipos();
		//this.imprimirEquiposRegistrados();
		//TODO revisar como ordenar los equipos por puntaje.
		List<Equipo> ordenados = armador.ordenarEquipos(equipos);
		String resultados = "";//"**** TABLA DE POSICIONES **********";
		for (Equipo e : ordenados) {
			resultados+="\n"+e.getNombre().toUpperCase()+","+e.getPuntos()+","+e.getPartidosJugados()+","
					+e.getPartidosGanados()+","+e.getPartidosEmpatados()+","+e.getPartidosPerdidos()
					+","+e.getGolesFavor()+","+e.getGolesContra();
		}
		return resultados;
	}
	
	private void generarResultadosEquipos() throws GranRetoException{
		//this.imprimirPartidosRegistrados();		
		//Primero se actualizan los atributos de los equipos con los resultados acumulados.
		for (Partido p : partidos.values()) {
			Equipo local = equipos.get(p.getLocal().getNombre().toLowerCase());
			Equipo visitante = equipos.get(p.getVisitante().getNombre().toLowerCase());
			p = armador.armarEquipos(p, local, visitante);
			local = p.getLocal();
			visitante = p.getVisitante();
		}
	}
	
}
