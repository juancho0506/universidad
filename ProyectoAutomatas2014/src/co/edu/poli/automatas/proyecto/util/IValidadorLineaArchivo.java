/**
 * 
 */
package co.edu.poli.automatas.proyecto.util;

import co.edu.poli.automatas.proyecto.exception.InvalidArgumentException;

/**
 * @author Luis Castillo
 * Interfaz que declara los m�todos para validar la l�nea de un archivo de partidos.
 */
public interface IValidadorLineaArchivo {

	boolean validarLinea(String linea) throws InvalidArgumentException;
}
