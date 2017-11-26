/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package algoritmosConcurrentes;

import java.util.concurrent.atomic.AtomicIntegerArray;

/**
 *
 * @author renejesusgv
 */
public class Peterson {
    private volatile int turno;
    private AtomicIntegerArray quiere_entrar = new AtomicIntegerArray(2);
    
    public Peterson() {
        quiere_entrar.set(0, 0);
        quiere_entrar.set(1, 0);
        turno = 0;
    }

    public void PreProtocolo(int t) {
        int otro = 1 - t; 
        turno = 1 - t;
        //Cambia su bandera como verdadera para pelear por el recurso.
        quiere_entrar.set(t, 1);
        while (quiere_entrar.get(otro) == 1 && turno == otro);
    }
    
    public void PostProtocolo(int t) {
        quiere_entrar.set(t, 0);
    } 
}
