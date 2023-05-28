package wwe.pojo;

/**
 * Tipo de ataque: patada
 * 
 * @author ismael
 *
 */
public class Patada extends Ataque {

	private double powerUp;

	/**
	 * @param potencia
	 */
	public Patada(int potencia) {
		super(potencia);
		this.powerUp = 1.3;
		this.potencia = (int) (potencia * powerUp);
	}

	@Override
	public int lanzarAtaque(Luchador l1, Luchador l2) {
		System.out.print(l1.getNombre() + " le lanza una patada a " + l2.getNombre() + "!!");
		int num = (int) (Math.random() * super.getPotencia());
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
		return ("-Patada | Potencia: " + getPotencia());
	}
}
