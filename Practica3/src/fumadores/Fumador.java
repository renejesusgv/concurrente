package fumadores;

public class Fumador extends Thread {
	private int iterador; // Las veces que un hilo iterará su ejecucion
	private String material;
	private int id;
	public boolean wait; // Variable que simula la condicion de espera para el hilo.
	private Semaforo tabacoPapel;
	private Semaforo papelCerillo;
	private Semaforo cerilloTabaco;
	private Semaforo TerminoDeFumar;

	public Fumador(String material, int id, int iterador, Semaforo tabacoPapel, Semaforo papelCerillo,
			Semaforo cerilloTabaco, Semaforo terminoDeFumar) {
		this.material = material;
		this.id = id;
		this.wait = wait;
		this.tabacoPapel = tabacoPapel;
		this.papelCerillo = papelCerillo;
		this.cerilloTabaco = cerilloTabaco;
		this.TerminoDeFumar = terminoDeFumar;
		this.wait = false;
		this.iterador = iterador;
	}
	
	/*Metodo que ejecuta un fumador al entrar a su sección critica.
	 * El fumador se mantendra dormido por 10 milisegundos mientras fuma.
	 */
	
	public void fuma() throws InterruptedException {
		System.out.println("Fumador " + id + " con material " + material + " comenzó a fumar.");
		Fumador.sleep(100);
		System.out.println("Fumador " + id + " con material " + material + " terminó de fumar.");
		System.out.println("Ronda completada.");
	}

	/*
	 * Metodo que mantiene un thread dormido. Utiliza la variable wait para
	 * determinar cuanto tiempo dormir. La misma variable se cambia en el metodo
	 * signal.
	 */
	public void sleepFumador() throws InterruptedException {
		while (wait) {
			Fumador.sleep(500);
		}
	}

	/*
	 * Funcion wait() que encola y duerme un thread cuando el contador es igual a
	 * cero, en otro caso entrara a su seccion critica y decrementara la variable
	 * contador de semafor en uno.
	 */
	public void waiting(Semaforo semaforo) throws InterruptedException {
		if (semaforo.contador == 0) {
			this.wait = true;
			synchronized (semaforo) {
				semaforo.cola.offer(this);
			}
			this.sleepFumador();
		} else
			semaforo.contador--;
	}

	/*
	 * Funcion signal() que despierta mediante la variable wait, a los threads en
	 * espera que se encuentran en la cola, si no hay threads esperando aumentará el
	 * contador de semaforo en uno.
	 */
	public synchronized void signal(Semaforo semaforo) {
		if (!semaforo.cola.isEmpty()) {
			//Dos casos posibles: Cuando el siguiente thread es un fumador o es el agente.
			//De cualquier forma, despertamos el siguiente thread si la cola no es vacia.
			if (semaforo.cola.peek().getClass().getName() == "fumadores.Agente") {
				Agente agente = (Agente) semaforo.cola.poll();
				agente.wait = false;
			} else {
				Fumador siguienteFumador = (Fumador) semaforo.cola.poll();
				siguienteFumador.wait = false;
			}
		} else
			semaforo.contador++;
	}

	@Override
	public void run() {
		while (iterador > 0) {
			//El fumador espera los objetos en la mesa que complementan el suyo, si logra
			//pasar el waiting, entra a fumar y al terminar libera la mesa para que el
			//agente pueda seleccionar objetos de nuevo.
			switch (id) {
			case 0:
				try {
					waiting(tabacoPapel);
					fuma();
					signal(TerminoDeFumar);
					break;
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			case 1:
				try {
					waiting(papelCerillo);
					fuma();
					signal(TerminoDeFumar);
					break;
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			case 2:
				try {
					waiting(cerilloTabaco);
					fuma();
					signal(TerminoDeFumar);
					break;
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}
	}
}
