package wwe.pojo;

/**
 * Clase abstracta que tiene los metodos de los diferentes tipos de ataques
 * 
 * @author ismael
 */
public abstract class Ataque {

	protected int potencia;

	/**
	 * @param potencia
	 */
	public Ataque(int potencia) {
		this.potencia = potencia;
	}

	/**
	 * @param luchador
	 * @return Entero con el dano del ataque
	 */
	protected abstract int lanzarAtaque(Luchador l1, Luchador l2);

	/**
	 * @param fuerzaAtaque
	 * @param salud
	 * @return True si bloquea el ataque o False si no lo hace
	 */
	public boolean ataqueBloqueado(int fuerzaAtaque, Luchador l) {
		boolean bloqueo = false;
		int num = (int) (Math.random() * l.getSalud());
		if (num > fuerzaAtaque) {
			bloqueo = true;
		}
		return bloqueo;
	}

	/**
	 * @return potencia
	 */
	public int getPotencia() {
		return potencia;
	}

	public abstract String toString();

}
