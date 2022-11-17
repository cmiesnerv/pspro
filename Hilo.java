//Clase Hilo que hereda de Thread
public class Hilo extends Thread {

    //Atributos del hilo (solo necesitamos un nombre)
    private String nombre;

    //Constructor
    public Hilo(String nombre) {
        super();
        this.nombre = nombre;
    }

    //Metodo run
    @Override
    public void run(){
        try {
            //Bucle infinito
            while (true) {
                //Imprime el nombre del hilo
                System.out.println("Soy el bucle " + this.nombre + " y estoy trabajando");
                //Detiene la ejecución entre 1 y 10 segundos
                Thread.sleep((long) (Math.floor(Math.random()*11+1) * 1000));
            }
        }catch(InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {

        //Creamos los hilos
        Hilo hilo1 = new Hilo("hilo1");
        Hilo hilo2 = new Hilo("hilo2");
        Hilo hilo3 = new Hilo("hilo3");
        Hilo hilo4 = new Hilo("hilo4");
        Hilo hilo5 = new Hilo("hilo5");

        //Los iniciamos
        hilo1.start();
        hilo2.start();
        hilo3.start();
        hilo4.start();
        hilo5.start();
    }
}