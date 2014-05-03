/**
 * 
 */
package co.edu.poli.ingesoft2.granreto;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

/**
 * @author Luis Castillo
 * Fachada que implementa la interfaz IFachadaGranReto.
 */
public class FachadaGranReto implements IFachadaGranReto {
	
	private BufferedReader br = null;
	
	/** Validador encargado de verificar cada l�nea que se lea del archivo. */
	private IValidadorLineaArchivo validadorLinea = new DefaultValidadorLineaArchivo();
	
	private AdministradorTorneo administrador = new AdministradorTorneo();
	
	private IArmadorPartido armador = new DefaultArmadorEquipo();
	

	/* (non-Javadoc)
	 * @see co.edu.poli.ingesoft2.granreto.IFachadaGranReto#cargarArchivo(java.lang.String)
	 */
	@Override
	public void cargarArchivo(String rutaArchivo) throws GranRetoException {

		String lineaActual = null;
		File archivo = null;
		
		try {
			//Se carga el archivo.
			archivo = new File(rutaArchivo);
			br = new BufferedReader(new FileReader(archivo));
			//Se recorre las l�neas del archivo..
			int contLinea = 1;
			int contPartido = 1;
			String[] datosPartido = new String[4];
			while((lineaActual=br.readLine())!=null){
				//System.out.println("Leyendo l�nea: " + lineaActual);
				//Se quitan los espacios al principio y fin.
				lineaActual = lineaActual.trim();
				lineaActual = lineaActual.replaceAll("  ", " ");
				if(!lineaActual.equals("")){
					if(validadorLinea.validarLinea(lineaActual)){
						//Se verifica la l�nea para registrar el partido.
						if(contPartido==5){
							contPartido = 1;
							try {
								administrador.registrarPartido(datosPartido);
							} catch (GranRetoException e) {
								System.err.println("\nERROR");
							}
							datosPartido = new String[4];
						}
						
						if(armador.validarDatoPosicion(lineaActual, contPartido)){
							datosPartido[contPartido-1] = lineaActual;
						}
					}else{
						System.err.println("\nERROR");
						continue;
					}
					
					contPartido++;
				}
				contLinea++;
			}
			//Se registra el ultimo partido leido...
			if(contPartido==5){
				contPartido = 1;
				try {
					administrador.registrarPartido(datosPartido);
				} catch (GranRetoException e) {
					System.err.println("\nERROR");
				}
				datosPartido = new String[4];
			}
			
		} catch (IOException e) {
			throw new GranRetoException("Error de lectura del archivo: "+
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

	/* (non-Javadoc)
	 * @see co.edu.poli.ingesoft2.granreto.IFachadaGranReto#obtenerTabla()
	 */
	@Override
	public String obtenerTabla() throws GranRetoException {
		String res = "";
		try {
			res = administrador.generarTablaResultados();
		} catch (GranRetoException e) {
			System.err.println("\nERROR");
		}
		return res;
	}	
	
}
