package br.com.letscode.bb.streams;

import java.util.*;
import java.util.stream.Stream;

public class UsoStreams {

    //Partes:
    // 1 - fonte: de onde o stream é gerado
    // 2 - operações intermediarias: que transforma o stream de um formato, ou conteudo para outro
    // 3 - operações terminais: produzem o resultado final
    public static void main(String[] args) {

        // criacao
        Stream<Integer> integerStream = Stream.of(1);
        Stream<Integer> integerStream1 = Stream.of(1, 2, 3);
        Stream<Object> empty = Stream.empty();

        // quem estende Collection pode criar um Stram
        List lista = new ArrayList<>();
        Stream stream = lista.stream();
        
        Set set = new HashSet();
        Stream stream1 = set.stream();

        // operacoes terminais
        List<String> campeonato  = new ArrayList<>();
        campeonato.add("Gama");
        campeonato.add("Brasiliense");
        campeonato.add("Corinthians");
        campeonato.add("Palmeiras");
        campeonato.add("Santos");
        campeonato.add("São paulo");
        campeonato.add("Flamengo");
        campeonato.add("Vasco");
        campeonato.add("Botafogo");
        campeonato.add("Fluminense");

        Stream<String> streamCampeonato = campeonato.stream();
        long count = streamCampeonato.count();

        System.out.println("Nesse campeonato temos " + count + " Times");

        String primeiroTime = campeonato.stream().findFirst().get();

        System.out.println(primeiroTime);

        String qualquerTime = campeonato.stream().findAny().get();
        System.out.println(qualquerTime);

        campeonato.stream().forEach(System.out::println);


    }
}
