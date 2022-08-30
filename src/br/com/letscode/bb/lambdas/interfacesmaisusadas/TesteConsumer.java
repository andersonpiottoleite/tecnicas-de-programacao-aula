package br.com.letscode.bb.lambdas.interfacesmaisusadas;

import java.util.function.BiConsumer;
import java.util.function.Consumer;

public class TesteConsumer {

    public static void main(String[] args) {
        Consumer<String> consumer = System.out::println; // BiConsumer
        imprime("meu texto", s -> System.out.println(s));
    }

    public static void imprime(String texto, Consumer<String> stringConsumer){
        stringConsumer.accept(texto);
    }

    public static void metodo(){
        // faz algumas coisa
    }
}
