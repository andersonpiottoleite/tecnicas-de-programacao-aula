package br.com.letscode.bb.lambdas.playcenter;

public class TesteValidadorEntradaPulaPula {

    public static void main(String[] args) {

        // classe nomeada
        MonitorEletronico me = new MonitorEletronico();
        ValidadorEntradaPulaPula vpp = new ValidadorEntradaPulaPula();
        boolean podeBrincar = me.validaEntradaBrinquedo(11L, 1.50, vpp);
        System.out.println(podeBrincar);

        // classe anonima
        MonitorEletronico me2 = new MonitorEletronico();
        boolean podeBrincar2 = me2.validaEntradaBrinquedo(9L, 1.50, new ValidadorEntradaBrinquedo() {
            @Override
            public boolean validaIdadeEAltura(Long idade, Double altura) {
                return idade > 10 && altura > 1.20;
            }
        });
        System.out.println(podeBrincar2);

        // lambda
        MonitorEletronico me3 = new MonitorEletronico();
        ValidadorEntradaBrinquedo validador = (i, a) -> i > 10 && a > 1.20;
        boolean podeBrincar3 = me3.validaEntradaBrinquedo(15L, 1.80,
                validador);
        System.out.println(podeBrincar3);




    }
}

class ValidadorEntradaPulaPula implements ValidadorEntradaBrinquedo{

    @Override
    public boolean validaIdadeEAltura(Long idade, Double altura) {
        return idade > 10 && altura > 1.20;
    }
}


