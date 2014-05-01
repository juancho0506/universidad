/**
 * 
 */
package co.edu.poli.ingesoft2.granreto;

/**
 * @author Luis Castillo
 *
 */
public class DefaultArmadorEquipo implements IArmadorPartido {

	/* (non-Javadoc)
	 * @see co.edu.poli.ingesoft2.granreto.IArmadorPartido#validarDatoPosicion(java.lang.String, int)
	 */
	@Override
	public boolean validarDatoPosicion(String dato, int pos) {
		Integer temp = null;
			try {
				temp = Integer.parseInt(dato);
			} catch (NumberFormatException e) {
				if(pos==2 || pos==4){//Es una posición para marcador del partido.
					return false;
				}
				
			}
			return true;
	}

}
