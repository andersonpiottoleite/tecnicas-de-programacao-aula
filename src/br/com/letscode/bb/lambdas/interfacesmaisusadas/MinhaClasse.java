package br.com.letscode.bb.lambdas.interfacesmaisusadas;

public class MinhaClasse implements MinhaInterfacePai{
    @Override
    public boolean metodo() {
        return false;
    }

    public static void main(String[] args) {

        MinhaInterfacePai m2 = new MinhaClasse();
        m2.equals(m2);
        MinhaInterface m = new MinhaInterface() {
            @Override
            public boolean metodo() {
                return false;
            }
        };
        m.equals(m);
    }
}
