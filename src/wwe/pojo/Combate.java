package wwe.pojo;

import java.util.LinkedList;

import wwe.exception.ContrincantesInsuficientesException;

/**
 * Clase con un metodo que realiza la pelea en si
 * 
 * @author ismael
 */
public class Combate {

	private LinkedList<Luchador> contrincantes = new LinkedList<>();
	private Luchador ganador;

	/**
	 * @param LinkedList de luchadores para hacer los contrincantes
	 */
	public Combate(LinkedList<Luchador> contrincantes) {
		this.contrincantes.addAll(contrincantes);
	}

	public LinkedList<Luchador> getContrincantes() {
		return contrincantes;
	}

	public void setContrincantes(LinkedList<Luchador> contrincantes) {
		this.contrincantes = contrincantes;
	}

	public Luchador getGanador() {
		return ganador;
	}

	public void setGanador(Luchador ganador) {
		this.ganador = ganador;
	}

	public Luchador Ganador() {
		return ganador;
	}

	/**
	 * @return Devuelve el ganador de la batalla
	 * @throws ContrincantesInsuficientesException
	 */
	public Luchador fight() throws ContrincantesInsuficientesException {
		for (Luchador l : contrincantes) {// Recuperamos a todos los luchadores antes de empezar la batalla(por si han
											// bebido un poquito antes de empezar)
			l.recuperar();
		}
		if (contrincantes.size() <= 1) {// Si la LinkedList tiene 0 o 1 luchador lanza una excepción
			throw new ContrincantesInsuficientesException("No hay suficientes luchadores para empezar el combate \n");
		} else {// Si tiene más
			while (contrincantes.size() > 1) {
				Luchador luch1 = contrincantes.get((int) (Math.random() * contrincantes.size())); // Cogemos un
																									// luchador(que
																									// pegará) aleatorio
				Luchador luch2 = contrincantes.get((int) (Math.random() * contrincantes.size())); // Cogemos un
																									// luchador(que
																									// recibirá)aleatorio
				while (luch1.equals(luch2)) {// Si el que recibe es igual que el que dá escoge otro hasta que no sea el
												// mismo
					luch2 = contrincantes.get((int) (Math.random() * contrincantes.size()));
				}
				// Guarda en una variable un ataque elegido aleatorio de los ataques disponibles
				// del luchador que dará golpes
				Ataque ataque = luch1.getAtaques().get((int) (Math.random() * luch1.getAtaques().size()));
				// Guarda el dano que hace al lanzar el ataque en una variable
				int fuerza = ataque.lanzarAtaque(luch1, luch2);
				if (ataque.ataqueBloqueado(fuerza, luch2)) { // Si el ataque es bloquado
					System.out.println(// Lo imprime
							" El ataque ha sido bloqueado!!\nSalud de " + luch2.getNombre() + ": " + luch2.getSalud());
					luch2.recibirDaño(fuerza / 2);// Y el que es golpeado recibe la mitad de dano
				} else {// Si no bloquea el ataque
					luch2.recibirDaño(fuerza);// Recibe todo el dano del ataque
					System.out.println(" No lo ha bloqueado, se la ha llevado de lleno!\nSalud de " + luch2.getNombre()
							+ ": " + luch2.getSalud());
				}
				if (luch2.isKO()) {// Si el luchador que recibe esta KO, se le quita de la LinkedList
					contrincantes.remove(luch2);
				}
			}
			ganador = contrincantes.getFirst();// El ganador será el unico que hay en la LinkedList
			ganador.setVictorias(ganador.getVictorias() + 1); // Sumamos una victoria al ganador
		}
		return ganador;
	}

}
