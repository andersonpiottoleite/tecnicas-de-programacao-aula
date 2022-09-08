package br.com.letscode.bb.streams;

import java.util.*;
import java.util.stream.*;

public class TesteStreamsNumeros {
    public static void main(String[] args) {

        criacao();

        List<Integer> numeros = List.of(10,10,5,5,42,31,69,87,452,1,6,4,7,8,9,4,3,3);

        operacoesTerminais(numeros);

        operacoesIntermediarias(numeros);

        observacaoImutabilidadeDaListaOriginal(numeros);

    }

    private static void operacoesIntermediarias(List<Integer> numeros) {
        // operacoes intermediarias, podem ser executadas varias vezes

        map(numeros);

        skip(numeros);

        distinct(numeros);

        limit(numeros);

        filter(numeros);

        peek(numeros);

        sorted(numeros);

        flatMap();
    }

    private static void flatMap() {
        ArrayList<Integer> primeiraLista = new ArrayList<>(List.of(1, 5, 9, 10));
        ArrayList<Integer> segundalista = new ArrayList<>(List.of(2, 8, 5, 3));
        ArrayList<Integer> terceiralista = new ArrayList<>(List.of(0, 8, 89, 56));

        // bidimencional
        List<List<Integer>> matriz = new ArrayList<>();
        matriz.add(primeiraLista);
        matriz.add(segundalista);
        System.out.println(matriz);

        List<Integer> flatMap = matriz.stream().flatMap(l -> l.stream()).collect(Collectors.toList());
        System.out.println(flatMap);

        // tridimensional
        List<List<List<Integer>>> matrizTridimensional = new ArrayList<>();
        List<List<Integer>> lista1 = List.of(List.of(1,2), List.of(3,4));
        List<List<Integer>> lista2 = List.of(List.of(5,6), List.of(7,8));

        matrizTridimensional.add(lista1);
        matrizTridimensional.add(lista2);
        System.out.println(matrizTridimensional);

        List<Integer> flatMapTridimensional = matrizTridimensional.stream()
                .flatMap(l -> l.stream())
                .flatMap(l -> l.stream())
                .filter(n -> n < 5)
                .collect(Collectors.toList());
        System.out.println(flatMapTridimensional);
    }

    private static void sorted(List<Integer> numeros) {
        String sorted = numeros.stream().sorted().map(n -> String.valueOf(n)).collect(Collectors.joining("-"));
        System.out.println(sorted);

        String sortedReverso = numeros.stream().sorted(Comparator.reverseOrder()).map(n -> String.valueOf(n)).collect(Collectors.joining("-"));
        System.out.println(sortedReverso);

        //antigo reverse:
        /*
         List<Integer> objects1 = new ArrayList<>();
        objects1.add(1);
        objects1.add(2);
        objects1.add(3);
        Collections.reverse(objects1);
        objects1.forEach(System.out::println);
        */
    }

    private static void peek(List<Integer> numeros) {
        long count1 = numeros.stream().filter(n -> n > 10).peek(n -> System.out.println("Usando Peek" + n)).count();
        System.out.println(count1);
    }

    private static void observacaoImutabilidadeDaListaOriginal(List<Integer> numeros) {
        List<Integer> novaLista = numeros.stream().filter(n -> n > 20).collect(Collectors.toList());
        System.out.println(novaLista);
        System.out.println(numeros); // lista de numero permanece inalterada
    }

    private static void filter(List<Integer> numeros) {
        String numerosMaioresQue20 = numeros.stream().filter(n -> n > 20).map(n -> String.valueOf(n)).collect(Collectors.joining("-"));
        System.out.println(numerosMaioresQue20);
    }

    private static void limit(List<Integer> numeros) {
        String limit = numeros.stream().limit(3).map(n -> String.valueOf(n)).collect(Collectors.joining("-"));
        System.out.println(limit);
    }

    private static void distinct(List<Integer> numeros) {
        String distinct = numeros.stream().distinct().map(n -> String.valueOf(n)).collect(Collectors.joining("-"));
        System.out.println(distinct);
    }

    private static void skip(List<Integer> numeros) {
        String skip = numeros.stream().skip(4).map(n -> String.valueOf(n)).collect(Collectors.joining("-"));
        System.out.println(skip);
    }

    private static void map(List<Integer> numeros) {
        var listString = numeros.stream()
                .map(n -> String.valueOf(n))
                .collect(Collectors.toList());

        var maoString = numeros.stream()
                .map(n -> String.valueOf(n))
                .distinct()
                .collect(Collectors.toMap(s -> s.toUpperCase(), s-> s.toLowerCase()));

        DoubleStream doubleStream1 = numeros.stream().mapToDouble(n -> Double.valueOf(n));

        LongStream longStream1 = numeros.stream().mapMultiToLong((n1, n2) -> Long.valueOf(n1.intValue()));

        LongStream longStream2 = numeros.stream().mapToLong(n1 -> Long.valueOf(n1));
    }

