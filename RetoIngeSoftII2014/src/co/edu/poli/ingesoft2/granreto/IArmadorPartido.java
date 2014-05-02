/**
 * 
 */
package co.edu.poli.ingesoft2.granreto;

/**
 * @author Luis Castillo
 * Interfaz que define el comportamiento para validar y armar un partido, dada 
 * la posici�n en el archivo para un conjunto de datos que formar�a el partido.
 */
public interface IArmadorPartido {

	/**
	 * Valida el dato enviado para la conformaci�n de un partido dado.
	 * Seg�n la posici�n que se env�e se valida si es un dato v�lido para
	 * conformar el partido de acuerdo a los requerimentos dados en el formato del 
	 * archivo.
	 * @param dato el dato a validar
	 * @param pos la posici�n a validar con el dato.
	 * @return <b>true</b> si el dato es v�lido en esa posici�n del archivo, 
	 * 			<b>false</b> si el dato n� es valido en esa posici�n.
	 */
	boolean validarDatoPosicion(String dato, int pos);
	
	/**
	 * Calcula y arma los equipos dado un partido, con esto devuelve un
	 * partido con los puntajes actualizados para cada uno de los equipos.
	 * @param p El partido usado para sacar los resultados.
	 * @param local equipo local con los resultados anteriores al partido p.
	 * @param visitante El equipo visitante con los resultados anteriores al partido p.
	 * @return El partido con los equipos actualizados.
	 */
	Partido armarEquipos(Partido p, Equipo local, Equipo visitante) throws GranRetoException;
}
