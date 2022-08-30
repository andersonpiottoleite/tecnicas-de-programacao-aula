package br.com.letscode.bb.lambdas.interfacesmaisusadas;

import java.util.ArrayList;
import java.util.List;
import java.util.function.BiPredicate;
import java.util.function.IntPredicate;
import java.util.function.Predicate;

public class TestePredicate {

    public static void main(String[] args) {

        Predicate<Integer> predicateNumero = (n) -> n > 10; // temos tbm a BiPredicate
        boolean validado = validaLimiteValorNumero(9, predicateNumero);
        System.out.println(validado);

        IntPredicate intPredicate = (n) -> n < 5;
        boolean validado2 = validaLimiteValorNumero2(2, intPredicate);
        System.out.println(validado2);

        List<Carro> carros = new ArrayList<>();
        carros.add(new Carro("Ferrari Shumacher", 350));
        carros.add(new Carro("Ferrari Rubinho", 349));
        carros.add(new Carro("Uno", 200));
        carros.add(new Carro("Jipe", 230));
        carros.add(new Carro("Maclarem Senna", 500));

        Predicate<Carro> carroPredicate = (Carro carro) -> carro.getVelocidade() > 300;
        buscarCarrosSuperRapidos(carros, carroPredicate);

        System.out.println("-----------------------------");

        Predicate<Carro> carroPredicateOutraImplementacao = (Carro carro) -> carro.getVelocidade() > 400;
        buscarCarrosSuperRapidos(carros, carroPredicateOutraImplementacao);


    }

    private static void buscarCarrosSuperRapidos(List<Carro> carros, Predicate<Carro> predicateCarro) {
        List<Carro> carrosSuperRapidos = new ArrayList<>();

        for (Carro carro: carros) {
            if (predicateCarro.test(carro)){
                carrosSuperRapidos.add(carro);
                System.out.println(carro);
            }
        }
    }

    private static boolean validaLimiteValorNumero(int numero, Predicate<Integer> predicateNumero){
        return predicateNumero.test(numero);
    }

    private static boolean validaLimiteValorNumero2(int numero, IntPredicate predicateNumero){
        return predicateNumero.test(numero);
    }
}

class Carro implements Comparable<Carro>{
    private String modelo;
    private int velocidade;

    public Carro(String modelo, int velocidade) {
        this.modelo = modelo;
        this.velocidade = velocidade;
    }

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public int getVelocidade() {
        return velocidade;
    }

    public void setVelocidade(int velocidade) {
        this.velocidade = velocidade;
    }

    @Override
    public String toString() {
        return "Carro{" +
                "modelo='" + modelo + '\'' +
                ", velocidade=" + velocidade +
                '}';
    }

    @Override
    public int compareTo(Carro o) {
        return 0;
    }
}
