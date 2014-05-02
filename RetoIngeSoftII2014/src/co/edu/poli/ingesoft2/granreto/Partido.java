/**
 * 
 */
package co.edu.poli.ingesoft2.granreto;

/**
 * @author Luis Castillo
 * Clase que representa un Partido registrado en el sistema.
 */
public class Partido {
	
	private Equipo local;
	
	private Equipo visitante;
	
	private int marcadorLocal;
	
	private int marcadorVisitante;

	/**
	 * @return the local
	 */
	public Equipo getLocal() {
		return local;
	}

	/**
	 * @param local the local to set
	 */
	public void setLocal(Equipo local) {
		this.local = local;
	}

	/**
	 * @return the visitante
	 */
	public Equipo getVisitante() {
		return visitante;
	}

	/**
	 * @param visitante the visitante to set
	 */
	public void setVisitante(Equipo visitante) {
		this.visitante = visitante;
	}

	/**
	 * @return the marcadorLocal
	 */
	public int getMarcadorLocal() {
		return marcadorLocal;
	}

	/**
	 * @param marcadorLocal the marcadorLocal to set
	 */
	public void setMarcadorLocal(int marcadorLocal) {
		this.marcadorLocal = marcadorLocal;
	}

	/**
	 * @return the marcadorVisitante
	 */
	public int getMarcadorVisitante() {
		return marcadorVisitante;
	}

	/**
	 * @param marcadorVisitante the marcadorVisitante to set
	 */
	public void setMarcadorVisitante(int marcadorVisitante) {
		this.marcadorVisitante = marcadorVisitante;
	}
	
	
}
