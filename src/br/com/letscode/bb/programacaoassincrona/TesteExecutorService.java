package br.com.letscode.bb.programacaoassincrona;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.RejectedExecutionException;

public class TesteExecutorService {

    public static void main(String[] args) throws InterruptedException {
        System.out.println("Metodo Main: " + Thread.currentThread().getName());

        // vantagem de não se limitar a um runnable
        Runnable primeiroRunnable = () -> System.out.println("Primeiro runnable: " + Thread.currentThread().getName());
        Runnable segundoRunnable = () -> System.out.println("Segundo runnable: " + Thread.currentThread().getName());

        ExecutorService service = Executors.newSingleThreadExecutor();
        try {
            System.out.println("inicio");
            try {
                service.execute(primeiroRunnable);
                service.execute(segundoRunnable);
                service.execute(primeiroRunnable);
            }catch (RejectedExecutionException e){
                System.out.println("RejectedExecutionException");
            }
            System.out.println("fim");

        } finally {
            service.shutdown();
        }

        //retorna true se o método shutdown() foi executado. Novas tarefas são rejeitadas e as tarefas previamente submetidas serão executadas.
        if(service.isShutdown()){
            System.out.println("Feito shutdown");
        }

        //retorna true se o método shutdown() foi executado e não existem mais tarefas em tarefas ou a serem executadas.
        Thread.sleep(5000);// para dar tempo de executar todas as tarefas
        if(service.isTerminated()){
            System.out.println("Nenhum tarefa à executar");
        }
    }
}
