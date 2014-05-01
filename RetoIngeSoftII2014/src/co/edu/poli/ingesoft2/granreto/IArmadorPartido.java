/**
 * 
 */
package co.edu.poli.ingesoft2.granreto;

/**
 * @author Luis Castillo
 * Interfaz que define el comportamiento para validar y armar un partido, dada 
 * la posición en el archivo para un conjunto de datos que formaría el partido.
 */
public interface IArmadorPartido {

	/**
	 * Valida el dato enviado para la conformación de un partido dado.
	 * Según la posición que se envíe se valida si es un dato válido para
	 * conformar el partido de acuerdo a los requerimentos dados en el formato del 
	 * archivo.
	 * @param dato el dato a validar
	 * @param pos la posición a validar con el dato.
	 * @return <b>true</b> si el dato es válido en esa posición del archivo, 
	 * 			<b>false</b> si el dato nó es valido en esa posición.
	 */
	boolean validarDatoPosicion(String dato, int pos);
}
