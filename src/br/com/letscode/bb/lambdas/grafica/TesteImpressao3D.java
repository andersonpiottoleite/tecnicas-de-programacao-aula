package br.com.letscode.bb.lambdas.grafica;

public class TesteImpressao3D {

    public static void main(String[] args) {
        // fazendo com classe nomeada
        Impressora3D impressoraD3d = new Impressora3D();
        Grafica grafica = new Grafica(impressoraD3d);
        grafica.imprimeConteudo("Conteudo 1");

        // fazendo com classe anonima
        Grafica grafica2 = new Grafica(new Impressora() {
            @Override
            public void imprime(String conteudo) {
                System.out.println(conteudo);
            }
        });
        grafica2.imprimeConteudo("Conteudo 2");

        // fazendo com lambda
        Grafica grafica3 = new Grafica( c ->  System.out.println(c));
        grafica3.imprimeConteudo("Conteudo 3");
    }
}

class Impressora3D implements Impressora{
    @Override
    public void imprime(String conteudo) {
        System.out.println(conteudo);
    }
}
