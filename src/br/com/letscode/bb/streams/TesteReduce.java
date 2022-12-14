package br.com.letscode.bb.streams;

import java.util.List;
import java.util.Optional;
import java.util.OptionalDouble;
import java.util.OptionalInt;
import java.util.stream.DoubleStream;
import java.util.stream.IntStream;

public class TesteReduce {

    public static void main(String[] args) {
        List<Integer> numeros = List.of(1, 2, 3, 4);

        primeiraVersaoDoMetodoReduce(numeros);

        primeiraSobrecargaDeReduce(numeros);

        segundaSobrecargaDeReduce(numeros);

    }

    private static void segundaSobrecargaDeReduce(List<Integer> numeros) {
        String novaConcatenacao = numeros.stream().map(n -> String.valueOf(n)).reduce((n1, n2) -> n1.concat(n2)).get();
        System.out.println(novaConcatenacao);

        // 2 sobrecarga de reduce
        // @param identity - valor de identidade
        // @param acumulator - acumulador
        // @param combiner - combina os pequenos blocos gerados pelo acumulador
        // util em caso de programação paralela
        // proporciona maior perfomance
        // essa sobrecarga é usada em casos de strams paralelos
        String novaConcatenacaoComCombiner = numeros
                .parallelStream()
                .reduce(""
                        ,(n1, n2) -> n1.toString().concat(n2.toString()) // geraria varios blocos, e nesses blocos faria pequenas concatenacoes
                        ,(n1, n2) -> n1.concat(n2)); // faria a combinação do resultado deses pequenos blocos
        System.out.println(novaConcatenacaoComCombiner);
    }

    private static void primeiraSobrecargaDeReduce(List<Integer> numeros) {
        // 1 sobrecarga de reduce
        // @param identity - valor de identidade
        // valor de identidade para soma = 0
        // n1 = 0 + n2 = 1 -> 1 (identity + n2)
        // @param acumulator - acumulador
        Integer somaComValorIdentidade = numeros.stream().reduce(0, (n1, n2) -> n1 + n2);
        System.out.println(somaComValorIdentidade);

        // valor de identidade para multiplicacao = 1
        // n1 = 1 * n2 = 1 -> 1 (identity + n2)
        Integer multiplicacaoComValorIdentidade = numeros.stream().reduce(1, (n1, n2) -> n1 * n2);
        System.out.println(multiplicacaoComValorIdentidade);

        Optional<String> concatenacao = numeros.stream().map(n -> String.valueOf(n)).reduce((n1, n2) -> n1.concat(n2));
        concatenacao.ifPresent(System.out::println);

        String concatenacaoComValorIdentidade = numeros.stream().map(n -> String.valueOf(n)).reduce("", (n1, n2) -> n1.concat(n2));
        System.out.println(concatenacaoComValorIdentidade);

        OptionalInt reduceOptional = IntStream.empty().reduce((n1, n2) -> n1 + n2);
        System.out.println(reduceOptional);

        int reduceTipoPrimitivo = IntStream.empty().reduce(0, (n1, n2) -> n1 + n2);
        System.out.println(reduceTipoPrimitivo);

        OptionalDouble optionalDouble = DoubleStream.of(10.5, 20.5, 30.5).reduce((n1, n2) -> Math.min(n1, n2));
        System.out.println(optionalDouble);

        // n1 = Maior, comparacaoComOsDemais n2 = 1 -> n2 (identity comparado n2)
        double doubleComValorIdentidade = DoubleStream.of(10.5, 20.5, 30.5)
                .reduce(1.0, (n1, n2) -> Math.min(n1, n2));
        System.out.println(doubleComValorIdentidade);

        double doubleComValorIdentidade2 = DoubleStream.of(10.5, 20.5, 30.5, 101.00)
                .reduce(100.00, (n1, n2) -> Math.min(n1, n2));
        System.out.println(doubleComValorIdentidade2);

        double doubleComValorIdentidade3 = DoubleStream.of(10.5, 20.5, 30.5, 101.00)
                .reduce(Double.POSITIVE_INFINITY, (n1, n2) -> Math.min(n1, n2));
        System.out.println(doubleComValorIdentidade3);
    }

    private static void primeiraVersaoDoMetodoReduce(List<Integer> numeros) {
        // 1 versao do metodo de reduce
        // @param acumulator - acumulador
        Optional<Integer> soma = numeros.stream().reduce((n1, n2) -> n1 + n2);
        soma.ifPresent(System.out::println);

        Optional<Integer> multiplicacao = numeros.stream().reduce((n1, n2) -> n1 * n2);
        multiplicacao.ifPresent(System.out::println);
    }
}
