package ejercicio3;

import java.util.concurrent.Semaphore;

public class Semaforos implements Runnable{

    //semaforo con capacidad de 4 hilos
    Semaphore semaforo = new Semaphore(4);


    @Override
    public void run() {

        try{
            //cogemos los hasta 4 hilos disponibles
            semaforo.acquire();
            System.out.println("El " + Thread.currentThread().getName() + " está siendo atendido.");
            Thread.sleep(10000);
            System.out.println("El " + Thread.currentThread().getName() + " ha terminado de ser atendido.");
            //Al acabar con el hilo lo soltamos
            semaforo.release();

        }catch(InterruptedException e){
            e.printStackTrace();
        }

    }

    public static void main(String[] args) {

        //buble para crear 10 hilos
        Semaforos sm = new Semaforos();

        for(int i =0; i<10;i++){
            Thread hilo = new Thread(sm);
            hilo.setName("cliente número " + i);
            hilo.start();
        }
    }
}
