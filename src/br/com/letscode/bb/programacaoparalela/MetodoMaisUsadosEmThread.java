package br.com.letscode.bb.programacaoparalela;

public class MetodoMaisUsadosEmThread {

    public static void main(String[] args) throws InterruptedException {

        //sleep();
        //start();
        //setDaemon();
        //isDaemon();
        //getState();
        //interrupt();
        //interrupted();
        //setName();
        //isAlive();
    }

    private static void isAlive() {
        Thread thread = new Thread(() -> {
            Thread threadAtual = Thread.currentThread();
            System.out.println(threadAtual.isAlive());
            //Testa se este segmento está vivo. Um thread está vivo se foi iniciado e ainda não morreu.
        });
        thread.start();
    }

    private static void setName() {
        Thread thread = new Thread(() -> {
            System.out.println(Thread.currentThread().getName());
        });
        thread.setName("Minha Thread Marota");
        thread.start();
    }

    private static void interrupted() {
        Thread thread = new Thread(() -> {
            System.out.println("Executando Thread");
        });
        thread.start();
        thread.interrupt();

        if (thread.isInterrupted()){ // verifica se a Thread foi interrompida
            System.out.println("Thead interrompida");
        } else {
            System.out.println("Thead não interrompida");
        }
    }

    private static void interrupt() {
        // interrupt - interrompe uma thread
        Thread threadAtual = Thread.currentThread();

        new Thread(() -> {
            try {
                Thread.sleep(2000); // 2 segundos
                threadAtual.interrupt(); // interrompene thread main
            } catch (InterruptedException e) {
                System.out.println("thread Interrompida!");
            }
        }).start();

        System.out.println("Executando Thread main...");
        try {
            System.out.println("aguardando 5 segundos...");
            Thread.sleep(5000); // 5 segundos para a main
            System.out.println("tempo de 5 segundos finalizados...");
        } catch (InterruptedException e) {
            System.out.println("main interrompido!");
        }
        System.out.println("Thread main finalizada");
    }

    private static void getState() throws InterruptedException {
        Thread thread0 = new Thread(() -> {
            System.out.println("Executando tarefa");
        });
        System.out.println(thread0.getState());
        thread0.start();
        System.out.println(thread0.getState());
        Thread.sleep(2000);
        System.out.println(thread0.getState());
        // outras forma de ver o estado:
        //https://www.baeldung.com/java-thread-lifecycle
    }

    private static void isDaemon() {
        Thread thread = new Thread(() -> {
            System.out.println("Executando tarefa");
        });
        thread.setDaemon(true); // default é false
        thread.start();

        if (thread.isDaemon()){
            System.out.println("Esta possessa!");
        } else {
            System.out.println("Já foi liberta do mal");
        }
    }

    private static void setDaemon() {
        // setDaemon
        // não obriga a JVM aguardar o fim da nossa thread para pode encerrar o programa
        Thread thread1 = new Thread(() -> pausa());
        thread1.setDaemon(false);
        thread1.start();
        System.out.println("Fim do programa");
    }

    private static void pausa(){
        try {
            Thread.sleep(2000);
            System.out.println("Fim da pausa");
        } catch (InterruptedException e) {
            System.out.println("Thread interrompida");
        }
    }

    private static void start() {
        Thread thread = new Thread(() -> System.out.println("Executando nova tarefa"));
        thread.start();
       // thread.start(); //Não podemos executar a Thread novamente.

        //estados da Thread:
        //NEW, quando uma thread é instanciada;
        //RUNNABLE, quando o método start() é executado
        //BLOCKED, quando a thread espera acessar um bloco ou recurso sincronizado;
        //WAITING, quando a thread espera um sinal de outra thread;
        //TIMED_WAITING, quando o método sleep() é executado;
        //TERMINATED, quando a thread concluiu a execução de uma tarefa, não é possível executar novamente a mesma thread terminada.
    }

    private static void sleep() throws InterruptedException {
        // sleep - dormir
        Thread.sleep(5000);
        System.out.println("Executando main ... ");
    }
}
