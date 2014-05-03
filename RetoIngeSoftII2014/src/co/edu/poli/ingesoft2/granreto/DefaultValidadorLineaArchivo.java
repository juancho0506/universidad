/**
 * 
 */
package co.edu.poli.ingesoft2.granreto;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author Luis Castillo
 * Clase que implementa la interfaz para validar las lineas del archivo de la aplicacion.
 */
public class DefaultValidadorLineaArchivo implements IValidadorLineaArchivo{
	
	private Pattern pattern;
	private Matcher matcher;
	
	private static String VALIDATION_EXPRESSION = "^[a-zA-Z0-9\\s'-]{1,1000}$";

	
	/**
	 * 
	 */
	public DefaultValidadorLineaArchivo() {
		pattern = Pattern.compile(VALIDATION_EXPRESSION);
	}

	/* (non-Javadoc)
	 * @see co.edu.poli.ingesoft2.granreto.ValidadorLineaArchivo#validarLinea(java.lang.String)
	 */
	@Override
	public boolean validarLinea(String linea) throws GranRetoException {
		matcher = pattern.matcher(linea);
		return matcher.matches();
	}

	
}
