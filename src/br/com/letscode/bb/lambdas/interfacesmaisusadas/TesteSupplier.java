package br.com.letscode.bb.lambdas.interfacesmaisusadas;

import java.util.function.Supplier;

public class TesteSupplier {

    public static void main(String[] args) {
        Supplier<Integer> integerSupplier = () -> 10;
        System.out.println(integerSupplier.get());

        Integer numero = retornaNumero(() -> 11);
        System.out.println(numero);
    }

    public static Integer retornaNumero(Supplier<Integer> integerSupplier){
        return integerSupplier.get();
    }
}
