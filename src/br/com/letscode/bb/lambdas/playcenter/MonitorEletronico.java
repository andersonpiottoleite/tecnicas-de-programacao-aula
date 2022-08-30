package br.com.letscode.bb.lambdas.playcenter;

public class MonitorEletronico {

    public boolean validaEntradaBrinquedo(Long idade, Double altura, ValidadorEntradaBrinquedo validadorEntradaBrinquedo){
        return validadorEntradaBrinquedo.validaIdadeEAltura(idade,altura);
    }
}
