package semaforos;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 *
 * @author renejesusgv
 */
public class main {
    public static void main(String[] args) throws InterruptedException{
        final int valorInicial = 1; //El numero de threads que pueden acceder a su seccion critica.
        List<MyThread> listaDeThreads = new ArrayList<>();
        System.out.println('\n');
        Scanner scanner = new Scanner(System.in);
        System.out.println("Ingresa el numero de threads que van a ejecutarse");
        int i = scanner.nextInt();
        //Creacion del objeto semaforo con el contador inicializado
        //con el valor de la variable valorInicial.
        Semaforo semaforo = new Semaforo(valorInicial);
        //Creacion de n threads.
        for(int n = 0; n < i; n++){
            MyThread mythread = new MyThread(semaforo, 10);
            listaDeThreads.add(mythread);
            mythread.start();
        }
        //Espera a terminar threads.
        for (MyThread mythread: listaDeThreads)
            mythread.join();
    }
}
