package wwe.pojo;

import java.util.ArrayList;

/**
 * Clase que crea los luchadores
 * 
 * @author Ismael
 */
public class Luchador {

	private String nombre;
	private int categoria;
	private int fuerza;
	private int salud;
	private ArrayList<Ataque> ataques = new ArrayList<Ataque>();
	private boolean KO;
	private int victorias;

	/**
	 * @param nombre    nombre del Luchador
	 * @param categoria en la que lucha el Luchador
	 */
	public Luchador(String nombre, int categoria) {
		this.nombre = nombre;
		this.categoria = categoria;
		this.fuerza = categoria * 10 + 10;
		this.salud = 300;
		this.KO = false;
		this.ataques = rellenarArraylist();
	}

	// Dos constructores por si tiene ataque especial o no.

	/**
	 * @param nombre       Nombre del luchador
	 * @param categoria    categoria en la que lucha el Luchador
	 * @param nombreAtaque nombre del ataque especial del luchador
	 */
	public Luchador(String nombre, int categoria, String nombreAtaque) {
		this.nombre = nombre;
		this.categoria = categoria;
		this.fuerza = categoria * 10 + 10;
		this.salud = 300;
		this.KO = false;
		this.ataques = rellenarArraylist();
		this.ataques.add(new AtaqueEspecial(fuerza, nombreAtaque));
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public int getCategoria() {
		return categoria;
	}

	public void setCategoria(int categoria) {
		this.categoria = categoria;
	}

	public int getFuerza() {
		return fuerza;
	}

	public void setFuerza(int fuerza) {
		this.fuerza = fuerza;
	}

	public int getSalud() {
		return salud;
	}

	public void setSalud(int salud) {
		this.salud = salud;
	}

	public ArrayList<Ataque> getAtaques() {
		return ataques;
	}

	public void setAtaques(ArrayList<Ataque> ataques) {
		this.ataques = ataques;
	}

	public boolean isKO() {
		return KO;
	}

	public void setKO(boolean kO) {
		KO = kO;
	}

	public int getVictorias() {
		return victorias;
	}

	public void setVictorias(int victorias) {
		this.victorias = victorias;
	}

	/**
	 * @param dano Resta a la salud el dano que le haya hecho si la salud es 0 o
	 *             menos cambia el estado a KO
	 */
	public void recibirDaño(int dano) {
		salud = salud - dano;
		if (salud <= 0) {
			KO = true;
		}

	}

	/**
	 * @return ArrayList
	 *         <ul>
	 *         <li>paco</li>
	 *         <li>pepe</li>
	 *         </ul>
	 */
	public ArrayList<Ataque> rellenarArraylist() {
		for (int i = 0; i < categoria; i++) {
			int num = (int) (Math.random() * 3) + 1;
			switch (num) {
			case 1: // Si es un uno añade un puñetazo
				ataques.add(new Puñetazo(fuerza));
				break;
			case 2:// Si es un uno añade una patada
				ataques.add(new Patada(fuerza));

				break;
			case 3: // Si es un uno añade un salto
				ataques.add(new Salto(fuerza));
				break;
			default:
				break;
			}
		}
		return ataques;
	}

	/**
	 * Pone la salud del Luchador a 300 y cambia su estado a no KO
	 */
	public void recuperar() {
		KO = false;
		salud = 300;
	}

	/**
	 * Muestra la información del luchador(nombre, categoría, si esta KO o no y los
	 * ataques disponibles).
	 */
	public void mostrarInfo() {
		System.out.println("Nombre: " + getNombre());
		System.out.println("Categoría: " + getCategoria());
		System.out.println("KO: " + KO);
		System.out.print("Ataques: ");
		for (Ataque a : ataques) {
			System.out.print("\n" + a);
		}

	}

}
