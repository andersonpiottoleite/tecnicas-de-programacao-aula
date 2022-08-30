package br.com.letscode.bb.lambdas.interfacesmaisusadas;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class TesteComparator {

    public static void main(String[] args) {
        List<String> nomes = new ArrayList<>();
        nomes.add("Pedro");
        nomes.add("Thiago");
        nomes.add("Mateus");
        nomes.add("Lucas");
        System.out.println(nomes);

        System.out.println("-------------");

        Comparator<String> comparator = (nome1, nome2) -> nome1.compareTo(nome2);
        nomes.sort(comparator);

        System.out.println(nomes);

        List<Integer> numeros = new ArrayList<>();
        numeros.add(2);
        numeros.add(0);
        numeros.add(1);
        numeros.add(3);

        System.out.println(numeros);

        System.out.println("-------------");

        Comparator<Integer> comparatorNumeros = (n1, n2) -> Integer.compare(n1, n2);
        numeros.sort(comparatorNumeros);

        System.out.println(numeros);

    }
}
