/**
 * 
 */
package co.edu.poli.ingesoft2.granreto;

/**
 * @author Luis Castillo
 *
 */
public class DefaultArmadorEquipo implements IArmadorPartido {
	
	public static int PUNTOS_GANAR = 3;
	public static int PUNTOS_EMPATE = 1;
	public static int PUNTOS_PERDER = 0;
	

	/* (non-Javadoc)
	 * @see co.edu.poli.ingesoft2.granreto.IArmadorPartido#validarDatoPosicion(java.lang.String, int)
	 */
	@Override
	public boolean validarDatoPosicion(String dato, int pos) {
		Integer temp = null;
			try {
				temp = Integer.parseInt(dato);
				if(temp<0 || temp>15){
					return false;
				}
			} catch (NumberFormatException e) {
				if(pos==2 || pos==4){//Es una posición para marcador del partido.
					return false;
				}else{
					if(dato!=null){
						if(dato.equals("")){
							return false;
						}
					}
				}
				
			}
			return true;
	}

	/* (non-Javadoc)
	 * @see co.edu.poli.ingesoft2.granreto.IArmadorPartido#armarEquipo(co.edu.poli.ingesoft2.granreto.Partido, co.edu.poli.ingesoft2.granreto.Equipo)
	 */
	@Override
	public Partido armarEquipos(Partido p, Equipo local, Equipo visitante) throws GranRetoException {
		//Se evalua primero cual equipo es al que se le calculara 
		int golesLocal = p.getMarcadorLocal();
		int golesVisit = p.getMarcadorVisitante();
		//Se revisa el resultado del juego.
		
		if(golesLocal == golesVisit){//Empate
			local.setPartidosEmpatados(local.getPartidosEmpatados()+1);
			visitante.setPartidosEmpatados(visitante.getPartidosEmpatados()+1);
			local.setPuntos(local.getPuntos()+PUNTOS_EMPATE);
			visitante.setPuntos(visitante.getPuntos()+PUNTOS_EMPATE);
		}else if(golesLocal <= golesVisit){//Pierde el local, gana el visitante.
			local.setPartidosPerdidos(local.getPartidosPerdidos()+1);
			visitante.setPartidosGanados(visitante.getPartidosGanados()+1);
			local.setPuntos(local.getPuntos()+PUNTOS_PERDER);
			visitante.setPuntos(visitante.getPuntos()+PUNTOS_GANAR);
		}else{//Ganó el local...
			visitante.setPartidosPerdidos(visitante.getPartidosPerdidos()+1);
			local.setPartidosGanados(local.getPartidosGanados()+1);
			local.setPuntos(local.getPuntos()+PUNTOS_GANAR);
			visitante.setPuntos(visitante.getPuntos()+PUNTOS_PERDER);
		}
		
		//Se guardan los goles marcados...
		local.setGolesFavor(local.getGolesFavor()+golesLocal);
		visitante.setGolesFavor(visitante.getGolesFavor()+golesVisit);
		local.setGolesContra(local.getGolesContra()+golesVisit);
		visitante.setGolesContra(visitante.getGolesContra()+golesLocal);
		local.setPartidosJugados(local.getPartidosJugados()+1);
		visitante.setPartidosJugados(visitante.getPartidosJugados()+1);
		
		//Se actualiza los objetos del partido..
		p.setLocal(local);
		p.setVisitante(visitante);
		return p;
	}

}
