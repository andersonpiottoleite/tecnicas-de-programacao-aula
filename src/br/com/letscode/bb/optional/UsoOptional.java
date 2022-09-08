package br.com.letscode.bb.optional;

import java.util.Optional;

public class UsoOptional {

    public static void main(String[] args) {
        ContaCorrente contaCorrente = new ContaCorrente();
        contaCorrente.setAgencia("2054");
        contaCorrente.setNumero("78945613");

        Cliente cliente1 = new Cliente("Paulo", contaCorrente);
        Cliente cliente2 = new Cliente("Vitoria", null);

        ContaCorrente ccCliente1 = getContaCorrentePorCliente(cliente1);
        System.out.println(ccCliente1.getNumero());

        ContaCorrente ccCliente2 = getContaCorrentePorCliente(cliente2);
        //System.out.println(ccCliente2.getNumero()); // NullPointerException

        Optional<ContaCorrente> optionalCliente1 = getOptionalContaCorrentePorCliente(cliente1);
        System.out.println(optionalCliente1);
        /*if (optionalCliente1.isPresent()){
            System.out.println(optionalCliente1.get());
        }*/
        //pode ser substituido por:
        optionalCliente1.ifPresent(System.out::println);

        optionalCliente1.ifPresentOrElse(System.out::println, () -> System.out.println("Esse cliente não possui Conta Corrente"));

        ContaCorrente novaCC = optionalCliente1.orElse(new ContaCorrente());
        System.out.println("Usando orElse CC : " + novaCC);

        novaCC = optionalCliente1.orElseGet(ContaCorrente::new);
        System.out.println("Usando orElseGet CC : " + novaCC);

        novaCC = optionalCliente1.orElseThrow();
        System.out.println("Usando orElseThrow CC : " + novaCC);

        novaCC = optionalCliente1.orElseThrow(() -> new ContaCorrenteException("Conta Corrente não encontrada!"));
        System.out.println("Usando orElseThrow CC : " + novaCC);

        Optional<ContaCorrente> optionalCliente2 = getOptionalContaCorrentePorCliente(cliente2);
        System.out.println(optionalCliente2);
        /*if (optionalCliente2.isPresent()){
            System.out.println(optionalCliente2.get());
        }*/
        //pode ser substituido por:
        optionalCliente2.ifPresent(System.out::println);

        optionalCliente2.ifPresentOrElse(System.out::println, () -> System.out.println("Esse cliente não possui Conta Corrente"));

        ContaCorrente novaCC2 = optionalCliente2.orElse(new ContaCorrente());
        System.out.println("Usando orElse CC : " + novaCC2);

        novaCC2 = optionalCliente2.orElseGet(() -> new ContaCorrente());
        System.out.println("Usando orElseGet CC : " + novaCC2);

       /* novaCC2 = optionalCliente2.orElseThrow();
        System.out.println("Usando orElseThrow CC : " + novaCC2);*/ // lança exception padrão: NoSuchElementException

        // para exception peronalizada:
        novaCC2 = optionalCliente2.orElseThrow(() -> new ContaCorrenteException("Conta Corrente não encontrada!"));
        System.out.println("Usando orElseThrow CC : " + novaCC2);
    }

    private static ContaCorrente getContaCorrentePorCliente(Cliente cliente) {
        return cliente.getContaCorrente();
    }

    private static Optional<ContaCorrente> getOptionalContaCorrentePorCliente(Cliente cliente) {
        return Optional.ofNullable(cliente.getContaCorrente());
        /*try {
            return Optional.of(cliente.getContaCorrente());
        }catch (NullPointerException ex){
            System.out.println("Ocorreu um null pointer, então a conta corrente nao existe");
        }

        return Optional.empty();*/
    }

}

class Cliente{
    private String nome;
    private ContaCorrente contaCorrente;

    public Cliente(String nome, ContaCorrente contaCorrente) {
        this.nome = nome;
        this.contaCorrente = contaCorrente;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public ContaCorrente getContaCorrente() {
        return contaCorrente;
    }
}

class ContaCorrente {
    private String numero;
    private String agencia;

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public String getAgencia() {
        return agencia;
    }

    public void setAgencia(String agencia) {
        this.agencia = agencia;
    }

    @Override
    public String toString() {
        return "ContaCorrente{" +
                "numero='" + numero + '\'' +
                ", agencia='" + agencia + '\'' +
                '}';
    }
}
