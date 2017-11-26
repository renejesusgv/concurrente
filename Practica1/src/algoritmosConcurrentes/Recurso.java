/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package algoritmosConcurrentes;

import java.util.concurrent.atomic.AtomicInteger;

/**
 *
 * @author renejesusgv
 */
public class Recurso {
    private int contador = 0;
    
    void incrementa(){
        contador++;
    }
    
    int get(){
        return contador;
    }
}
