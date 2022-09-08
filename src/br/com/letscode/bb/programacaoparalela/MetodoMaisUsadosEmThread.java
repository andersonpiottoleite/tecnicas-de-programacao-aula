package br.com.letscode.bb.programacaoparalela;

public class MetodoMaisUsadosEmThread {

    public static void main(String[] args) throws InterruptedException {

        Thread threadAtual = Thread.currentThread();

        // sleep - dormir
        //Thread.sleep(5000);

        //System.out.println("Executando main ... ");

        /*Thread thread = new Thread(() -> System.out.println("Executando nova tarefa"));
        thread.start();
        thread.start();*/

        //estados:
        //NEW, quando uma thread é instanciada;

        //RUNNABLE, quando o método start() é executado

        //BLOCKED, quando a thread espera acessar um bloco ou recurso sincronizado;

        //WAITING, quando a thread espera um sinal de outra thread;

        //TIMED_WAITING, quando o método sleep() é executado;

        //TERMINATED, quando a thread concluiu a execução de uma tarefa,
        // não é possível executar novamente a mesma thread terminada.

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
}
