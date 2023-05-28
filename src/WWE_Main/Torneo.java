package WWE_Main;

import java.util.*;

import wwe.exception.CategoriaErronea;
import wwe.exception.ContrincantesInsuficientesException;
import wwe.pojo.Combate;
import wwe.pojo.Luchador;

/**
 * Clase Main
 * 
 * @author Ismael
 */
public class Torneo {

	static Scanner sc = new Scanner(System.in);
	static int accion = -1;
	static LinkedList<Luchador> luchadores = new LinkedList<>();
	static HashMap<String, Integer> HallOfFame = new HashMap<>();
	static LinkedList<String> orden = new LinkedList<>();

	public static void main(String[] args) throws ContrincantesInsuficientesException, CategoriaErronea {

		do {
			mostrarMenu();
		} while (accion != 0);

	}

	/**
	 * @return Entero con el numero introducido por el usuario
	 */
	public static int numberFormatControl() {
		boolean caso = false;
		int a = 0;
		do {// Repetimos hasta que introduzca un caracter válido.
			try { // Controlamos la excepción
				a = Integer.parseInt(sc.nextLine());
				caso = true;
			} catch (NumberFormatException e) {
				System.out.println("No es lo que te hemos pedido.\n");
			}
		} while (caso == false);
		return a;
	}

	/**
	 * @throws ContrincantesInsuficientesException No hay suficientes contrincantes
	 *                                             para hacer un combate
	 * 
	 * @throws CategoriaErronea                    No es un categoría válida
	 */
	public static void mostrarMenu() throws ContrincantesInsuficientesException, CategoriaErronea {
		System.out.println("** MENÚ PRINCIPAL **\r\n" + "1 – Mostrar luchadores\r\n" + "2 – Añadir luchadores\r\n"
				+ "3 – Ready? FIGHT!\r\n" + "4 – Mostrar Hall of Fame\r\n" + "0 – Salir\r\n");
		accion = numberFormatControl(); // Pedimos el número para realizar la acción capturando la excepción posible
		switch (accion) {
		case 0: {
			System.out.println("Ha sido una gran batalla!");
			break;
		}
		case 1: {
			mostrarLuchadores(); // Muestra los luchadores que hay en el torneo
			break;
		}
		case 2: {
			añadirLuchador(); // Añade luchadores a la LinkedList
			break;
		}
		case 3: {
			Combate c = new Combate(luchadores);
			try { // Tratamos la excepción posible
				c.fight();// Método para luchar
				System.out.println("\nEl ganador es... \n" + c.Ganador().getNombre() + "!!!!");// Muestra el ganador de
																								// la batalla
				System.out.println();
				HallOfFame.put(c.Ganador().getNombre(), c.Ganador().getVictorias());// Mete en el HashMap como key el
																					// nombre del ganador y como value
																					// el número de victorias
				if (!orden.contains(c.Ganador().getNombre())) {// Si en la LinkedList no existe el nombre del ganador
					orden.add(c.Ganador().getNombre());// añade uno nuevo
				}
			} catch (ContrincantesInsuficientesException cie) {
				System.out.println(cie.getMessage());
			}
			break;
		}
		case 4: {
			mostrarHall();// Muestra los luchadores y sus respectivas victorias
			break;
		}

		default:
			System.out.println("No es un número válido.");
		}
	}

	/**
	 * @throws CategoriaErronea Aparece cuando insertas una número de categoría que
	 *                          no existe. Metodo para añadir luchadores(con su
	 *                          nombre y categoría) al arraylist de luchadores.
	 */
	public static void añadirLuchador() throws CategoriaErronea {
		boolean flag = true;
		int categoria = 0;
		System.out.println("** Luchador nuevo **");
		System.out.print("Nombre: ");
		String nombre = sc.nextLine(); // Pedimos el nombre del luchador
		do {// Preguntamos la categoría hasta que cambiemos la flag
			System.out.println("Categoría:\r\n" + "1: Peso mosca\r\n" + "2: Peso pluma\r\n" + "3: Peso ligero\r\n"
					+ "4: Peso medio\r\n" + "5: Peso pesado");
			try {
				categoria = numberFormatControl(); // Pedimos un número capturando la excepción posible
				if (categoria <= 0 || categoria >= 6) {// Si el número de la categoría no esta entre 1 y 5 lanzamos una
														// excepción con el mensaje de "Categoría no válida".
					throw new CategoriaErronea("Categoría no válida.");
				}
				flag = false;
			} catch (CategoriaErronea ce) {
				System.out.println(ce.getMessage());
			}
		} while (flag);
		flag = true;
		do {
			System.out.print("¿Tiene ataque especial? (s/n)");
			String tieneAtaque = sc.nextLine();
			if (tieneAtaque.startsWith("s") || tieneAtaque.startsWith("S")) {// Si tiene ataque especial guarda el
																				// nombre en
																				// una
																				// variable y llama al constructor con
																				// ataque
																				// especial
				System.out.print("Nombre del ataque: ");
				String nombreAtaque = sc.nextLine();
				luchadores.add(new Luchador(nombre, categoria, nombreAtaque));
				flag = false;
			} else if (tieneAtaque.startsWith("n") || tieneAtaque.startsWith("N")) {// Si no es una "S" guarda sin
																					// ataque
																					// especial
				luchadores.add(new Luchador(nombre, categoria));// Añadimos el luchador con su nombre y categoría al
																// arraylist de luchadores
				flag = false;
			} else {
				System.out.println("No es un caracter válido.");
			}
		} while (flag);

		System.out.println("\n** Luchador añadido **\n");
	}

	/**
	 * Muestra la informacion de todos los luchadores en el torneo
	 */
	public static void mostrarLuchadores() {
		int i = 1;
		// Si no hay luchadores en el arraylist muestra que no hay
		if (luchadores.size() == 0) {
			System.out.println("Aún no hay luchadores\n");
		} else { // Si hay recorre el array mostrando su información
			for (Luchador l : luchadores) {
				System.out.println("Luchador " + i + "\n----------------------");
				l.mostrarInfo();
				System.out.println("\n----------------------\n");
				i++;
			}
		}
	}

	/**
	 * Muestra los luchadores y el numero de victorias que ha hecho cada uno por
	 * orden de entrada al Hall(no por número de victorias).
	 */
	public static void mostrarHall() {

		// Si el ArrayList esta vacio dirá que no hay luchadores
		if (orden.isEmpty()) {
			System.out.println("No hay luchadores en el Hall Of Fame aún.\n");
		} else { // Si hay luchadores mostrará los luchadores y sus respectivas victorias.
			System.out.println("** Hall Of Fame **");
			for (String nombre : orden) {// Recorre orden
				Integer victorias = HallOfFame.get(nombre); // Coje el numero de victorias del luchador guardado en el
															// HashMap que tiene el nombre del luchador guardado en la
															// LinkedList(ordenado por orden de entrada)
				System.out.println(nombre + " : " + victorias);
			}
			System.out.println();
		}
	}
}
