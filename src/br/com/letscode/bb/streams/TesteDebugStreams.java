package br.com.letscode.bb.streams;

import java.util.List;

public class TesteDebugStreams {

    public static void main(String[] args) {
        List<Integer> numeros = List.of(1, 2, 3, 4);

        // pensar se o lambda esta:
        // suscinto - lambdas feito para ser pequenos e legiveis
        // se precisar de debug, pode ser indicio de que nosso lambda não esteja tão simples

        // dificuldade em debugar aqui:
        numeros.stream()
                .map(n -> String.valueOf(Double.valueOf(n)))
                .forEach(System.out::println);

        // alternativas ao debug:

        usarBlocoDeCodigo(numeros);

        extrairParaMetodo(numeros);

        usarMethodReference(numeros);

        usarPeek(numeros);
    }

    private static void usarPeek(List<Integer> numeros){
        numeros.stream()
                .map(n -> String.valueOf(Double.valueOf(n)))
                .peek(s -> System.out.println("Pelo peek: " + s))
                .forEach(System.out::println);
    }

    private static void usarMethodReference(List<Integer> numeros) {
        numeros.stream()
                .map(TesteDebugStreams::meuMetodo)
                .forEach(System.out::println);
    }

    private static void extrairParaMetodo(List<Integer> numeros) {
        numeros.stream()
                .map(n -> meuMetodo(n))
                .forEach(System.out::println);
    }

    private static void usarBlocoDeCodigo(List<Integer> numeros) {
        numeros.stream()
                .map(n -> {
                    Double aDouble = Double.valueOf(n);
                    String s = String.valueOf(aDouble);
                    return s;
                })
                .forEach(System.out::println);
    }


    private static String meuMetodo(Integer n) {
        Double aDouble = Double.valueOf(n);
        String s = String.valueOf(aDouble);
        return s;
    }
}
