package br.com.letscode.bb.programacaoassincrona;

import java.util.concurrent.*;

public class TesteFuture {

    // Future é utilizado para verificar o estado de execução de uma tarefa,
    // além de obter dados de retorno através do método get().
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        var service = Executors.newFixedThreadPool(1);

        Future<Double> future = service.submit(new Callable<Double>() {
            @Override
            public Double call() throws Exception {
                TimeUnit.SECONDS.sleep(3);
                return 5.0;
            }
        });

        fazendoOutraTarefa();
        try {
            Double resultado = future.get(4, TimeUnit.SECONDS); //assincrono
           // Double resultado = future.get(); // sincrono
            // isDone
            System.out.println(resultado);

        } catch (TimeoutException e) {
           throw new RuntimeException(e);
        } finally {
            service.shutdown();
        }
    }

    public static void fazendoOutraTarefa(){
        System.out.println("Fazendo outra tarefa ...");
        int soma = 0;
        for (int i = 0; i < 900000; i++) {
            soma += i;
        }

        System.out.println(soma);
    }
}
