package br.com.letscode.bb.programacaoparalela;

public class SingleThread {

    // Tipos de Threads:
    // System Thread - Threads definida/criadas pelo sistema (JVM), exemplo GC GarbageCollector (System.gc();)
    // User defined - Threads definida pelo programador
    // Single Thread - apenas uma Thread
    // Multi Thread - rodar varias Threads


    public static void main(String[] args) {
        //System.gc();// sugerindo rodar o garbage collector

        // não precisamos de uma nova Thread para que seja Single, pois o main já é uma Thread
        //new Thread(() ->  System.out.println("Fazendo uma tarefa")).start();

        Thread threadAtual = Thread.currentThread();
        String name = threadAtual.getName();
        System.out.println(name);

    }
}
