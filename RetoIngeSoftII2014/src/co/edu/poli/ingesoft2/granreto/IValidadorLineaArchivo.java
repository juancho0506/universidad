/**
 * 
 */
package co.edu.poli.ingesoft2.granreto;

/**
 * @author Luis Castillo
 * Interfaz que declara los m�todos para validar la l�nea de un archivo de partidos.
 */
public interface IValidadorLineaArchivo {

	boolean validarLinea(String linea) throws GranRetoException;
}
