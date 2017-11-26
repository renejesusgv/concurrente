package algoritmosConcurrentes;
import java.util.concurrent.TimeUnit;
/**
 *
 * @author renejesusgv
 */

class main {
    static final int rondas = 20;
    
    static Recurso r1 = new Recurso();
    
    static Recurso r2 = new Recurso();
    
    static Recurso r3 = new Recurso();
 
    static Dekker d = new Dekker();
    
    static Peterson p = new Peterson();
    
    static Kessels k = new Kessels();

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws InterruptedException {
    
        System.out.println('\n');
        System.out.println("Ejecutando 2 threads bajo el algoritmo de Dekker " + rondas + " veces");
        
        MyThread d1 = new MyThread(0, rondas, r1, d);
        MyThread d2 = new MyThread(1, rondas, r1, d);

        d1.start();
        d2.start();

        d1.join();
        d2.join();
        
        System.out.println("El proceso  " + d1.getMyId() + " uso el recurso " + d1.getVecesQueUse() + " veces.");
        System.out.println("El proceso  " + d2.getMyId() + " uso el recurso " + d2.getVecesQueUse() + " veces.");
        System.out.println("Fueron completadas " + r1.get() + " rondas.");
        
        System.out.println('\n');
        System.out.println("Ejecutando 2 threads bajo el algoritmo de Peterson " + rondas + " veces");
        
        MyThread p1 = new MyThread(0, rondas, r2, p);
        MyThread p2 = new MyThread(1, rondas, r2, p);
        p1.start();
        p2.start();
        
        p1.join();
        p2.join();

        System.out.println("El proceso  " + p1.getMyId() + " uso el recurso " + p1.getVecesQueUse() + " veces.");
        System.out.println("El proceso  " + p2.getMyId() + " uso el recurso " + p2.getVecesQueUse() + " veces.");
        System.out.println("Fueron completadas " + r2.get() + " rondas.");
        
        System.out.println('\n');
        System.out.println("Ejecutando 2 threads bajo el algoritmo de Kessels " + rondas + " veces");
        
        MyThread k1 = new MyThread(0, rondas, r3, k.getKessels0());
        MyThread k2 = new MyThread(1, rondas, r3, k.getKessels1());
        k1.start();
        k2.start();
        
        k1.join();
        k2.join();

        System.out.println("El proceso  " + k1.getMyId() + " uso el recurso " + k1.getVecesQueUse() + " veces.");
        System.out.println("El proceso  " + k2.getMyId() + " uso el recurso " + k2.getVecesQueUse() + " veces.");
        System.out.println("Fueron completadas " + r3.get() + " rondas.");
    }
}