    private static void operacoesTerminais(List<Integer> numeros) {
        // operacoes terminais - onde termina o Stream

        count(numeros);

        findFirst(numeros);

        findAny(numeros);

        forEach(numeros);

        allMatch(numeros);

        anyMatch(numeros);

        noneMatch(numeros);

        min(numeros);

        max(numeros);

        toArray(numeros);

        toList(numeros);

        collect(numeros);

        reduce(numeros);
    }

    private static void reduce(List<Integer> numeros) {
        Integer somaReducao = numeros.parallelStream().reduce((n1, n2) -> n1 + n2).get();
        System.out.println(somaReducao);

        Integer reduce = numeros.stream().reduce((n1, n2) -> n1 * n2).get();
        System.out.println(reduce);
    }

    private static void collect(List<Integer> numeros) {
        List<Integer> list2 = numeros.stream().collect(Collectors.toList());
        System.out.println(list2);

        Set<Integer> set = numeros.stream().collect(Collectors.toSet());
        System.out.println(set);

        Random r = new Random(500000);
        Map<String, Integer> collect = numeros.stream().collect(Collectors.toMap(n -> String.valueOf(n) + r.nextInt(), n -> n.intValue()));
        System.out.println(collect);

        String numerosConcatenados = numeros.stream().map(s -> String.valueOf(s)).collect(Collectors.joining());
        System.out.println(numerosConcatenados);

        var numerosConcatenadosComDelimitador = numeros
                .stream()
                .map(s -> String.valueOf(s))
                .collect(Collectors.joining("-"));

        System.out.println(numerosConcatenadosComDelimitador);

        Map<Boolean, List<Integer>> agrupado = numeros.stream().collect(Collectors.groupingBy(n -> n % 2 == 0));
        System.out.println(agrupado);

        Map<Integer, List<Integer>> agrupado2 = numeros.stream().collect(Collectors.groupingBy(n -> n % 2));
        System.out.println(agrupado2);

        Integer maximo = numeros.stream().collect(Collectors.maxBy((n1, n2) -> Integer.compare(n1, n2))).get();
        System.out.println(maximo);

        Integer minimo = numeros.stream().collect(Collectors.minBy((n1, n2) -> Integer.compare(n1, n2))).get();
        System.out.println(minimo);
    }

    private static void toList(List<Integer> numeros) {
        List<Integer> list1 = numeros.stream().toList();
        System.out.println(list1);
    }

    private static void toArray(List<Integer> numeros) {
        Object[] objects = numeros.stream().toArray();
        System.out.println(objects[0]);
    }

    private static void max(List<Integer> numeros) {
        Integer maiorNumero = numeros.stream().max((n1, n2) -> n1 - n2).get();
        System.out.println(maiorNumero);
    }

    private static void min(List<Integer> numeros) {
        Integer menorNumero = numeros.stream().min((n1, n2) -> Integer.compare(n1, n2)).get();
        System.out.println(menorNumero);
    }

    private static void noneMatch(List<Integer> numeros) {
        boolean noneMatch = numeros.stream().noneMatch(n -> n == 42);
        System.out.println(noneMatch);
    }

    private static void anyMatch(List<Integer> numeros) {
        boolean anyMatch = numeros.stream().anyMatch(n -> n == 2);
        System.out.println(anyMatch);
    }

    private static void allMatch(List<Integer> numeros) {
        boolean allMatch = numeros.stream().allMatch(n -> n > -1);
        System.out.println(allMatch);
    }

    private static void forEach(List<Integer> numeros) {
        numeros.stream().forEach(System.out::println);
    }

    private static void findAny(List<Integer> numeros) {
        Optional<Integer> any = numeros.stream().findAny();
        any.ifPresent(System.out::println);
    }

    private static void findFirst(List<Integer> numeros) {
        Optional<Integer> first = numeros.stream().findFirst();
        first.ifPresent(System.out::println);
    }

    private static void count(List<Integer> numeros) {
        long count = numeros.stream().count();
        System.out.println(count);
    }

    private static void criacao() {
        Stream<Integer> integerStream = Stream.of(1, 2, 3);
        Stream<Integer> integerStream2 = Stream.of(1);
        Stream<Integer> integerStreamVazio = Stream.empty();

        IntStream intStream = IntStream.of(1, 2, 3);
        LongStream longStream = LongStream.of(1, 2, 3);
        DoubleStream doubleStream = DoubleStream.of(1, 2, 3);
    }
}
