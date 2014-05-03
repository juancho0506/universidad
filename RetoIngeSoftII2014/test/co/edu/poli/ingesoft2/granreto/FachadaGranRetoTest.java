/**
 * 
 */
package co.edu.poli.ingesoft2.granreto;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * @author Luis Castillo
 *
 */
public class FachadaGranRetoTest {

	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
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
		String rutaArchivo = "files/pruebaConApostrofeCaracteresEsp.txt";
		FachadaGranReto f = new FachadaGranReto();
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
		fail("Not yet implemented");
	}

}
