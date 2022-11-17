import java.util.ArrayList;

public class NumeroOculto extends Thread{

    //Atributos
    public static int numeroAdivinar = (int) (Math.random()*100+1);;
    public static boolean acertado = false;
    public int num = 0;

    //Comprueba si el numero propuesto es el numeroAdivinar
    synchronized public static int propuestaNumero(int num){
        int res = 0;

        //En caso de adivinarlo, imprimimos el nombre del hilo que lo adivinó
        if(num==numeroAdivinar && !acertado){
            res=1;
            acertado=true;
            System.out.println("El hilo " + currentThread().getName() + " acertó el número.");
        }else if(acertado){
            res=-1;
        }

        return res;
    }
    @Override
    public void run(){

        //Comprueba si el número ya ha sido adivinado por otro hilo
        while(!acertado){
                num = (int) (Math.random()*100+1);
                if(propuestaNumero(num)==-1){
                    System.out.println("El número ya ha sido adivinado por otro hilo");
                    currentThread().interrupt();
                }
        }
    }

    public static void main(String[] args) {

        ArrayList<NumeroOculto> hilos = new ArrayList<>();
        //Bucle para crear los hilos
        for(int i =0; i<10;i++){
            hilos.add(new NumeroOculto());
            hilos.get(i).setName("Hilo"+(i+1));
        }

        //Bucle para iniciar todos los hilos
        for (NumeroOculto hilo : hilos) {
            hilo.start();
        }

    }
}
