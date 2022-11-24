package ejercicio3;

import java.util.concurrent.Semaphore;

public class SemaforosAvanzado implements Runnable{

    Semaphore semaforoCarniceria = new Semaphore(4);
    Semaphore semaforoCharcuteria = new Semaphore(2);
    private boolean terminadoCarniceria = false;
    private boolean terminadoCharcuteria = false;
    private boolean terminadoTodo = false;


    @Override
    public void run(){
        do {
            if (!terminadoCarniceria && semaforoCarniceria.availablePermits() > 0){
                vezCarniceria();
            }
            if (!terminadoCharcuteria  && semaforoCharcuteria.availablePermits() > 0){
                vezCharcuteria();
            }
            if (terminadoCharcuteria & terminadoCarniceria ){
                terminadoTodo = true;
            }

        }while (!terminadoTodo);
        System.out.println("El " + Thread.currentThread().getName() + " ha terminado toda su compra.");
    }

    private void vezCarniceria() {
        try {
            semaforoCarniceria.acquire(1);
            System.out.println("El " + Thread.currentThread().getName() + " está siendo atendido en la carnicería.");
            Thread.sleep(10000);
            System.out.println("El " + Thread.currentThread().getName() + " ha terminado en la carnicería.");
            semaforoCarniceria.release(1);
            terminadoCarniceria = true;
        }catch (InterruptedException e){
            e.printStackTrace();
        }
    }


    private void vezCharcuteria() {
        try {
            semaforoCharcuteria.acquire(1);
            System.out.println("El " + Thread.currentThread().getName() + " está siendo atendido en la charcutería.");
            Thread.sleep(10000);
            System.out.println("El " + Thread.currentThread().getName() + " ha terminado en la charcutería.");
            semaforoCharcuteria.release(1);
            terminadoCharcuteria = true;
        }catch (InterruptedException e){
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {

        SemaforosAvanzado sma = new SemaforosAvanzado();

        for(int i =0; i<10;i++){
            Thread hilo = new Thread(sma);
            hilo.setName("cliente número " + i);
            hilo.start();
        }

    }
}
