package br.com.letscode.bb.lambdas.sorteio;

@FunctionalInterface
public interface GeradorNumeroAleatorio {

    public abstract Integer geraNumeroAleatorio();
    // SAM - Simple Abstract Method

    default void metodo1(){
        System.out.println("metodo 1");
    }


    default void metodo2(){
        System.out.println("metodo 2");
    }


    default void metodo3(){
        System.out.println("metodo 2");
    }
}
