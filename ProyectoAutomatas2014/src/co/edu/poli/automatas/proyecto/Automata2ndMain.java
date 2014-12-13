/**
 * 
 */
package co.edu.poli.automatas.proyecto;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import co.edu.poli.automatas.proyecto.exception.InvalidArgumentException;
import co.edu.poli.automatas.proyecto.exception.ProyectoAutomatasException;
import co.edu.poli.automatas.proyecto.model.Arista;
import co.edu.poli.automatas.proyecto.model.Estado;
import co.edu.poli.automatas.proyecto.util.DefaultValidadorLineaArchivo;
import co.edu.poli.automatas.proyecto.util.IValidadorLineaArchivo;

/**
 * @author Luis Castillo
 * Clase principal que arranca el programa de lectura de un automata.
 */
public class Automata2ndMain {

	//Datos iniciales...
	/** Cantidad de estados del automata, Numero Q del archivo **/
	private static int numEstados = 0;
	/** Numero de aristas del automata, Numero N del archivo **/
	private static int numAristas = 0;
	/** Cardinalidad del alfabeto, Numero Z del archivo **/
	private static int numAlfabeto = 0;
	/** Cantidad de estados finales, Numero F del archivo **/
	private static int numEstadosFinales = 0;
	/** Simbolo que representa una transicion vacia **/
	private static String transicionVacia = "";
	/** Estados finales del automata **/
	private static List<Estado> estadosFinales = new ArrayList<Estado>();
	/** alfabeto a usar para el automata **/
	private static List<String> alfabeto = new ArrayList<String>();
	/** Lista de las aristas que conforman el automata **/
	private static List<Arista> aristas = new ArrayList<Arista>();
	private static boolean conTransicionesVacias = false;
	
