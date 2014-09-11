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
	private static int numEstados = 0;
	private static int numAlfabeto = 0;
	private static int numEstadosFinales = 0;
	private static int numConsultas = 0;
	private static int matrizEstados[];
	private static List<Estado> estadosFinales = new ArrayList<Estado>();
	private static List<String> alfabeto = new ArrayList<String>();
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		
		//se verifican los argumentos del programa
		if(args!=null){
			if(args.length>0){
				String opcion = args[0];
				try {
					leerArchivo("");
				} catch (InvalidArgumentException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (ProyectoAutomatasException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}else{
				System.out.println("ERROR: Debe enviar un párametro con la opción a ejecutar [1-2].");
			}
		}else{
			System.out.println("ERROR: Debe enviar un párametro con la opción a ejecutar [1-2].");
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
			int contPartido = 1;
			String[] datosPartido = new String[4];
			while((lineaActual=br.readLine())!=null){
				//System.out.println("Leyendo línea: " + lineaActual);
				//Se quitan los espacios al principio y fin.
				lineaActual = lineaActual.trim();
				lineaActual = lineaActual.replaceAll("  ", " ");
				if(!lineaActual.equals("")){
					if(validadorLinea.validarLinea(lineaActual)){
						//Se verifica la línea para registrar el partido.
						
					}else{
						System.err.println("\nERROR");
						continue;
					}
					
					contPartido++;
				}
				contLinea++;
			}
			
		} catch (IOException e) {
			throw new ProyectoAutomatasException("Error de lectura del archivo: "+
					(archivo!=null?archivo.getAbsolutePath():archivo));
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
}
