package fumadores;

import java.util.Random;

public class Agente extends Thread{
	boolean wait; // Variable que simula la condicion de espera para el hilo.
	//Semaforos para los posibles objetos.
	private Semaforo tabacoPapel;
	private Semaforo papelCerillo;
	private Semaforo cerilloTabaco;
	private Semaforo TerminoDeFumar;
	private Random random; //Clase para obtener valores al azar al escoger objetos.
	private int iterador;
	
	public Agente(int iterador, Semaforo tabacoPapel, Semaforo papelCerillo, Semaforo cerilloTabaco, Semaforo terminoDeFumar) {
		this.tabacoPapel = tabacoPapel;
		this.papelCerillo = papelCerillo;
		this.cerilloTabaco = cerilloTabaco;
		this.TerminoDeFumar = terminoDeFumar;
		this.random = new Random();
		this.wait = false;
		this.iterador = iterador;
	}
	
	/*Metodo que mantiene un thread dormido. Utiliza la variable
    wait para determinar cuanto tiempo dormir. La misma variable se cambia
    en el metodo signal.
    */
    public void sleepAgente () throws InterruptedException{
        while(wait){
            Fumador.sleep(500);
        }
    }
	
	/*Funcion wait() que encola y duerme un thread cuando el contador es
    igual a cero, en otro caso entrara a su seccion critica y decrementara
    la variable contador de semafor en uno.
    */
    public synchronized void waiting(Semaforo semaforo) throws InterruptedException {
    	if (semaforo.contador == 0) {
    		this.wait = true;
	        semaforo.cola.offer(this);
	        this.sleepAgente();
	    }else
	    	semaforo.contador--;
    }
    
    /*Funcion signal() que despierta mediante la variable wait, a los threads
    en espera que se encuentran en la cola, si no hay threads esperando aumentará
    el contador de semaforo en uno. 
    */
    public void signal(Semaforo semaforo) {
    	synchronized(semaforo) {
	    	if (!semaforo.cola.isEmpty()) {
	            Fumador siguienteFumador = (Fumador) semaforo.cola.poll();
	            siguienteFumador.wait = false;
	        }else {
	            semaforo.contador++;
	        }
    	}
    }
	
	@Override
    public void run(){
		while(iterador > 0) {
			//Espera que la mesa este disponible, esto es que ningun thread esta fumando.
			try {
				waiting(TerminoDeFumar);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			//Selecciona objetos al azar dentro de las tres posibles combinaciones.
			System.out.println("Agente escogiendo objectos.");
			int elementos = random.nextInt(1023)%3;
			
			//Hace un signal de acuerdo con los objetos seleccionados para que el fumador
			//que complementa, pueda entrar a fumar.
			switch (elementos) {
				case 0:
					System.out.println("Escogio tabaco y papel.");
					signal(tabacoPapel);
					break;
				case 1:
					System.out.println("Escogio papel y cerillos.");
					signal(papelCerillo);
					break;
				case 2:
					System.out.println("Escogio cerillo y tabaco.");
					signal(cerilloTabaco);
					break;
			}
			//Decrementa el iterador (ronda).
			iterador--;
		}
	}
}
