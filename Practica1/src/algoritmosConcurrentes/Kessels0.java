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
public class Kessels0 {
    
    private AtomicIntegerArray quiere_entrar;
    private AtomicIntegerArray turno;
    private AtomicIntegerArray local = new AtomicIntegerArray(2);
    
    public Kessels0(AtomicIntegerArray quiere_entrar, AtomicIntegerArray turno){
        this.quiere_entrar = quiere_entrar;
        this.turno = turno;
    }
    
    public void PreProtocolo() {
        int otro = 1;
        //Cambia su bandera como verdadera para pelear por el recurso.
        quiere_entrar.set(0, 1);
        local.set(0, turno.get(otro));
        turno.set(0, local.get(0));
        while (!(quiere_entrar.get(otro) == 0 || local.get(0) != turno.get(otro)));
    }

    public void PostProtocolo() {
        quiere_entrar.set(0, 0);
    }
}
