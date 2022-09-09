package br.com.letscode.bb.programacaoparalela;

public class RotinaDaMadrugada extends Thread{

    public RotinaDaMadrugada(Runnable runnable){
        super(runnable);
    }


    public static void main(String[] args) {
        RotinaDaMadrugada rotinaDaMadrugada = new RotinaDaMadrugada(
                () -> System.out.println(Thread.currentThread().getName()));
        rotinaDaMadrugada.start();

        RotinaDaMadrugada2 runnable = new RotinaDaMadrugada2();
        runnable.run();
    }
}

class RotinaDaMadrugada2 implements Runnable{

    @Override
    public void run() {
        System.out.println(Thread.currentThread().getName());
    }
}


