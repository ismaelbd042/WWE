package wwe.pojo;

/**
 * Tipo de ataque: salto
 * 
 * @author ismael
 *
 */
public class Salto extends Ataque {

	private double powerUp;
	private int dañoMinimo;

	/**
	 * @param potencia
	 */
	public Salto(int potencia) {
		super(potencia);
		this.powerUp = 1.5;
		this.potencia = (int) (potencia * powerUp);
		this.dañoMinimo = potencia / 6;
	}

	@Override
	public int lanzarAtaque(Luchador l1, Luchador l2) {
		System.out.print(l1.getNombre() + " salta sobre " + l2.getNombre() + "!!");
		int num = (int) (Math.random() * getPotencia()) + dañoMinimo;
		return num;
	}

	/**
	 * @return potencia
	 */
	public int getPotencia() {
		return potencia;
	}

	@Override
	public String toString() {
		return ("-Salto | Potencia: " + getPotencia());
	}

}
