package br.com.letscode.bb.lambdas.interfacesmaisusadas;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class TesteRelacaoLambdaECollections {

    public static void main(String[] args) {
        List<Integer> numeros = new ArrayList<>();
        numeros.add(2);
        numeros.add(0);
        numeros.add(1);
        numeros.add(3);

        System.out.println(numeros);

        numeros.removeIf(n -> n == 1);

        System.out.println(numeros);
        
        // até java 1.4
        Iterator<Integer> iterator = numeros.iterator();
        while (iterator.hasNext()){
            System.out.println(iterator.next());
        }

        // até java 1.5 (java 5)
        for (Integer numero : numeros) {
            System.out.println(numero);
        }

        // a partir do java 8
        numeros.forEach(System.out::println);

        //numeros.forEach( n -> System.out.println(n));



    }
}
