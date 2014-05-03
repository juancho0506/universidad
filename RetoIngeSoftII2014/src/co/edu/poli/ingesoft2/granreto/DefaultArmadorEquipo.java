/**
 * 
 */
package co.edu.poli.ingesoft2.granreto;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

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

	/* (non-Javadoc)
	 * @see co.edu.poli.ingesoft2.granreto.IArmadorPartido#ordenarEquipos(java.util.Map)
	 */
	@Override
	public List<Equipo> ordenarEquipos(Map<String, Equipo> equipos)
			throws GranRetoException {
		
		List<Equipo> actuales = null;
		//List<Equipo> consultados = new ArrayList<Equipo>();
		List<Equipo> ordenados = new ArrayList<Equipo>();
		if(equipos!=null){
			actuales = new ArrayList<Equipo>(equipos.values());
			//consultados = new ArrayList<Equipo>(actuales);
		}		
		
		//Se recorre la lista de actuales para evaluarlos.
		Equipo mayor = null;
		if(actuales!=null){
			if(actuales.size()>0){
				//mayor = actuales.get(0);
				for(int i=0;i<actuales.size();i++){
					if(!ordenados.contains(actuales.get(i))){
						mayor = actuales.get(i);
					}
					//System.out.println("El mayor valor es: " + mayor.getNombre()+"-"+mayor.getPuntos());
					for(int j=0;j<actuales.size();j++){
						if(!ordenados.contains(actuales.get(j))){ //Si ya fue ordenado saltelo.
							//OJO esta validación ocurre sólo cuando queda el último elemento que no se puede
							//comparar con el mismo.
							if(actuales.size()-ordenados.size()==1){
								mayor = actuales.get(j);
							}else{
								//Si es el mismo equipo no se debe validar nada...
								if(!(actuales.get(j).getNombre().equals(mayor.getNombre()))){
									if(actuales.get(j).getPuntos()>mayor.getPuntos()){ //Si es mayor remplace el mayor anterior.
										mayor=actuales.get(j);
									}else if(actuales.get(j).getPuntos()==mayor.getPuntos()){//Si estan iguales en puntaje hay que desempatar.
										//se decide el desempate de los equipos..
										//mayor=actuales.get(j);
										mayor=this.decidirDesempate(actuales.get(j), mayor);
									}
								}								
							}							
						}
					}
					
					ordenados.add(mayor);
				}
			}			
		}		
		
		
		return ordenados;
	}

	/* (non-Javadoc)
	 * @see co.edu.poli.ingesoft2.granreto.IArmadorPartido#decidirDesempate(co.edu.poli.ingesoft2.granreto.Equipo, co.edu.poli.ingesoft2.granreto.Equipo)
	 */
	@Override
	public Equipo decidirDesempate(Equipo a, Equipo b) throws GranRetoException {

		//Se valida por mayor diferencia de goles.
		int difEquipoA = a.getGolesFavor()-a.getGolesContra();
		int difEquipoB = b.getGolesFavor()-b.getGolesContra();
		
		if(difEquipoA>difEquipoB){
			return a;
		}else if(difEquipoB>difEquipoA){
			return b;
		}else{ //igual diferencia de goles
			//Mayor partidos ganados..
			if(a.getPartidosGanados()>b.getPartidosGanados()){
				return a;
			}else if(b.getPartidosGanados()>a.getPartidosGanados()){
				return b;
			}else{
				//Numero total de goles a favor..
				if(a.getGolesFavor()>b.getGolesFavor()){
					return a;
				}else if(b.getGolesFavor()>a.getGolesFavor()){
					return b;
				}
			}
		}
		
		return a;
	}
	
	

}
