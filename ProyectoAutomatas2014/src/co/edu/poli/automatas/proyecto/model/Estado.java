/**
 * 
 */
package co.edu.poli.automatas.proyecto.model;

/**
 * @author Rodrigo
 * Clase 
 */
public class Estado {
	
	private int numEstado;
	
	boolean estadoFinal;

	/**
	 * @return the numEstado
	 */
	public int getNumEstado() {
		return numEstado;
	}

	/**
	 * @param numEstado the numEstado to set
	 */
	public void setNumEstado(int numEstado) {
		this.numEstado = numEstado;
	}

	/**
	 * @return the estadoFinal
	 */
	public boolean isEstadoFinal() {
		return estadoFinal;
	}

	/**
	 * @param estadoFinal the estadoFinal to set
	 */
	public void setEstadoFinal(boolean estadoFinal) {
		this.estadoFinal = estadoFinal;
	}
}
