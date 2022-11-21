package ejercicio3;

import java.util.concurrent.CopyOnWriteArrayList;

public class GestorHojasFixed extends Thread {

    //Cambiamos List<String> por CopyOnWriteArrayList<String>, que es una variante
    //segura para el uso de hilos. Crea una copia del ArrayList subyacente con cada operación
    // de escritura, por ejemplo, agregar, eliminar o cuando se modifican valores.
    private static CopyOnWriteArrayList<String> lista = new CopyOnWriteArrayList<String>();

    //Metemos lo que había en el run en un nuevo método synchronized, para crear
    //la lista de forma sincronizada y acabar con los errores de concurrencia
    synchronized public static void bucleTextos() {
        for (int i = 0; i < 10; i++) {
            lista.add(new String("Texto" + i));
        }

        for (String string : lista) {
            System.out.println(string);
        }
    }
    //Pasamos el método creado por el run
    @Override
    public void run() {
        bucleTextos();
    }

    public static void main(String[] args) {
        for (int i = 0; i < 10; i++) {
            new GestorHojasFixed().start();
        }

    }

}