/**
 * 
 */
package co.edu.poli.automatas.proyecto;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * @author Luis Castillo
 *
 */
public class AutomataMainTest {
	
	private AutomataMain main;

	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		main = new AutomataMain();
	}

	/**
	 * @throws java.lang.Exception
	 */
	@After
	public void tearDown() throws Exception {
	}

	/**
	 * Test method for {@link co.edu.poli.automatas.proyecto.AutomataMain#main(java.lang.String[])}.
	 */
	@Test
	public void testMain() {
		main.main(new String[]{"2"});
	}

}
