package br.com.letscode.bb.lambdas.exercicio;

import java.math.BigDecimal;

@FunctionalInterface
public interface CalculadorRenegociacaoDivida {

    BigDecimal calculaRenegociacaoDivida(BigDecimal valorDividaPendente, int novaQuantidadeParcelasPendentes);
}
