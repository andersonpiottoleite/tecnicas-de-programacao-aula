package br.com.letscode.bb.programacaoparalela;

public class MultiThread {

    public static void main(String[] args) {
        String name = Thread.currentThread().getName();
        System.out.println(name);

        Executavel executavel = new Executavel();

        Thread thread0 = new Thread(executavel);
        //thread0.run(); // não cria uma nova Thread, executa apenas o run dentro do main
        thread0.start(); // o metodo start cria uma thread de fato

        Thread thread1 = new Thread(() -> {
            System.out.println(Thread.currentThread().getName());
        });
        thread1.start();

        Thread thread2 = new Thread(executavel);
        thread2.start();

        Runnable tarefa = new Runnable() {
            @Override
            public void run() {
                System.out.println("TESTE IN RUNABLE: " + Thread.currentThread().getName());
            }
        };

        Thread thread3 = new Thread(tarefa);
        thread3.start();

        tarefa.run();
    }
}

class Executavel implements Runnable{

    @Override
    public void run() {
        System.out.println("Executando tarefa...");
        System.out.println(Thread.currentThread().getName());
    }
}
