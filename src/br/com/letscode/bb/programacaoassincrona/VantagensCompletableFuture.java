package br.com.letscode.bb.programacaoassincrona;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.function.Function;
import java.util.function.Supplier;

public class VantagensCompletableFuture {

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        ExecutorService exec = Executors.newSingleThreadExecutor();
        CompletableFuture<Integer> f = CompletableFuture.supplyAsync(new MeuSupplier(), exec);
        System.out.println(f.isDone());
        // encadeamento
        CompletableFuture<Integer> f2 = f.thenApply(new incrementaValorUm());
        System.out.println(f2.get());
        exec.shutdown();
    }


    public static class MeuSupplier implements Supplier<Integer> {

        @Override
        public Integer get() {
            try {
                System.out.println("Fazendo algo...");
                Thread.sleep(1000);
            } catch (InterruptedException e) {
            }
            return 1;
        }
    }
    public static class incrementaValorUm implements Function<Integer, Integer> {

        @Override
        public Integer apply(Integer x) {
            return x + 1;
        }
    }
}

