/**
 * 
 */
package co.edu.poli.ingesoft2.granreto;

/**
 * @author Luis Castillo
 * Interfaz que declara los métodos para validar la línea de un archivo de partidos.
 */
public interface IValidadorLineaArchivo {

	boolean validarLinea(String linea) throws GranRetoException;
}
