package br.com.letscode.bb.programacaoparalela;

public class SolucaoConcorrenciaAdequada {

    static int numero = -1;

    public static void main(String[] args) {
        TarefaCalculaRaiz calculaRaiz = new TarefaCalculaRaiz();

        Thread thread0 = new Thread(calculaRaiz);
        Thread thread1 = new Thread(calculaRaiz);
        Thread thread2 = new Thread(calculaRaiz);
        Thread thread3 = new Thread(calculaRaiz);
        Thread thread4 = new Thread(calculaRaiz);

        thread0.start();
        thread1.start();
        thread2.start();
        thread3.start();
        thread4.start();
    }

    static class TarefaCalculaRaiz implements Runnable{
        @Override
        public void run() {
            double raiz = 0;
            synchronized (this){
                numero ++;
                raiz = Math.sqrt(numero);
            }
            System.out.println(Thread.currentThread().getName() +
                    " a raiz de " + numero + " é: " + raiz);
        }
    }
}
