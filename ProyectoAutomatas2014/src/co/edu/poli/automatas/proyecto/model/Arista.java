/**
 * 
 */
package co.edu.poli.automatas.proyecto.model;

/**
 * @author Luis Castillo
 * Clase que representa una arista o transición en el automata finito no determinista.
 */
public class Arista {
	
	private Estado origen;
	
	private Estado destino;
	
	private String simbolo = "";

	

	/**
	 * @return the origen
	 */
	public Estado getOrigen() {
		return origen;
	}



	/**
	 * @param origen the origen to set
	 */
	public void setOrigen(Estado origen) {
		this.origen = origen;
	}



	/**
	 * @return the destino
	 */
	public Estado getDestino() {
		return destino;
	}



	/**
	 * @param destino the destino to set
	 */
	public void setDestino(Estado destino) {
		this.destino = destino;
	}



	/**
	 * @return the simbolo
	 */
	public String getSimbolo() {
		return simbolo;
	}



	/**
	 * @param simbolo the simbolo to set
	 */
	public void setSimbolo(String simbolo) {
		this.simbolo = simbolo;
	}



	/* (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if(obj != null && obj instanceof Arista){
			Arista temp = (Arista) obj;
			if(temp.getOrigen().equals(this.getOrigen()) && temp.getDestino().equals(this.getDestino()) && temp.getSimbolo().equals(this.getSimbolo())){
				return true;
			}
		}
		return false;
	}
	
	/**
	 * Se compara con otra arista pero verificando que tengan el mismo origen pero diferente destino
	 * es decir que identifica si para una misma arista del mismo origen hay dos destinos.
	 * @param obj La arista a comparar.
	 * @return true si es cierto false de lo contrario.
	 */
	public boolean equalsByOrigin(Object obj){
		if(obj != null && obj instanceof Arista){
			Arista temp = (Arista) obj;
			if(temp.getOrigen().equals(this.getOrigen()) && temp.getSimbolo().equals(this.getSimbolo())){
				return true;
			}
		}
		return false;
	}
	
	/**
	 * Compara la cadena vacia con el simbolo de la transicion 
	 * para saber si tiene transiciones vacias.
	 * @param cadenaVacia
	 * @return
	 */
	public boolean tieneTransicionVacia(String cadenaVacia){
		if(this.simbolo.equals(cadenaVacia)){
			return true;
		}
		return false;
	}
}
