/**
 * 
 */
package co.edu.poli.automatas.proyecto.enums;

/**
 * @author Luis Castillo
 * Listado de las operaciones soportadas por la aplicación.
 */
public enum OperadorLogicoEnum {
	
	NEGACION		(1, "Negación", "NEG"),
	OR				(2, "OR", "v"),
	AND				(3, "AND", "^"),
	CONDITIONAL		(4, "Condicional", "->"),
	XOR				(5, "XOR", "XOR"),
	BICONDITIONAL	(6, "Bicondicional", "<->"),
	RECIPROCA		(7, "Reciproca", "REC->"),
	CONTRARECIPROCA	(8, "Contrareciproca", "CONT->"),
	INVERSA			(9, "Inversa", "INV->");
	
	private Integer id;
	private String nombre;
	private String simbol;
	
	private OperadorLogicoEnum(Integer id, String nombre, String simbol) {
		this.id = id;
		this.nombre = nombre;
		this.simbol = simbol;
	}

	public Integer getId() {
		return id;
	}

	public String getNombre() {
		return nombre;
	}

	public String getSimbol() {
		return simbol;
	}
}
