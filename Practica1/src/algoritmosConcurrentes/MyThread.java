/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package algoritmosConcurrentes;

/**
 *
 * @author renejesusgv
 */
public class MyThread extends Thread{
    private int id;
    private int rondas;
    private int vecesQueUse;
    Recurso r;
    Dekker d;
    Peterson p;
    Kessels0 k0;
    Kessels1 k1;
    
    public MyThread(int id, int rondas, Recurso r, Dekker d){
        this.id = id;
        this.rondas = rondas;
        this.vecesQueUse = 0;
        this.r = r;
        this.d = d;
    }
    
    public MyThread(int id, int rondas, Recurso r, Peterson p){
        this.id = id;
        this.rondas = rondas;
        this.vecesQueUse = 0;
        this.r = r;
        this.p = p;
    }
    
    public MyThread(int id, int rondas, Recurso r, Kessels0 k0){
        this.id = id;
        this.rondas = rondas;
        this.vecesQueUse = 0;
        this.r = r;
        this.k0 = k0;
    }
    
    public MyThread(int id, int rondas, Recurso r, Kessels1 k1){
        this.id = id;
        this.rondas = rondas;
        this.vecesQueUse = 0;
        this.r = r;
        this.k1 = k1;
    }
    
    @Override
    public void run(){
        if(d != null){
            while(r.get() < rondas) {
                d.PreProtocolo(id);
                //Seccion critica
                System.out.println("El proceso " + id + " entró en su seccion critica.");
                int sum = 0;
                for (int i = 1; i <= 1000; i++) {
                    sum += i;
                }
                System.out.println("La suma de los primeros 1000 numeros naturales es " +sum);
                r.incrementa();
                vecesQueUse++;
                //Fin de seccion critica
                System.out.println("El proceso " + id + " salió en su seccion critica.");
                d.PostProtocolo(id); 
            }
        }
        if(p != null){
            while (r.get() < rondas) {
                p.PreProtocolo(id);
                //Seccion critica
                System.out.println("El proceso " + id + " entró en su seccion critica.");
                int sum = 0;
                for (int i = 1; i <= 1000; i++) {
                    sum += i;
                }
                System.out.println("La suma de los primeros 1000 numeros naturales es " +sum);
                r.incrementa();
                vecesQueUse++;
                //Fin de seccion critica
                System.out.println("El proceso " + id + " salió en su seccion critica.");
                p.PostProtocolo(id);
            }
        }
        if(k0 != null){
            while (r.get() < rondas) {
                k0.PreProtocolo();
                //Seccion critica
                System.out.println("El proceso " + id + " entró en su seccion critica.");
                int sum = 0;
                for (int i = 1; i <= 1000; i++) {
                    sum += i;
                }
                System.out.println("La suma de los primeros 1000 numeros naturales es " +sum);
                r.incrementa();
                vecesQueUse++;
                //Fin de seccion critica
                System.out.println("El proceso " + id + " salió en su seccion critica.");
                k0.PostProtocolo();
            }
        }
        if(k1 != null){
            while (r.get() < rondas) {
                k1.PreProtocolo();
                //Seccion critica
                System.out.println("El proceso " + id + " entró en su seccion critica.");
                int sum = 0;
                for (int i = 1; i <= 1000; i++) {
                    sum += i;
                }
                System.out.println("La suma de los primeros 1000 numeros naturales es " +sum);
                r.incrementa();
                vecesQueUse++;
                //Fin de seccion critica
                System.out.println("El proceso " + id + " salió en su seccion critica.");
                k1.PostProtocolo();
            }
        }
    }
    
    public int getMyId(){
        return this.id;
    }
    
    public int getVecesQueUse(){
        return this.vecesQueUse;
    }
}
