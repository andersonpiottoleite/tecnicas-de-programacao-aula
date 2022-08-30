package br.com.letscode.bb.lambdas.exercicio;

import java.math.BigDecimal;
import java.text.NumberFormat;
import java.util.Locale;

public class TesteRenegociacaoEmprestimoAtrasado {

    public static void main(String[] args) {
        RenegociacaoEmprestimoAtrasado renegociador = new RenegociacaoEmprestimoAtrasado();
        CalculoRenegociacaoEmprestimoAtrasado calculoRenegociacaoEmprestimoAtrasado = new CalculoRenegociacaoEmprestimoAtrasado();
        BigDecimal valorRenegociado = renegociador.calculaRenegociacao(new BigDecimal(1000), 10, calculoRenegociacaoEmprestimoAtrasado);
        imprimeValorRenegociadoFormatado(valorRenegociado);

        RenegociacaoEmprestimoAtrasado renegociador2 = new RenegociacaoEmprestimoAtrasado();
        BigDecimal valorRenegociado2 = renegociador2.calculaRenegociacao(new BigDecimal(500), 5,
                new CalculadorRenegociacaoDivida() {
                    @Override
                    public BigDecimal calculaRenegociacaoDivida(BigDecimal valorDividaPendente, int novaQuantidadeParcelasPendentes) {
                        BigDecimal vRenegociado = BigDecimal.ZERO;
                        BigDecimal valorParcela = valorDividaPendente.divide(new BigDecimal(novaQuantidadeParcelasPendentes));
                        for (int i = 0; i < novaQuantidadeParcelasPendentes; i++) {
                            vRenegociado = vRenegociado.add(
                                    valorParcela.add(valorParcela.multiply(new BigDecimal(0.01))));
                        }
                        return vRenegociado;
                    }
        });

        imprimeValorRenegociadoFormatado(valorRenegociado2);

        /*RenegociacaoEmprestimoAtrasado renegociador3 = new RenegociacaoEmprestimoAtrasado();
        BigDecimal valorRenegociado3 = renegociador3.calculaRenegociacao(new BigDecimal(200), 2,
                (v, p) -> {
                BigDecimal vRenegociado = BigDecimal.ZERO;
                BigDecimal valorParcela = v.divide(new BigDecimal(p));
                for (int i = 0; i < p; i++) {
                    vRenegociado = vRenegociado.add(
                            valorParcela.add(valorParcela.multiply(new BigDecimal(0.01))));
                }
                return vRenegociado;
        });*/

        RenegociacaoEmprestimoAtrasado renegociador3 = new RenegociacaoEmprestimoAtrasado();
        BigDecimal valorRenegociado3 = renegociador3.calculaRenegociacao(new BigDecimal(200), 2,
                TesteRenegociacaoEmprestimoAtrasado::calcula);

        imprimeValorRenegociadoFormatado(valorRenegociado3);
    }

    private static BigDecimal calcula(BigDecimal valorDividaPendente, int novaQuantidadeParcelasPendentes){
        BigDecimal vRenegociado = BigDecimal.ZERO;
        BigDecimal valorParcela = valorDividaPendente.divide(new BigDecimal(novaQuantidadeParcelasPendentes));
        for (int i = 0; i < novaQuantidadeParcelasPendentes; i++) {
            vRenegociado = vRenegociado.add(
                    valorParcela.add(valorParcela.multiply(new BigDecimal(0.01))));
        }
        return vRenegociado;
    }

    private static void imprimeValorRenegociadoFormatado(BigDecimal valorRenegociado){
        System.out.println(NumberFormat
                .getCurrencyInstance(new Locale("pt", "br"))
                .format(valorRenegociado) );
    }
}

class CalculoRenegociacaoEmprestimoAtrasado implements CalculadorRenegociacaoDivida{
    @Override
    public BigDecimal calculaRenegociacaoDivida(BigDecimal valorDividaPendente, int novaQuantidadeParcelasPendentes) {
        BigDecimal vRenegociado = BigDecimal.ZERO;
        BigDecimal valorParcela = valorDividaPendente.divide(new BigDecimal(novaQuantidadeParcelasPendentes));
        for (int i = 0; i < novaQuantidadeParcelasPendentes; i++) {
            vRenegociado = vRenegociado.add(
                    valorParcela.add(valorParcela.multiply(new BigDecimal(0.01))));
        }
        return vRenegociado;
    }
}


