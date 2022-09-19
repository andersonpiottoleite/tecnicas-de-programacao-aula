package br.com.letscode.bb.programacaoassincrona;

import java.util.Random;
import java.util.concurrent.*;

public class TesteCompletableFuture {

    public static void main(String[] args){

        long inicioSync  = System.currentTimeMillis();
        Double valorSync1 = getValorSync();
        Double valorSync2 = getValorSync();
        Double valorSync3 = getValorSync();
        Double valorSync4 = getValorSync();
        long fimSync  = System.currentTimeMillis();
        System.out.println("Tempo Sync: " + (fimSync - inicioSync));

        System.out.println("---------------------------------------");

        System.out.println(valorSync1);
        System.out.println(valorSync2);
        System.out.println(valorSync3);
        System.out.println(valorSync4);

        long inicioAsync  = System.currentTimeMillis();
        Future<Double> valorAsync1 = getValorAsyncSupply();
        Future<Double> valorAsync2 = getValorAsync();
        Future<Double> valorAsync3 = getValorAsync();
        Future<Double> valorAsync4 = getValorAsync();
        long fimAsync  = System.currentTimeMillis();
        System.out.println("Tempo Async: " + (fimAsync - inicioAsync));

        System.out.println("---------------------------------------");

        fazendoOutraTarefa();

        System.out.println("---------------------------------------");

        try {
            long inicioObtendoValores  = System.currentTimeMillis();
            System.out.println(valorAsync1.get());
            System.out.println(valorAsync2.get());
            System.out.println(valorAsync3.get());
            System.out.println(valorAsync4.get());
            long fimObtendoValores  = System.currentTimeMillis();
            System.out.println("Tempo ObtendoValores: " + (fimObtendoValores - inicioObtendoValores));
        } catch (InterruptedException | ExecutionException e) {
            throw new RuntimeException(e);
        }

    }

    public static Double getValorSync(){
        return geradorValorAleatorio();
    }

    public static Future<Double> getValorAsync(){
        CompletableFuture<Double> valorFuture = new CompletableFuture<>();
        new Thread(() -> {
            //try { // propagando exception
                //double v = 0/0;
                valorFuture.complete(geradorValorAleatorio());
            //}catch (Exception e){
               // valorFuture.completeExceptionally(e);
           // }
        }).start();
        return valorFuture;
    }

    public static Future<Double> getValorAsyncSupply() {
        return CompletableFuture.supplyAsync(TesteCompletableFuture::geradorValorAleatorio);
    }

    public static double geradorValorAleatorio(){
        delay();
        return new Random().nextDouble();
    }

    public static void delay() {
        try {
            TimeUnit.SECONDS.sleep(5);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
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
