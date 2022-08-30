package br.com.letscode.bb.lambdas.exercicio;

import java.math.BigDecimal;

public class RenegociacaoEmprestimoAtrasado {

    public BigDecimal calculaRenegociacao(BigDecimal valorDividaPendente, int novaQuantidadeParcelasPendentes, CalculadorRenegociacaoDivida calculadorRenegociacaoDivida){
        return calculadorRenegociacaoDivida.calculaRenegociacaoDivida(valorDividaPendente, novaQuantidadeParcelasPendentes);
    }
}
