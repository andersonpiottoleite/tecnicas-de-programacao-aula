package br.com.letscode.bb.lambdas.interfacesmaisusadas;

import java.util.ArrayList;
import java.util.List;
import java.util.function.BiFunction;
import java.util.function.Function;

public class TesteFunction {

    public static void main(String[] args) {
        List<String> cursos = new ArrayList<>();
        cursos.add("Java");
        cursos.add("Python");
        cursos.add("Spring Boot");
        cursos.add("C#");

        List<String> listInfos = retornaInformacoesListaCursos(String::toUpperCase, cursos);

        for (String s: listInfos) {
            System.out.println(listInfos);
        }

        String curso = retornaInformacoesListaCursos2(String::toUpperCase, cursos.get(1));
        System.out.println(curso);

        Integer tamanhoNomeCurso = retornaInformacoesListaCursos2(String::length, cursos.get(0));
        System.out.println(tamanhoNomeCurso);
    }

    public static <T,R> List<R> retornaInformacoesListaCursos(Function<T,R> funcao, List<T> cursos){
            List<R> retornoInformacoes = new ArrayList();
            for (T t: cursos) {
                retornoInformacoes.add(funcao.apply(t));
            }
            return retornoInformacoes;
    }

    public static <T,R> R retornaInformacoesListaCursos2(Function<T,R> funcao, T curso){

        return funcao.apply(curso);
    }
}
