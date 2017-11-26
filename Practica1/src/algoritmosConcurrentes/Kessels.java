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
public class Kessels {
    private final AtomicIntegerArray turno = new AtomicIntegerArray(2);
    private final AtomicIntegerArray quiere_entrar = new AtomicIntegerArray(2);
    private final AtomicIntegerArray local = new AtomicIntegerArray(2);
    private final Kessels0 kessels0;
    private final Kessels1 kessels1;
    
    public Kessels() {
        quiere_entrar.set(0, 0);
        quiere_entrar.set(1, 0);
        turno.set(0, 0);
        turno.set(1, 0);
        local.set(0, 0);
        local.set(1, 0);
        kessels0 = new Kessels0(quiere_entrar, turno);
        kessels1 = new Kessels1(quiere_entrar, turno);
    }
    
    public Kessels0 getKessels0(){
        return kessels0;
    }
    
    public Kessels1 getKessels1(){
        return kessels1;
    }
}
