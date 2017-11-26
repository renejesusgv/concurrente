/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package semaforos;

import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;

/**
 *
 * @author renejesusgv
 */
public class Semaforo {
    public volatile int contador;
    public Queue<MyThread> cola = new ConcurrentLinkedQueue<>();;
    
    public Semaforo(int contador){
        this.contador = contador;
    }
}
