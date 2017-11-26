/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package semaforos;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author renejesusgv
 */
public class MyThread extends Thread{
    public int iterador; //Las veces que un hilo iterará su ejecucion
    boolean wait; // Variable que simula la condicion de espera para el hilo.
    Semaforo semaforo; //Objeto semaforo que controla los n threads.
    
    public MyThread(Semaforo semaforo, int iterador){
        this.semaforo = semaforo;
        this.iterador = iterador;
        this.wait = false;
    }
    /*Metodo que mantiene un thread dormido. Utiliza la variable
    wait para determinar cuanto tiempo dormir. La misma variable se cambia
    en el metodo signal.
    */
    public void sleepThread () throws InterruptedException{
        while(wait){
            MyThread.sleep(500);
        }
    }
    /*Funcion wait() que encola y duerme un thread cuando el contador es
    igual a cero, en otro caso entrara a su seccion critica y decrementara
    la variable contador de semafor en uno.
    */
    public synchronized void waiting(Semaforo semaforo) throws InterruptedException {
        if (semaforo.contador == 0) {
            this.wait = true;
            semaforo.cola.add(this);
            this.sleepThread();
        }else
            semaforo.contador--;
    }
    /*Funcion signal() que despierta mediante la variable wait, a los threads
    en espera que se encuentran en la cola, si no hay threads esperando aumentará
    el contador de semaforo en uno. 
    */
    public synchronized void signal(Semaforo semaforo) {
        if (!semaforo.cola.isEmpty()) {
            MyThread siguienteThread = semaforo.cola.poll();
            siguienteThread.wait = false;
        }else
            semaforo.contador++;
    }
    
    @Override
    public void run(){
        while(iterador > 0){
            try {
                this.waiting(semaforo);
            } catch (InterruptedException ex) {
                Logger.getLogger(MyThread.class.getName()).log(Level.SEVERE, null, ex);
            }
            //Sección crítica
            System.out.println("El thread número " + this.getId() + " entró en su sección critica.");
            System.out.println("El valor actual del semaforo es: " + semaforo.contador);
            System.out.println("El valor actual de la cola es: " + semaforo.cola.toString());
            try {
                MyThread.sleep(100);
            } catch (InterruptedException ex) {
                Logger.getLogger(MyThread.class.getName()).log(Level.SEVERE, null, ex);
            }
            this.signal(semaforo);
            //Final de la seccion critica
            System.out.println("El thread número " + this.getId() + " salió de su sección critica.");
            iterador--;
        }
    }
}
