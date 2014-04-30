/**
 * 
 */
package co.edu.poli.ingesoft2.granreto;

/**
 * @author Luis Castillo
 * Interfaz con los m�todos definidos para la aplicaci�n del Gran Reto.
 */
public interface IFachadaGranReto {

	/**
	 * M�todo que carga un archivo enviando la rura por par�metro, 
	 * lee los datos del archivo y los almacena para obtener los resultados.
	 * @param rutaArchivo La ruta del archivo a leer.
	 * @throws GranRetoException Si ocurre un error de validaci�n, lectura o de ejecuci�n al leer el archivo.
	 */
	public void cargarArchivo( String rutaArchivo ) throws GranRetoException;
	
	/**
	 * Devuelve un String con la informaci�n para mostrar una tabla de posiciones y resultados del torneo.
	 * @return String con el formato para mostrar por pantalla la tabla de posiciones de los equipos.
	 * @throws GranRetoException Si ocurre algun error en el proceso.
	 */
	public String obtenerTabla() throws GranRetoException;
}
