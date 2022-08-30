package br.com.letscode.bb.lambdas.currying;

import java.util.function.BiFunction;
import java.util.function.Function;

public class TesteCartaCurrying {

    public static void main(String[] args) {

        Function<String, Carta> funcao1 = s -> new Carta(s);
        Carta carta1 = funcao1.apply("Olá amigo");
        System.out.println(carta1);

        Carta carta2 = criaCarta(funcao1, "Olá amigo 2");
        System.out.println(carta2);

        BiFunction<String, String, Carta> biFunction = (saudacao, assunto) -> new Carta(saudacao, assunto);
        Carta carta3 = biFunction.apply("Ola amigo 3", "Assunto 3");
        System.out.println(carta3);

        Carta carta4 = criaCartaComBiFunction(biFunction, "Ola amigo 4", "Assunto 4");
        System.out.println(carta4);

        /*
        Currying o que é? função que recebe o primeiro parametro (valor 1),
        depois chama outra função que recebe o segundo parametro (valor 2),
        que pode chamar  outra função que recebe o terceiro parametro (valor 3),
        e assim vai até quando necessario
        */

        Function<String, Function<String, Function<String, Carta>>> currying =
                saudacao -> assunto -> (despedida) -> new Carta(saudacao, assunto, despedida);

        Carta carta5 = currying.apply("Olá amigos 5").apply("Assunto 5").apply("um grande abraço 5");
        System.out.println(carta5);

        Carta carta6 = criaCartaComCurryingMaisExtenso(currying, "Ola amigo 6", "Assunto 6", "um grande abraço 6");
        System.out.println(carta6);

        AddSaudacao<String, AddAssunto<String, AddDespedida<String, Carta>>> comNossasInterfacesFuncionais =
                saudacao -> assunto -> (despedida) -> new Carta(saudacao, assunto, despedida);

        Carta carta7 = comNossasInterfacesFuncionais.comSaudacao("Ola amigo 7").comAssunto("Assunto 7").comDespedida("um abraco 7");
        System.out.println(carta7);

        Carta carta8 = criaCartaComInterfacesFuncionais(comNossasInterfacesFuncionais, "Ola amigo 8", "Assunto 8", "um grande abraço 8");
        System.out.println(carta8);

    }

    public static <T, R> R criaCarta(Function<T, R> f, T saudacao){
        R apply = f.apply(saudacao);
        return apply;
    }

    public static <T, U, R> R criaCartaComBiFunction(BiFunction<T, U, R> f, T saudacao, U assunto){
        R apply = f.apply(saudacao,assunto);
        return apply;
    }

    public static <T, R> R criaCartaComCurryingMaisExtenso(Function<T, Function<T,  Function<T, R>>> f, T saudacao, T conversa, T despedida){
        R apply = f.apply(saudacao).apply(conversa).apply(despedida);
        return apply;
    }

    public static <String, Carta> Carta criaCartaComInterfacesFuncionais(
            AddSaudacao<String, AddAssunto<String,  AddDespedida<String, Carta>>> f, String saudacao, String conversa, String despedida){
        Carta carta = f.comSaudacao(saudacao).comAssunto(conversa).comDespedida(despedida);
        return carta;
    }
}

interface AddSaudacao<String,Carta> {
    Carta comSaudacao(String saudacao);
}

interface AddAssunto<String,Carta> {
    Carta comAssunto(String assunto);
}

interface AddDespedida<String,Carta> {
    Carta comDespedida(String despedida);
}

