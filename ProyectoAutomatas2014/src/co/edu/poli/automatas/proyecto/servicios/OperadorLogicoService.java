/**
 * 
 */
package co.edu.poli.automatas.proyecto.servicios;

import co.edu.poli.automatas.proyecto.enums.OperadorLogicoEnum;
import co.edu.poli.automatas.proyecto.exception.InvalidArgumentException;
import co.edu.poli.automatas.proyecto.exception.ProyectoAutomatasException;

/**
 * @author Luis Castillo
 * Clase que ofrece los servicios de las funciones para el proyecto del operador lógico.
 */
public class OperadorLogicoService {
	
	/**
	 * Ejecuta una acción dado el id operador y las preposiciones dadas 
	 * con valores booleanos.
	 * @param id
	 * @param p1
	 * @param p2
	 * @return
	 */
	public boolean evaluate(Integer id, boolean p1, boolean p2) throws InvalidArgumentException,ProyectoAutomatasException{
		
		if(id!=null){
			if(id == OperadorLogicoEnum.NEGACION.getId()){
				
				return this.negate(p1);
				
			}else if(id == OperadorLogicoEnum.AND.getId()){
				
				return this.evaluateAND(p1, p2);
				
			}else if(id == OperadorLogicoEnum.OR.getId()){
				
				return this.evaluateOR(p1, p2);
				
			}else if(id == OperadorLogicoEnum.CONDITIONAL.getId()){
				
				return this.evaluateConditional(p1, p2);
				
			}else if(id == OperadorLogicoEnum.XOR.getId()){
				
				return this.evaluateXOR(p1, p2);
				
			}else if(id == OperadorLogicoEnum.BICONDITIONAL.getId()){
				
				return this.evaluateBiconditional(p1, p2);
				
			}else if(id == OperadorLogicoEnum.RECIPROCA.getId()){
				
				return this.evaluateReciproca(p1, p2);
				
			}else if(id == OperadorLogicoEnum.CONTRARECIPROCA.getId()){
				
				return this.evaluateContrareciproca(p1, p2);
				
			}else if(id == OperadorLogicoEnum.INVERSA.getId()){
				
				return this.evaluateInversaCond(p1, p2);
			}
		}else{
			throw new InvalidArgumentException("Argumento invalido. Operación inválida.");
		}
		
		
		return false;
	}
	
	
	/**
	 * Niega una proposición dada.
	 * @param p1
	 * @return
	 */
	public boolean negate(boolean p1){
		return !p1;
	}
	
	/**
	 * Evalua la funcion AND para dos valores de verdad dados.
	 * @param p1
	 * @param p2
	 * @return
	 */
	public boolean evaluateAND(boolean p1, boolean p2){
		
		if(p1 && p2){
			return true;
		}
		return false;
	}
	
	/**
	 * Evalua la funcion OR para dos valores de verdad dados.
	 * @param p1
	 * @param p2
	 * @return
	 */
	public boolean evaluateOR(boolean p1, boolean p2){
		
		if(p1 && p2){
			return true;
		}else{
			if(p1){
				return true;
			}
			if(p2){
				return true;
			}
		}
		return false;
	}
	
	/**
	 * Evalua la funcion Conditional para dos valores de verdad dados.
	 * @param p1
	 * @param p2
	 * @return
	 */
	public boolean evaluateConditional(boolean p1, boolean p2){
		
		if(!p2){
			return false;
		}else{
			return true;
		}
	}
	
	/**
	 * Evalua la funcion XOR para dos valores de verdad dados.
	 * @param p1
	 * @param p2
	 * @return
	 */
	public boolean evaluateXOR(boolean p1, boolean p2){
		if(p1){
			return true;
		}
		else if(p2){
			return true;
		}
		return false;
	}
	
	/**
	 * Evalua la funcion Conditional para dos valores de verdad dados.
	 * @param p1
	 * @param p2
	 * @return
	 */
	public boolean evaluateBiconditional(boolean p1, boolean p2){
		
		if(p1!=p2){
			return false;
		}else{
			return true;
		}
	}
	
	/**
	 * Evalua la funcion Reciproca del condicional para dos valores de verdad dados.
	 * @param p1
	 * @param p2
	 * @return
	 */
	public boolean evaluateReciproca(boolean p1, boolean p2){
		if(!p1){
			return false;
		}else{
			return true;
		}
	}
	
	/**
	 * Evalua la funcion Contrareciproca del condicional para dos valores de verdad dados.
	 * @param p1
	 * @param p2
	 * @return
	 */
	public boolean evaluateContrareciproca(boolean p1, boolean p2){
		if(p1){
			return false;
		}else{
			return true;
		}
	}
	
	/**
	 * Evalua la funcion Inversa del condicional para dos valores de verdad dados.
	 * @param p1
	 * @param p2
	 * @return
	 */
	public boolean evaluateInversaCond(boolean p1, boolean p2){
		if(p2){
			return false;
		}else{
			return true;
		}
	}

}
