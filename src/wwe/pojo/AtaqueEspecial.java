package wwe.pojo;

/**
 * Tipo de ataque: ataque especial
 * 
 * @author ismael
 *
 */
public class AtaqueEspecial extends Ataque {

	private double powerUp;
	private String nombre;
	private int dañoMinimo;

	public AtaqueEspecial(int potencia, String nombre) {
		super(potencia);
		this.nombre = nombre;
		this.powerUp = 1.8;
		this.potencia = (int) (potencia * powerUp);
		this.dañoMinimo = potencia / 3;
	}

	@Override
	protected int lanzarAtaque(Luchador l1, Luchador l2) {
		System.out.print(l1.getNombre() + " hace su " + getNombre() + " sobre " + l2.getNombre() + "!!");
		int num = (int) (Math.random() * getPotencia()) + dañoMinimo;

		return num;
	}

	public String getNombre() {
		return nombre;
	}

	@Override
	public String toString() {
		return ("Ataque especial: " + getNombre());
	}
}
