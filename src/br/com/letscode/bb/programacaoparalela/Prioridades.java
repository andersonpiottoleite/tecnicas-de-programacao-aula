package br.com.letscode.bb.programacaoparalela;

public class Prioridades {

    public static void main(String[] args) {

        Thread thread0 = new Thread(() ->
                System.out.println("Prioridade da thread0 - " + Thread.currentThread().getPriority()));
        thread0.setPriority(Thread.MAX_PRIORITY);
        thread0.start();


        Thread thread1 = new Thread(() ->
                System.out.println("Prioridade da thread1 - " + Thread.currentThread().getPriority()));
        thread1.setPriority(Thread.NORM_PRIORITY);
        thread1.start();


        Thread thread2 = new Thread(() ->
                System.out.println("Prioridade da thread2 - " + Thread.currentThread().getPriority()));
        thread2.setPriority(Thread.MIN_PRIORITY);
        thread2.start();

        Thread thread3 = new Thread(() ->
                System.out.println("Prioridade da thread3 - " + Thread.currentThread().getPriority()));
        thread3.setPriority(7);
        thread3.start();

        Thread thread4 = new Thread(() ->
                System.out.println("Prioridade da thread4 - " + Thread.currentThread().getPriority()));
        thread4.setPriority(0);
        thread4.start();


    }
}
