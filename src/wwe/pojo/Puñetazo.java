package wwe.pojo;

/**
 * Tipo de ataque: puñetazo
 * 
 * @author ismael
 *
 */
public class Puñetazo extends Ataque {

	public Puñetazo(int potencia) {
		super(potencia);
	}

	@Override
	public int lanzarAtaque(Luchador l1, Luchador l2) {
		System.out.print(l1.getNombre() + " le pega un puñetazo a " + l2.getNombre() + "!!");
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
		return ("-Puñetazo | Potencia: " + getPotencia());
	}
}
