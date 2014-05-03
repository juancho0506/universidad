/**
 * 
 */
package co.edu.poli.ingesoft2.granreto;

import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 * @author Luis Castillo
 *
 */
public class FachadaGranRetoTest {
	
	private static FachadaGranReto f;

	/**
	 * @throws java.lang.Exception
	 */
	@BeforeClass
	public static void setUp() throws Exception {
		f = new FachadaGranReto();
	}

	/**
	 * @throws java.lang.Exception
	 */
	@After
	public void tearDown() throws Exception {
	}

	/**
	 * Test method for {@link co.edu.poli.ingesoft2.granreto.FachadaGranReto#cargarArchivo(java.lang.String)}.
	 */
	@Test
	public void testCargarArchivo() {
		String rutaArchivo = "files/caso2.txt"; 
		try {
			f.cargarArchivo(rutaArchivo);
		} catch (GranRetoException e) {
			System.err.println(e.getMessage());
		}
	}

	/**
	 * Test method for {@link co.edu.poli.ingesoft2.granreto.FachadaGranReto#obtenerTabla()}.
	 */
	@Test
	public void testObtenerTabla() {
		try {
			System.out.println(f.obtenerTabla());
		} catch (GranRetoException e) {
			System.err.println(e.getMessage());
		}
	}

}
