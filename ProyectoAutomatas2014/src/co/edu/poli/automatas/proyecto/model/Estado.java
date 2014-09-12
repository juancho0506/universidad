/**
 * 
 */
package co.edu.poli.automatas.proyecto.model;

/**
 * @author Luis Castillo
 * Clase 
 */
public class Estado {
	
	private int numEstado;
	
	boolean estadoFinal = false;

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

	/* (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if(obj != null && obj instanceof Estado){
			Estado temp = (Estado) obj;
			if(temp.getNumEstado() == this.getNumEstado()){
				return true;
			}
		}
		return false;
	}
}
