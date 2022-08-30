package br.com.letscode.bb.lambdas.sorteio;

import java.util.Random;

public class TesteSorteioMegaSena {

    public static void main(String[] args) {
        // fazendo com classe nomeada
        Sorteio sorteio = new Sorteio();
        GeradorNumeroAleatorioMegaSenha geradorNumeroAleatorioMegaSenha = new GeradorNumeroAleatorioMegaSenha();
        Integer numeroAleatorio = sorteio.sorteiaNumero(geradorNumeroAleatorioMegaSenha);

        System.out.println(numeroAleatorio);

        // fazendo com classe anonima
        Sorteio sorteio2 = new Sorteio();
        Integer numeroAleatorio2 = sorteio2.sorteiaNumero(new GeradorNumeroAleatorio() {
            @Override
            public Integer geraNumeroAleatorio() {
                return new Random().nextInt(60);
            }
        });

        System.out.println(numeroAleatorio2);

        // fazendo com lambda
        Sorteio sorteio3 = new Sorteio();
        Integer numeroAleatorio3 = sorteio3
                .sorteiaNumero(() -> new Random().nextInt(60));

        System.out.println(numeroAleatorio3);
    }
}

class GeradorNumeroAleatorioMegaSenha implements GeradorNumeroAleatorio{

    @Override
    public Integer geraNumeroAleatorio() {
        return new Random().nextInt(60);
    }
}
