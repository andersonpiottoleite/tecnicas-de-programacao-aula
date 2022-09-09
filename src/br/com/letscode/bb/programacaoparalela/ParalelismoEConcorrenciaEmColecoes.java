package br.com.letscode.bb.programacaoparalela;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ParalelismoEConcorrenciaEmColecoes {

    private static List<String> lista = new ArrayList<>();

    public static void main(String[] args) {

        lista = Collections.synchronizedList(lista);

        Thread thread0 = new Thread(() -> adicionaValorNaLista());
        Thread thread1 = new Thread(() -> adicionaValorNaLista());
        Thread thread2 = new Thread(() -> adicionaValorNaLista());
        Thread thread3 = new Thread(() -> adicionaValorNaLista());
        Thread thread4 = new Thread(() -> adicionaValorNaLista());

        thread0.start();
        thread1.start();
        thread2.start();
        thread3.start();
        thread4.start();
    }

    public static void adicionaValorNaLista() {
        lista.add("TESTE");
        System.out.println(Thread.currentThread().getName() + " - adicionou na lista " + lista);
    }
}
