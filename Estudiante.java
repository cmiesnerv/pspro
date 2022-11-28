package ejercicio3;

import java.util.Random;

public class Estudiante implements Runnable {

    public static boolean[] libros = new boolean[9];
    public static final Object objeto = new Object();

    @Override
    public void run() {
        try {
            while (true) {
                //Escogemos dos libros random
                int libro1 = new Random().nextInt(9);
                int libro2 = new Random().nextInt(9);
                //Comprobamos que no sean ambos el mismo libro y hacemos reroll si son el mismo
                while (libro2 == libro1) {
                    libro2 = new Random().nextInt(9);
                }

                //Reservamos esos dos libros
                synchronized (objeto) {
                    while (libros[libro1] || libros[libro2]) {
                        try {
                            objeto.wait();
                        } catch (InterruptedException interruptedException) {
                            System.err.println(interruptedException.getMessage());
                        }
                    }

                    libros[libro1] = true;
                    libros[libro2] = true;
                }

                //Imprimimos quién tiene reservado esos dos libros
                System.out.println(
                        Thread.currentThread().getName() + " tiene reservados los libros " + libro1 + " y " + libro2);
                //Los tiene durante un tiempo aleatorio
                Thread.sleep((long) (Math.random() * 3000));
                //Los devuelve
                System.out.println(Thread.currentThread().getName() + " ha terminado de leer.");
                //Como están libres, vuelven a ser false
                synchronized (objeto) {
                    libros[libro1] = false;
                    libros[libro2] = false;
                    objeto.notifyAll();
                }

                Thread.sleep(1000);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }

    public static void main(String[] args) {

        //Bucle para crear estudiantes
        Estudiante estudiante = new Estudiante();
        for (int i = 1; i <= 4; i++) {
            Thread hilo = new Thread(estudiante);
            hilo.setName("Estudiante " + i);
            hilo.start();
        }
    }
}