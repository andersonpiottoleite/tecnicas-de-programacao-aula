package br.com.letscode.bb.streams;

import java.util.List;

public class TesteDebugStreams {

    public static void main(String[] args) {
        List<Integer> numeros = List.of(1, 2, 3, 4);

        // suscinto - lambdas feito para ser pequenos e legiveis
        // se precisar de debug, pode ser indicio de que nosso lambda não esta tão simples
        numeros.stream()
                .map(n -> String.valueOf(Double.valueOf(n)))
                .forEach(System.out::println);

        // bloco de código
        numeros.stream()
                .map(n -> {
                    Double aDouble = Double.valueOf(n);
                    String s = String.valueOf(aDouble);
                    return s;
                })
                .forEach(System.out::println);

        // extrair para um metodo
        numeros.stream()
                .map(n -> meuMetodo(n))
                .forEach(System.out::println);

        // method reference
        numeros.stream()
                .map(TesteDebugStreams::meuMetodo)
                .forEach(System.out::println);

        // peek
        numeros.stream()
                .map(n -> String.valueOf(Double.valueOf(n)))
                .peek(s -> System.out.println("Pelo peek: " + s))
                .forEach(System.out::println);
    }



    private static String meuMetodo(Integer n) {
        Double aDouble = Double.valueOf(n);
        String s = String.valueOf(aDouble);
        return s;
    }
}
