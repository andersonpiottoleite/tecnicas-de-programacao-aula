package br.com.letscode.bb.arquivos;

import java.io.*;

public class TesteIO {

    public static void main(String[] args) throws IOException {
        File file = instanciandoClasseFile();

        criaArquivo(file);

        deletaArquivo(file);

        criaArquivo(file);

        algunsMetodosUteis(file);

        escreverNoArquivo(file);

        escreverNoArquivoComAppend(file);

        lerDoArquivo(file);

        escreverNoArquivoUsandoBuffer(file);

        lerDoArquivoUsandoBuffer(file);

        criaPastaComCaminhoRelativo();

        File diretorio = criaPastaComCaminhoAbsoluto();

        File meuFile = criaArquivoDentroDaPasta(diretorio);

        renomeiaArquivo(diretorio, meuFile);

        remomeiaDiretorio(diretorio);

    }

    private static void remomeiaDiretorio(File diretorio) {
        File diretorioRenomeado = new File("minha-pasta4");
        diretorio.renameTo(diretorioRenomeado);
        System.out.println(diretorio.getPath());
        System.out.println(diretorio.getAbsolutePath());
    }

    private static void renomeiaArquivo(File diretorio, File meuFile) {
        File fileRenomeado = new File(diretorio,"file-novo-nome.txt");
        meuFile.renameTo(fileRenomeado);
    }

    private static File criaArquivoDentroDaPasta(File diretorio) throws IOException {
        File meuFile = new File(diretorio, "file.txt");
        boolean criado = meuFile.createNewFile();
        System.out.println(criado);
        return meuFile;
    }

    private static File criaPastaComCaminhoAbsoluto() {
        File diretorio = new File("C:\\Users\\Administrador\\IdeaProjects\\biblioteca\\TecnicasDeProgramacaoTurmaBB\\minha-pasta3");
        boolean diretorioCriado2 = diretorio.mkdir();
        System.out.println(diretorioCriado2);
        return diretorio;
    }

    private static void criaPastaComCaminhoRelativo() {
        File minhaPasta = new File("minha-pasta\\file.txt");
        boolean diretorioCriado = minhaPasta.mkdir();
        System.out.println(diretorioCriado);
    }

    private static void lerDoArquivoUsandoBuffer(File file) throws IOException {
        try(BufferedReader reader = new BufferedReader(new FileReader(file))){
            reader.lines().forEach(System.out::println);
            
            // outra forma de fazer
            /*while(reader.ready()){
                System.out.println(reader.readLine());
            }*/
        }
    }

    private static void escreverNoArquivoUsandoBuffer(File file) throws IOException {
        try(BufferedWriter writer = new BufferedWriter(new FileWriter(file))){
            writer.write("A turma BB vai longe!");
            writer.newLine();
            writer.write("A turma BB vai vai mais longe ainda!");
            writer.newLine();
            writer.write("Tudo dará certo!");
        }
    }

    private static void lerDoArquivo(File file) throws IOException {
        try(FileReader reader = new FileReader(file)){
            /*int read = reader.read(); // retorna uma representação do primeiro do caractere em inteiro (tabela ASCII)
            System.out.println(read);
            System.out.println((char)read);*/
            int i;
            while ((i = reader.read()) != -1){
                System.out.print((char) i);
            }
        }
    }

    private static void escreverNoArquivoComAppend(File file) throws IOException {
        try(FileWriter writer = new FileWriter(file,true)) {
            writer.write("Bodos venceremos \n");
            writer.write("Juntos");
        }
    }

    private static void escreverNoArquivo(File file) throws IOException {
        try(FileWriter writer = new FileWriter(file)) {
            writer.write("Todos venceremos \n");
            writer.write("Juntos");
        }
    }

    private static void algunsMetodosUteis(File file) {
        System.out.println("Nome: " + file.getName());
        System.out.println("Caminho: " + file.getPath());
        System.out.println("Caminho absoluto: " + file.getAbsolutePath());
        System.out.println("É um arquivo: " + file.isFile());
        System.out.println("É um diretorio: " + file.isDirectory()); // para ver se é pasta
        System.out.println("É oculo: " + file.isHidden());
    }

    private static void deletaArquivo(File file) {
        if(file.exists()){
            System.out.println("Deletando...");
            System.out.println("Deletado? " + file.delete());
            //file.delete() // se chamar novamente e o arquivo não existir, retorna false
        }
    }

    private static void criaArquivo(File file) throws IOException {
        boolean arquivoFoiCriado = file.createNewFile();
        System.out.println("Arquivo criado? " + arquivoFoiCriado);
    }

    private static File instanciandoClasseFile() {
        // usando classe file
        // podemos passar o caminho absoluto, por exemplo C:\\pasta\\...
        // podemos passar o caminho relativo
        File file = new File("meuArquivo.txt");
        return file;
    }
}
