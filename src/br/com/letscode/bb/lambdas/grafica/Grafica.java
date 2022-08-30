package br.com.letscode.bb.lambdas.grafica;

public class Grafica {

    private Impressora impressora;

    public Grafica(Impressora impressora){
        this.impressora = impressora;
    }

    public void imprimeConteudo(String conteudo){
        impressora.imprime(conteudo);
    }
}
