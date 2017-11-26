package fumadores;

import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;

/**
*
* @author renejesusgv
*/
public class Semaforo {
   public volatile int contador;
   public Queue<Object> cola = new ConcurrentLinkedQueue<>();
   
   public Semaforo(int contador){
       this.contador = contador;
   }
}