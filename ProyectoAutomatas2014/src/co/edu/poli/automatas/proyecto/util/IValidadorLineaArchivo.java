/**
 * 
 */
package co.edu.poli.automatas.proyecto.util;

import co.edu.poli.automatas.proyecto.exception.InvalidArgumentException;

/**
 * @author Luis Castillo
 * Interfaz que declara los métodos para validar la línea de un archivo de partidos.
 */
public interface IValidadorLineaArchivo {

	boolean validarLinea(String linea) throws InvalidArgumentException;
}
