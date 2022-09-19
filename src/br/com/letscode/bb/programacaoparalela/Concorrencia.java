package br.com.letscode.bb.programacaoparalela;

public class Concorrencia {

    static int count = -1;

    public static void main(String[] args) {
        Contador contador = new Contador();

        Thread thread0 = new Thread(contador);
        Thread thread1 = new Thread(contador);
        Thread thread2 = new Thread(contador);
        Thread thread3 = new Thread(contador);
        Thread thread4 = new Thread(contador);

        thread0.start();
        thread1.start();
        thread2.start();
        thread3.start();
        thread4.start();
    }

    static class Contador implements Runnable {

        @Override
        public void run() {
            //synchronized (this) {
                count++;
                System.out.println(Thread.currentThread().getName() + " - count: " + count);
            //}
        }

        private static void incrementa(){
            synchronized (Contador.class) {
                count++;
                System.out.println(Thread.currentThread().getName() + " - count: " + count);
            }
        }
    }
}
