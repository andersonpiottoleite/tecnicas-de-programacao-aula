package br.com.letscode.bb.lambdas.minhasthreads;

public class TesteThreads {

    public static void main(String[] args) {
        Thread minhaThread = new Thread(new Executavel());
        minhaThread.start();

        Thread minhaThread2 = new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("chamada 2");
            }
        });
        minhaThread2.start();

        Thread minhaThread3 = new Thread(() -> System.out.println("chamada 3"));
        minhaThread3.start();
    }
}

class Executavel implements Runnable{
    @Override
    public void run() {
        System.out.println("chamada 1");
    }
}
