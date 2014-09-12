/**
 * 
 */
package co.edu.poli.automatas.proyecto;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import co.edu.poli.automatas.proyecto.exception.InvalidArgumentException;
import co.edu.poli.automatas.proyecto.exception.ProyectoAutomatasException;
import co.edu.poli.automatas.proyecto.model.Estado;
import co.edu.poli.automatas.proyecto.util.DefaultValidadorLineaArchivo;
import co.edu.poli.automatas.proyecto.util.IValidadorLineaArchivo;

/**
 * @author Luis Castillo
 * Clase principal que arranca el programa de lectura de un automata.
 */
public class AutomataMain {

	//Datos iniciales...
	/** Numero de estados del automata, Numero N del archivo **/
	private static int numEstados = 0;
	/** Cardinalidad del alfabeto, Numero Z del archivo **/
	private static int numAlfabeto = 0;
	/** Cantidad de estados finales, Numero F del archivo **/
	private static int numEstadosFinales = 0;
	/** Cantidad de consultas a realizar, Numero Q del archivo **/
	private static int numConsultas = 0;
	/** Matriz de estados del automata **/
	private static String matrizEstados[][];
	private static List<Estado> estadosFinales = new ArrayList<Estado>();
	private static List<String> alfabeto = new ArrayList<String>();
	private static List<String> consultas = new ArrayList<String>();
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		
		//se verifican los argumentos del programa
		if(args!=null){
			if(args.length>0){
				String opcion = args[0];
				try {
					System.out.println("****** Lectura de Automata desde Archivo ***************");
					System.out.println("* Leyendo archivo .......");
					leerArchivo("files/test1.txt");
					System.out.println("* Termina la carga del archivo!");
					imprimirLecturaArchivo();
				} catch (InvalidArgumentException e) {
					System.err.println(e.getMessage());
				} catch (ProyectoAutomatasException e) {
					System.err.println(e.getMessage());
				}
			}else{
				System.err.println("ERROR: Debe enviar un párametro con la opción a ejecutar [1-2].");
			}
		}else{
			System.err.println("ERROR: Debe enviar un párametro con la opción a ejecutar [1-2].");
		}
	}

	private static void leerArchivo(String path) throws InvalidArgumentException, ProyectoAutomatasException{
		
		BufferedReader br = null;
		/** Validador encargado de verificar cada línea que se lea del archivo. */
		IValidadorLineaArchivo validadorLinea = new DefaultValidadorLineaArchivo();
		
		String lineaActual = null;
		File archivo = null;
		
		try {
			//Se carga el archivo.
			archivo = new File(path);
			br = new BufferedReader(new FileReader(archivo));
			//Se recorre las líneas del archivo..
			int contLinea = 1;
			int filaActualmatriz = 0;
			int lineaFinalMatriz = 0;
			while((lineaActual=br.readLine())!=null){
				//System.out.println("Leyendo línea: " + lineaActual);
				//Se quitan los espacios al principio y fin.
				lineaActual = lineaActual.trim();
				lineaActual = lineaActual.replaceAll("  ", " ");
				if(!lineaActual.equals("")){
					if(validadorLinea.validarLinea(lineaActual)){
						//Comienza la lectura del archivo..
						//1- Sacar el N y Z
						String[] datosLinea = lineaActual.split(" ");
						if(datosLinea!=null){
							if(datosLinea.length>0){
								//Paso 1: Se valida que no se este leyendo las filas de la matriz
								if(contLinea < 3){
									//Se llama al método que guarda los datos de la línea.
									extraerDatosBasicosLinea(datosLinea, contLinea);
								}else{
									//Paso 2: Se lee la matriz y los datos que van después de ella.
									if(contLinea == 3){
										lineaFinalMatriz = contLinea+numEstados;
										matrizEstados = new String[numEstados][numAlfabeto];
										llenarMatrizEstados(filaActualmatriz, datosLinea);
										filaActualmatriz++;
									}
									if(contLinea>3 && contLinea<lineaFinalMatriz){//Termino de llenar la matriz
										llenarMatrizEstados(filaActualmatriz, datosLinea);
										filaActualmatriz++;
									}else{//si termino de leer la matriz puedo leer las lineas finales.
										extraerDatosFinalesLinea(datosLinea, contLinea, lineaFinalMatriz);
									}
								}
								
							}else{
								throw new ProyectoAutomatasException("\nERROR: No se pudo leer los datos de la línea: "+lineaActual);
							}
						}else{
							throw new ProyectoAutomatasException("\nERROR: No se pudo leer los datos de la línea: "+lineaActual);
						}
						
					}else{
						throw new ProyectoAutomatasException("\nERROR: Datos inválidos en la línea: "+lineaActual);
					}
				}else{
					throw new ProyectoAutomatasException("\nERROR: No hay datos en la línea: "+contLinea);
				}
				contLinea++;
			}
			
		} catch (IOException e) {
			throw new ProyectoAutomatasException("Error de lectura del archivo: "+
					(archivo!=null?archivo.getAbsolutePath():archivo));
		}catch (ProyectoAutomatasException e){
			System.err.println("\n Hubo un error en el proceso de lectura del archivo, no se pudo cargar los datos.");
			throw e;
		}finally{
			try {
				if (br != null){
					br.close();
				}
				archivo = null;
			} catch (IOException ex) {
				System.err.println("\n ERROR de lectura en el Archivo");
			}
		}
		
	}
	
	/**
	 * Extrae los datos básicos en la lectura inicial del arhivo
	 * antes de llegar a leer la matriz.
	 * @param datosLinea los datos ya guardados en el arreglo extraido de la línea.
	 * @param contLinea contador para saber que linea se esta leyendo
	 * @throws ProyectoAutomatasException
	 */
	private static void extraerDatosBasicosLinea(String[] datosLinea, int contLinea) throws ProyectoAutomatasException{
		//Se extre primero los numeros N y Z
		if(contLinea==1){
			try {
				numAlfabeto = Integer.parseInt(datosLinea[0]);
				numEstados = Integer.parseInt(datosLinea[1]);
			}catch (NumberFormatException e) {
				throw new ProyectoAutomatasException("\n ERROR: Debe ingresar solo números para la línea: "+contLinea+"\n"+e.getMessage());
			}catch (ArrayIndexOutOfBoundsException e){
				throw new ProyectoAutomatasException("\n ERROR: Faltan datos para procesar la línea: "+contLinea+"\n"+e.getMessage());
			}
			
		}else{//Se leen los simbolos del alfabeto.
			alfabeto = new ArrayList<String>(numAlfabeto);
			try {
				for(int i=0; i<numAlfabeto; i++){
					alfabeto.add(datosLinea[i]);
				}
			}catch (ArrayIndexOutOfBoundsException e){
				throw new ProyectoAutomatasException("\n ERROR: Faltan datos para procesar la línea: "+contLinea+"\n"+e.getMessage());
			}
			
		}
	}
	
	/**
	 * Llena una fila de la matriz de estados dada la fila actual en el contador
	 * del lector de archivos.
	 * @param filaActualmatriz
	 */
	private static void llenarMatrizEstados(int filaActualmatriz, String[] datosLinea)throws ProyectoAutomatasException{
		try {
			for(int i=0; i<numAlfabeto; i++){
				matrizEstados[filaActualmatriz][i] = datosLinea[i];
			}
		} catch (ArrayIndexOutOfBoundsException e){
			throw new ProyectoAutomatasException("\n ERROR: No se puede construir la matriz de estados: "+datosLinea+"\n"+e.getMessage());
		}
	}
	
	private static void extraerDatosFinalesLinea(String[] datosLinea, int contLinea, int lineaFinalMatriz) throws ProyectoAutomatasException{
		try {
			
			if(contLinea==lineaFinalMatriz){
				numEstadosFinales = Integer.parseInt(datosLinea[0]);
			}else if(contLinea == lineaFinalMatriz+1){
				estadosFinales = new ArrayList<Estado>(numEstadosFinales);
				for(int i=0; i < numEstadosFinales; i++){
					Estado temp = new Estado();
					temp.setEstadoFinal(true);
					temp.setNumEstado(Integer.parseInt(datosLinea[i]));
					estadosFinales.add(temp);
				}
			}else if(contLinea == lineaFinalMatriz+2){
				numConsultas = Integer.parseInt(datosLinea[0]);
			}else{
				//se leen las consultas
				consultas.add(datosLinea[0]);
				
			}
			
		}catch (NumberFormatException e) {
			throw new ProyectoAutomatasException("\n ERROR: Debe ingresar solo números para la línea: "+contLinea+"\n"+e.getMessage());
		}catch (ArrayIndexOutOfBoundsException e){
			throw new ProyectoAutomatasException("\n ERROR: Faltan datos para procesar la línea: "+contLinea+"\n"+e.getMessage());
		}
	}
	
	private static void imprimirLecturaArchivo()throws ProyectoAutomatasException{
		try {
			System.out.println("**** Automata Cargado ********");
			System.out.println("* N: "+numEstados);
			System.out.println("* Z: "+numAlfabeto);
			System.out.println("* Matriz cargada: ");
			for(int i=0; i < numEstados; i++) {
				System.out.println("|");
				for (int j = 0; j < numAlfabeto; j++) {
					System.out.println(""+matrizEstados[i][j]+"|");
				}
				System.out.println("| \n");
			}
			System.out.println("* F: "+numEstadosFinales);
			System.out.println("* Estados finales: ");
			for (Estado estado : estadosFinales) {
				System.out.println(""+estado.getNumEstado());
			}
			System.out.println("* Q: "+numConsultas);
			System.out.println("* Consultas a realizar: ");
			for (String c : consultas) {
				System.out.println(""+c);
			}
		} catch (Exception e) {
			throw new ProyectoAutomatasException("\n ERROR: Al imprimir los datos leidos del archivo: "+e.getMessage()+"\n"+e.getMessage());
		}
		
	}
}
