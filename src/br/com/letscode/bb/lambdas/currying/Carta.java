package br.com.letscode.bb.lambdas.currying;

public class Carta {

    private String saudacao;
    private String assunto;
    private String despedida;

    public Carta(String saudacao, String assunto, String despedida) {
        this.saudacao = saudacao;
        this.assunto = assunto;
        this.despedida = despedida;
    }

    public Carta(String saudacao, String assunto) {
        this.saudacao = saudacao;
        this.assunto = assunto;
    }

    public Carta(String saudacao) {
        this.saudacao = saudacao;
    }

    public String getSaudacao() {
        return saudacao;
    }

    public void setSaudacao(String saudacao) {
        this.saudacao = saudacao;
    }

    public String getAssunto() {
        return assunto;
    }

    public void setAssunto(String assunto) {
        this.assunto = assunto;
    }

    public String getDespedida() {
        return despedida;
    }

    public void setDespedida(String despedida) {
        this.despedida = despedida;
    }

    @Override
    public String toString() {
        return "Carta{" +
                "saudacao='" + saudacao + '\'' +
                ", assunto='" + assunto + '\'' +
                ", despedida='" + despedida + '\'' +
                '}';
    }
}