	/********** Variables para la salida *******************/
	/** Mapa con los nuevos estados generados, donde la llave es el id del nuevo estado generado concatenando los 2 estados resultantes **/
	private static HashMap<String, Estado> estadosGenerados = new HashMap<String, Estado>();
	//private static int numAlfabetoGenerado = 0;
	private static Estado estadoInicialSalida = null;
	private static List<Estado> estadosAgregadosMapa = new ArrayList<Estado>();
	private static List<Estado> estadosFinalesGenerados = new ArrayList<Estado>();
	/** Matriz de estados del automata original **/
	private static Estado matrizEstadosOriginal[][];
	/** Matriz de estados del automata generada **/
	//private static Estado matrizEstadosGenerada[][];
	
	
	//private static List<String> consultas = new ArrayList<String>();
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		
		try {
			System.out.println("****** Lectura de Automata desde Archivo ***************");
			System.out.println("* Leyendo archivo .......");
			leerArchivo("files/testEntrega2.txt");
			System.out.println("* Termina la carga del archivo!");
			imprimirLecturaArchivo();
			iniciarAtributos();
			imprimirMatrizOriginal();
			generarMatrizNueva();
			generarSalida();
		} catch (InvalidArgumentException e) {
			System.err.println(e.getMessage());
		} catch (ProyectoAutomatasException e) {
			System.err.println(e.getMessage());
		}
	}

	/**
	 * Carga los datos del archivo para inicializar el automata.
	 * @param path
	 * @throws InvalidArgumentException
	 * @throws ProyectoAutomatasException
	 */
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
			
			while((lineaActual=br.readLine())!=null){
				//System.out.println("Leyendo línea: " + lineaActual);
				//Se quitan los espacios al principio y fin.
				lineaActual = lineaActual.trim();
				lineaActual = lineaActual.replaceAll("  ", " ");
				if(!lineaActual.equals("")){
					if(validadorLinea.validarLinea(lineaActual)){
						//Comienza la lectura del archivo..
						String[] datosLinea = lineaActual.split(" ");
						if(datosLinea!=null){
							if(datosLinea.length>0){
								//Paso 1: Se valida que no se este leyendo las filas las aristas.
								if(contLinea < 5){
									//Se llama al método que guarda los datos basicos de la línea.
									extraerDatosBasicosLinea(datosLinea, contLinea);
								}else{
									//Se leen las aristas..
									extraerAristasLinea(datosLinea, contLinea);
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
		//Se extre primero los numeros Q, N, Z y F
		try {
			if(contLinea==1){
				numEstados 			=  Integer.parseInt(datosLinea[0]);
				numAristas 			=  Integer.parseInt(datosLinea[1]);
				numAlfabeto 		= Integer.parseInt(datosLinea[2]);
				numEstadosFinales 	= Integer.parseInt(datosLinea[3]);
				
			}else if(contLinea==2){
				//Se extrae el caracter o cadena para simbolizar la transicion vacia.
				transicionVacia = datosLinea[0];
			}else if(contLinea==3){
				//Se extraen los estados finales...
				for (String linea : datosLinea) {
					Estado e = new Estado();
					e.setEstadoFinal(true);
					e.setNombre(linea);
					e.setNumEstado(Integer.parseInt(linea));
					estadosFinales.add(e);
				}
			}else if(contLinea==4){
				//Se extrae el alfabeto...
				for (String linea : datosLinea) {
					alfabeto.add(linea);
				}
			}
		}catch (NumberFormatException e) {
			throw new ProyectoAutomatasException("\n ERROR: Debe ingresar solo números para la línea: "+contLinea+"\n"+e.getMessage());
		}catch (ArrayIndexOutOfBoundsException e){
			throw new ProyectoAutomatasException("\n ERROR: Faltan datos para procesar la línea: "+contLinea+"\n"+e.getMessage());
		}
	}
	
	/**
	 * Construye una arista y la adiciona al arreglo de aristas dada una linea del archivo.
	 * @param datosLinea los datos ya guardados en el arreglo extraido de la línea.
	 * @param contLinea contador para saber que linea se esta leyendo
	 * @throws ProyectoAutomatasException
	 */
	private static void extraerAristasLinea(String[] datosLinea, int contLinea) throws ProyectoAutomatasException{
		try {
			Arista arista = new Arista();
			Estado origen = new Estado();
			origen.setNombre(datosLinea[0]);
			origen.setNumEstado(Integer.parseInt(datosLinea[0]));
			arista.setOrigen(origen);
			arista.setSimbolo(datosLinea[1]);
			//Se verifica si la arista contiene una transicion vacia...
			if(arista.tieneTransicionVacia(transicionVacia)){
				if(!conTransicionesVacias){
					alfabeto.add(transicionVacia);
					numAlfabeto++;
					conTransicionesVacias = true;
				}				
			}
			Estado destino = new Estado();
			destino.setNombre(datosLinea[2]);
			destino.setNumEstado(Integer.parseInt(datosLinea[2]));
			arista.setDestino(destino);
			
			//Se agrega la arista al listado.
			aristas.add(arista);
		}catch (NumberFormatException e) {
			throw new ProyectoAutomatasException("\n ERROR: Debe ingresar solo números para la línea: "+contLinea+"\n"+e.getMessage());
		}catch (ArrayIndexOutOfBoundsException e){
			throw new ProyectoAutomatasException("\n ERROR: Faltan datos para procesar la línea: "+contLinea+"\n"+e.getMessage());
		}
	}	

	/**
	 * Imprime los datos cargados en el archivo.
	 * @throws ProyectoAutomatasException
	 */
	private static void imprimirLecturaArchivo()throws ProyectoAutomatasException{
		try {
			System.out.println("**** Automata Cargado ********");
			System.out.println("* Q: "+numEstados);
			System.out.println("* N: "+numAristas);
			System.out.println("* Z: "+numAlfabeto);
			System.out.println("* F: "+numEstadosFinales);
			System.out.println("* Cadena vacia: "+transicionVacia);
			System.out.println("* Estados finales: ");
			for (Estado estado : estadosFinales) {
				System.out.print(""+estado.getNumEstado());
			}
			System.out.println("");
			System.out.println("* Alfabeto: ");
			for (String a : alfabeto) {
				System.out.print(""+a);
			}
			System.out.println("");
			System.out.println("* Aristas cargadas: ");
			for (Arista a : aristas) {
				System.out.print("\n"+a.getOrigen().getNombre()+"->"+a.getSimbolo()+"->"+a.getDestino().getNombre());
			}
		} catch (Exception e) {
			throw new ProyectoAutomatasException("\n ERROR: Al imprimir los datos leidos del archivo: \n"+e.getMessage());
		}
		
	}
	
	/**
	 * inicializa los atributos de acuerdo a lo leido por el archivo
	 * OJO se asume que ya se corrio el metodo que lee el archivo.
	 */
	private static void iniciarAtributos() {
		matrizEstadosOriginal = new Estado[numEstados][numAlfabeto];
		
		//Se lee la lista de las aristas para cargar la matriz de estados.
		for (Arista a : aristas) {
			//Se haya la posicion del alfabeto de la arista.
			int pos = alfabeto.indexOf(a.getSimbolo());
			//Se verifica si ya se habia asignado un estado en esa posición
			if(matrizEstadosOriginal[a.getOrigen().getNumEstado()][pos]!=null){
				if(!(a.getDestino().equals(matrizEstadosOriginal[a.getOrigen().getNumEstado()][pos]))){
					//Se agrega un nuevo estado generado por la transicion doble.
					Estado previo = matrizEstadosOriginal[a.getOrigen().getNumEstado()][pos];
					Estado nuevo = new Estado();
					nuevo.setNombre(previo.getNombre()+a.getDestino().getNombre());
					estadosGenerados.put(nuevo.getNombre(), nuevo);
				}
				
			}else{//Sino se asigna a la posicion.
				matrizEstadosOriginal[a.getOrigen().getNumEstado()][pos] = a.getDestino();
			}
		}
	}
	
	private static void imprimirMatrizOriginal(){
		System.out.println("\n* Matriz de transicion del AFND: ");
		for (int i = 0; i < numEstados; i++) {
			for (int j = 0; j < numAlfabeto; j++) {
				Estado e = matrizEstadosOriginal[i][j];
				if(e!=null){
					System.out.print("|"+matrizEstadosOriginal[i][j].getNombre()+"|");
				}else{
					System.out.print("| |");
				}
				
			}
			System.out.println("");
		}
	}
	
	private static void generarMatrizNueva(){
		//Primero se saca el estado inicial..
		Estado inicial = new Estado();
		inicial.setNumEstado(0);
		inicial.setNombre(""+0);
		if(estadosFinales.contains(inicial)){
			inicial.setEstadoFinal(true);
		}
		//Se revisa en la matriz si tiene un estado con transicion vacia...
		if(matrizEstadosOriginal[0][numAlfabeto-1]!=null){
			inicial.setNombre(inicial.getNombre()+matrizEstadosOriginal[0][numAlfabeto-1].getNombre());
			
		}
		estadosGenerados.put(inicial.getNombre(), inicial);
		estadoInicialSalida = inicial;
		estadosAgregadosMapa.add(inicial);
		
		//Se evalua la matriz original para generar el resto de estados..
		for (int i = 0; i < numEstados; i++) {
			for (int j = 0; j < numAlfabeto; j++) {
				Estado e = matrizEstadosOriginal[i][j];
				if(e!=null){
					if(j==numAlfabeto-1){
						e.setNombre(i+matrizEstadosOriginal[i][j].getNombre());
						estadosGenerados.put(e.getNombre(), e);
						if(estadosGenerados.containsKey(""+i)){
							estadosGenerados.remove(""+i);
						}
						Estado agregado = new Estado();
						agregado.setNumEstado(i);
						agregado.setNombre(""+i);
						estadosAgregadosMapa.add(agregado);
						if(estadosFinales.contains(e) || estadosFinales.contains(matrizEstadosOriginal[i][j])){
							estadosFinalesGenerados.add(e);
						}
					}else{
						Estado agregado = new Estado();
						agregado.setNumEstado(i);
						agregado.setNombre(""+i);
						if(!(estadosAgregadosMapa.contains(agregado))){
							estadosGenerados.put(""+i, e);
							estadosAgregadosMapa.add(agregado);
							if(estadosFinales.contains(agregado)){
								estadosFinalesGenerados.add(agregado);
							}
						}
						
					}
				}			
			}
		}
		//Se revisa si es un automata con transiciones vacias...
		if(conTransicionesVacias){
			estadosGenerados.put(transicionVacia, null);
		}
	}
	
	private static void generarSalida(){
		System.out.println("\n * Salida generada:");
		System.out.println("* Tamaño alfabeto: "+(numAlfabeto-1));
		System.out.println("* Alfabeto: ");
		for (String alf : alfabeto) {
			if(!alf.equals(transicionVacia)){
				System.out.println(alf+" ");
			}
		}
		System.out.println("* Estado Inicial: "+estadoInicialSalida.getNombre());
		int tamanoEstados = estadosGenerados.values().size();
		System.out.println("* Cantidad de estados: "+tamanoEstados);
		Set<String> keys = estadosGenerados.keySet();
		Iterator<String> it = keys.iterator();
		System.out.println("* Estados generados: ");
		while(it.hasNext()){
			System.out.println(it.next()+",");
		}
		System.out.println("* Cantidad de estados finales: "+estadosFinalesGenerados.size());
		System.out.println("* Estados finales: ");
		for (Estado e : estadosFinalesGenerados) {
			System.out.print(""+e.getNombre()+",");
		}
	}
}
