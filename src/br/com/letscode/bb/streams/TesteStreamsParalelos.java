package br.com.letscode.bb.streams;

import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.DoubleStream;
import java.util.stream.IntStream;
import java.util.stream.LongStream;

public class TesteStreamsParalelos {

    public static void main(String[] args) {
        // por padrão nos usamos o Stream
        // e quando usar o parallel?
        // quando precisarmos de performance
        // isso tem a ver com algoritmos pesados
        // ou com volume de dados (milhares ou milhoes registros)
        // tira proveito nucleos, para ganhar perfomance
        // separar o processo em varias threads

        formasDeCriacao();
        
        // forEach
        List<Integer> numeros = List.of(1, 2, 3, 4);

        streamSingleThread(numeros);

        streamMultiThread(numeros);

        parallelManterOrdemComForEachOrdered(numeros);

        parallelFindAny(numeros);

        parallelSkipLimitUnordered(numeros);

        parallelReduce(numeros);

        parallelToConcurrentMap(numeros);

        parallelGroupingByConcurrent(numeros);

    }

    private static void parallelGroupingByConcurrent(List<Integer> numeros) {
        // tem o mesmo problema do map, não é thread safe
        Map<Boolean, List<Integer>> groupyBy1 = numeros.parallelStream().collect(Collectors.groupingBy(n -> n % 2 == 0));

        // groupingByConcurrent com paralelo, cria maps individuais e depois junta esse em só
        Map<Boolean, List<Integer>> groupyBy2 = numeros.parallelStream().collect(Collectors.groupingByConcurrent(n -> n % 2 == 0));
    }

    private static void parallelToConcurrentMap(List<Integer> numeros) {
        // map com stream, vai ser criado um unico map
        Map<Integer, Boolean> collect1 = numeros.stream().collect(Collectors.toMap(n -> n, n -> n % 2 == 0));

        // map com stream paralelo tbm criado um unico map
        Map<Integer, Boolean> collect2 = numeros.parallelStream().collect(Collectors.toMap(n -> n, n -> n % 2 == 0));

        // toConcurrentMap com paralelo, cria maps individuais e depois junta esse em só
        Map<Integer, Boolean> collect3 = numeros.parallelStream().collect(Collectors.toConcurrentMap(n -> n, n -> n % 2 == 0));
    }

    private static void parallelReduce(List<Integer> numeros) {
        // soma
        // operacao associativa (segura para se usar com streams paralelos)
        // independente da ordem o resultado é o mesmo
        // (1+2+3+4 = 10) é igual à: ((1+2=3) + (3+4=7) = 10) é igual à: ((1+3=4) + (2+4=6) = 10)
        Integer somaNaoParalela = numeros.stream().reduce((n1, n2) -> n1 + n2).get();
        System.out.println(somaNaoParalela);
        Integer somaParalela = numeros.parallelStream().reduce((n1, n2) -> n1 + n2).get();
        System.out.println(somaParalela);

        // subtracao
        // nao é uma operacao associativa (não é segura para ser trabalhada com streams paralelos)
        // (1-2-3-4) = -8 não é igual à (((1-2) = -1) + ((-3 - -4) = -1)) = -2
        Integer subtracaoNaoParalela = numeros.stream().reduce((n1, n2) -> n1 - n2).get();
        System.out.println(subtracaoNaoParalela);
        Integer subtracaoParalela = numeros.parallelStream().reduce((n1, n2) -> n1 - n2).get();
        System.out.println(subtracaoParalela);
    }

    private static void parallelSkipLimitUnordered(List<Integer> numeros) {
        numeros.stream().skip(1).limit(2).forEach(System.out::println);
        numeros.parallelStream().skip(1).limit(2).forEach(System.out::println); // preso a ordem, o que pode roubar um pouco danossa perfomance
        numeros.parallelStream().unordered().skip(1).limit(2).forEach(System.out::println); // se não precisamos de ordem, mas sim de perfomance. podemos aplicar unordered
    }

    private static void parallelFindAny(List<Integer> numeros) {
        Integer qualquerUm = numeros.stream().findAny().get();
        System.out.println(qualquerUm);

        Integer qualquerUmComParallel = numeros.stream().parallel().findAny().get();
        System.out.println(qualquerUmComParallel);
    }

    private static void parallelManterOrdemComForEachOrdered(List<Integer> numeros) {
        numeros.parallelStream().forEachOrdered(System.out::println);
    }

    private static void streamMultiThread(List<Integer> numeros) {
        numeros.parallelStream().forEach(System.out::println);
    }

    private static void streamSingleThread(List<Integer> numeros) {
        numeros.stream().forEach(System.out::println);
    }

    private static void formasDeCriacao() {
        // parallel é usado a partir de Streams para primitivos
        IntStream.of(1,2).parallel();
        LongStream.of(1,2).parallel();
        DoubleStream.of(1,2).parallel();

        // parallelStream - usado em implemtacoes de collections
        List.of(1).parallelStream();
        Set.of(1).parallelStream();

        // parallel a partir de stream, quando a partir de uma list ou de set
        List.of(1).stream().limit(1).parallel();
    }

}
