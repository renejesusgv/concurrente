package fumadores;

public class Main {

	public static void main(String[] args) throws InterruptedException {
		
		System.out.println("Inicia ejecución.");
		int iterador = 15;	//El número de rondas para los fumadores.
		//Creamos semaforos para las posibles combinaciones de objetos en la mesa.
		//Se inicializan en cero ya que al inicio, no hay ninguna combinación de objetos en la mesa.
		Semaforo tabacoPapel = new Semaforo(0);
		Semaforo papelCerillo = new Semaforo(0);
		Semaforo cerilloTabaco = new Semaforo(0);
		//Semaforo para la iniciar una nueva ronda, se inicializa en uno ya que solo un fumador puede entrar a fumar a la vez.
		//Y el agente debe esperar a que no haya fumadores fumando.
		Semaforo terminoDeFumar = new Semaforo(1);
		
		//Creación de los fumadores con los respectivos semaforos.
		Fumador conTabaco = new Fumador("Tabaco", 1, iterador, tabacoPapel, papelCerillo, cerilloTabaco, terminoDeFumar);
		Fumador conPapel = new Fumador("Papel", 2, iterador, tabacoPapel, papelCerillo, cerilloTabaco, terminoDeFumar);
		Fumador conCerillo = new Fumador("Cerillo", 0 ,iterador, tabacoPapel, papelCerillo, cerilloTabaco, terminoDeFumar);
		//Creación del agente que selecciona los objetos al azar.
        Agente agente = new Agente(iterador, tabacoPapel, papelCerillo, cerilloTabaco, terminoDeFumar);
		
        //Ejecución de los fumadores.
		conTabaco.start();
		conPapel.start();
		conCerillo.start();
		agente.start();
		
		//Espera para que los threads de los fumadores terminen.
        conTabaco.join();
        conPapel.join();
        conCerillo.join();
        agente.join();
	}
}
