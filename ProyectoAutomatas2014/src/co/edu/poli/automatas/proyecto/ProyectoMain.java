/**
 * 
 */
package co.edu.poli.automatas.proyecto;

import co.edu.poli.automatas.proyecto.enums.OperadorLogicoEnum;
import co.edu.poli.automatas.proyecto.exception.InvalidArgumentException;
import co.edu.poli.automatas.proyecto.exception.ProyectoAutomatasException;
import co.edu.poli.automatas.proyecto.servicios.OperadorLogicoService;

/**
 * @author Luis Castillo
 * Clase principal que inica la interfaz del menu para el proyecto de operadores lógicos.
 */
public class ProyectoMain {
	
	private static OperadorLogicoService service = new OperadorLogicoService();

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		
		//Se inicializa el programa....
		boolean valoresVariableA[] = new boolean[]{false, false, true, true};
		boolean valoresVariableB[] = new boolean[]{true, false, true, false};
		
		
		//Se prueban los servicios del operador lógico.
		System.out.println("******* Proyecto Automatas primer 5% *******");
		System.out.println("--------- PRUEBA DE OPERADORES LOGICOS --------");
		//funcion negar:
		System.out.println("* Función de negación: ");
		System.out.println("  - Probando con 2 variables: ");
		for(int i=0; i<valoresVariableA.length;i++){
			System.out.println("    Entradas: ");
			System.out.println("   a= "+ valoresVariableA[i] + ", b= "+ valoresVariableB[i]+" \n");
			System.out.println("    Resultado: ");
			try {
				System.out.println("   a= "+ service.evaluate(OperadorLogicoEnum.NEGACION.getId(),valoresVariableA[i], false) + 
						", b= "+ service.evaluate(OperadorLogicoEnum.NEGACION.getId(),valoresVariableB[i], false)+" \n");
			} catch (InvalidArgumentException e) {
				System.out.println("    Error al realizar la operación: "+e.getMessage());
			} catch (ProyectoAutomatasException e) {
				System.out.println("    Error al realizar la operación: "+e.getMessage());
			}
		}
		
		//funcion AND:
		llamarServicio(OperadorLogicoEnum.AND, valoresVariableA, valoresVariableB);
		
		//funcion OR:
		llamarServicio(OperadorLogicoEnum.OR, valoresVariableA, valoresVariableB);
		
		//funcion Conditional:
		llamarServicio(OperadorLogicoEnum.CONDITIONAL, valoresVariableA, valoresVariableB);
		
		//funcion XOR:
		llamarServicio(OperadorLogicoEnum.XOR, valoresVariableA, valoresVariableB);	
		
		//funcion Biconditional:
		llamarServicio(OperadorLogicoEnum.BICONDITIONAL, valoresVariableA, valoresVariableB);
		
		//funcion Reciproca:
		llamarServicio(OperadorLogicoEnum.RECIPROCA, valoresVariableA, valoresVariableB);
		
		//funcion Contrareciproca:
		llamarServicio(OperadorLogicoEnum.CONTRARECIPROCA, valoresVariableA, valoresVariableB);
		
		//funcion Contrareciproca:
		llamarServicio(OperadorLogicoEnum.INVERSA, valoresVariableA, valoresVariableB);
		
	}
	
	public static void llamarServicio(OperadorLogicoEnum operador, boolean valoresVariableA[], boolean valoresVariableB[]){
		System.out.println("* Función "+ operador.getNombre() +": ");
		System.out.println("  - Probando con 2 variables: ");
		for(int i=0; i<valoresVariableA.length;i++){
			System.out.println("    Entradas: ");
			System.out.println("   a= "+ valoresVariableA[i] + ", b= "+ valoresVariableB[i]+" \n");
			try {
				System.out.println("    Resultado: "+service.evaluate(operador.getId(),valoresVariableA[i], valoresVariableB[i]));
			} catch (InvalidArgumentException | ProyectoAutomatasException e) {
				System.out.println("    Error al realizar la operación: "+e.getMessage());
			}
		}
	}

}
