/**
 * 
 */
package co.edu.poli.ingesoft2.granreto;

/**
 * @author Luis Castillo
 * Interfaz con los métodos definidos para la aplicación del Gran Reto.
 */
public interface IFachadaGranReto {

	/**
	 * Método que carga un archivo enviando la rura por parámetro, 
	 * lee los datos del archivo y los almacena para obtener los resultados.
	 * @param rutaArchivo La ruta del archivo a leer.
	 * @throws GranRetoException Si ocurre un error de validación, lectura o de ejecución al leer el archivo.
	 */
	public void cargarArchivo( String rutaArchivo ) throws GranRetoException;
	
	/**
	 * Devuelve un String con la información para mostrar una tabla de posiciones y resultados del torneo.
	 * @return String con el formato para mostrar por pantalla la tabla de posiciones de los equipos.
	 * @throws GranRetoException Si ocurre algun error en el proceso.
	 */
	public String obtenerTabla() throws GranRetoException;
}
