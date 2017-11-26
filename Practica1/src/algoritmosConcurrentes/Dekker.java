package algoritmosConcurrentes;

import java.lang.reflect.Array;
import java.util.concurrent.atomic.AtomicIntegerArray;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author renejesusgv
 */
public class Dekker {
    
    private volatile int turno;
    private AtomicIntegerArray quiere_entrar = new AtomicIntegerArray(2);
    
    public Dekker() {
        quiere_entrar.set(0, 0);
        quiere_entrar.set(1, 0);
        turno = 0;
    }

    public void PreProtocolo(int t) {
        int otro;
        otro = 1 - t;
        //Cambia su bandera como verdadera para pelear por el recurso.
        quiere_entrar.set(t, 1);
        while (quiere_entrar.get(otro) == 1) {
            if (turno == otro) {
                quiere_entrar.set(t, 0);
                while (turno == otro);
                quiere_entrar.set(t, 1);
            }
        }
    }

    public void PostProtocolo(int t) {
        turno = 1 - t;
        quiere_entrar.set(t, 0);
    }
}
