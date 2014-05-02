/**
 * 
 */
package co.edu.poli.ingesoft2.granreto;

/**
 * @author Luis Castillo
 * Clase que representa un equipo en el campeonato.
 */
public class Equipo {

	private String nombre = "Equipo sin Nombre";
	
	private int puntos = 0;
	
	private int partidosJugados = 0;
	
	private int partidosGanados = 0;
	
	private int partidosEmpatados = 0;
	
	private int partidosPerdidos = 0;
	
	private int golesFavor = 0;
	
	private int golesContra = 0;
	
	/**
	 * Constructor por nombre de equipo.
	 * @param nombre el nombre del equipo a crear.
	 */
	public Equipo(String nombre){
		this.nombre = nombre;
	}

	/**
	 * @return the nombre
	 */
	public String getNombre() {
		return nombre;
	}

	/**
	 * @param nombre the nombre to set
	 */
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	/**
	 * @return the puntos
	 */
	public int getPuntos() {
		return puntos;
	}

	/**
	 * @param puntos the puntos to set
	 */
	public void setPuntos(int puntos) {
		this.puntos = puntos;
	}

	/**
	 * @return the partidosJugados
	 */
	public int getPartidosJugados() {
		return partidosJugados;
	}

	/**
	 * @param partidosJugados the partidosJugados to set
	 */
	public void setPartidosJugados(int partidosJugados) {
		this.partidosJugados = partidosJugados;
	}

	/**
	 * @return the partidosGanados
	 */
	public int getPartidosGanados() {
		return partidosGanados;
	}

	/**
	 * @param partidosGanados the partidosGanados to set
	 */
	public void setPartidosGanados(int partidosGanados) {
		this.partidosGanados = partidosGanados;
	}

	/**
	 * @return the partidosEmpatados
	 */
	public int getPartidosEmpatados() {
		return partidosEmpatados;
	}

	/**
	 * @param partidosEmpatados the partidosEmpatados to set
	 */
	public void setPartidosEmpatados(int partidosEmpatados) {
		this.partidosEmpatados = partidosEmpatados;
	}

	/**
	 * @return the partidosPerdidos
	 */
	public int getPartidosPerdidos() {
		return partidosPerdidos;
	}

	/**
	 * @param partidosPerdidos the partidosPerdidos to set
	 */
	public void setPartidosPerdidos(int partidosPerdidos) {
		this.partidosPerdidos = partidosPerdidos;
	}

	/**
	 * @return the golesFavor
	 */
	public int getGolesFavor() {
		return golesFavor;
	}

	/**
	 * @param golesFavor the golesFavor to set
	 */
	public void setGolesFavor(int golesFavor) {
		this.golesFavor = golesFavor;
	}

	/**
	 * @return the golesContra
	 */
	public int getGolesContra() {
		return golesContra;
	}

	/**
	 * @param golesContra the golesContra to set
	 */
	public void setGolesContra(int golesContra) {
		this.golesContra = golesContra;
	}
}
