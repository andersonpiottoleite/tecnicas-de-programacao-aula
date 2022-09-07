package br.com.letscode.bb.arquivos;

import java.io.*;

public class TesteIO {

    public static void main(String[] args) throws IOException {
        // pacote java.io

        // usando classe file
        // passar o caminho absoluto, por exemplo C:\\pasta\\...
        // passar o caminho relativo
        File file = new File("meuArquivo.txt");

        //criando arquivo
        boolean arquivoFoiCriado = file.createNewFile();
        System.out.println("Arquivo criado? " + arquivoFoiCriado);

        boolean delete = file.delete();
        System.out.println("Arquivo foi deletado? " + delete);

        boolean arquivoFoiCriado2 = file.createNewFile();
        System.out.println("Arquivo criado? " + arquivoFoiCriado2);

        if(file.exists()){
            System.out.println("Deletando...");
           // System.out.println("Deletado? " + file.delete());
        }

        file.createNewFile();

        // metodos uteis
        System.out.println("Nome: " + file.getName());
        System.out.println("Caminho: " + file.getPath());
        System.out.println("Caminho absoluto: " + file.getAbsolutePath());
        System.out.println("É um arquivo: " + file.isFile());
        System.out.println("É um diretorio: " + file.isDirectory()); // para ver se é pasta
        System.out.println("É oculo: " + file.isHidden());

        // escrever

        /*try(FileWriter writer = new FileWriter(file)) {
            writer.write("Todos venceremos \n");
            writer.write("Juntos");
            //writer.flush();
           // writer.close();
        }

        /*try(FileWriter writer = new FileWriter(file,true)) {
            writer.write("Bodos venceremos \n");
            writer.write("Juntos");
            writer.flush();
            writer.close();
        }*/

        // ler
        try(FileReader reader = new FileReader(file)){
            /*int read = reader.read();
            System.out.println(read);
            System.out.println((char)read);*/
            int i;
            while ((i = reader.read()) != -1){
                System.out.print((char) i);
            }
        }

        // escrita otimizada
        try(BufferedWriter writer = new BufferedWriter(new FileWriter(file))){
            writer.write("A turma BB vai longe!");
            writer.newLine();
            writer.write("A turma BB vai vai mais longe ainda!");
            writer.newLine();
            writer.write("Tudo dará certo!");
        }

        // leitura otimizada
        try(BufferedReader reader = new BufferedReader(new FileReader(file))){
            /*while(reader.ready()){
                System.out.println(reader.readLine());
            }*/
            reader.lines().forEach(System.out::println);
        }

        // criando diretorio
        /*File minhaPasta = new File("minha-pasta\\file.txt");
        boolean diretorioCriado = minhaPasta.mkdir();
        System.out.println(diretorioCriado);*/

        File diretorio = new File("C:\\Users\\Administrador\\IdeaProjects\\biblioteca\\TecnicasDeProgramacaoTurmaBB\\minha-pasta3");
        boolean diretorioCriado2 = diretorio.mkdir();
        System.out.println(diretorioCriado2);

        File meuFile = new File(diretorio, "file.txt");
        boolean criado = meuFile.createNewFile();
        System.out.println(criado);

        // renomear aquivo
        File fileRenomeado = new File(diretorio,"file-novo-nome.txt");
        meuFile.renameTo(fileRenomeado);

        File diretorioRenomeado = new File("minha-pasta4");
        diretorio.renameTo(diretorioRenomeado);

        System.out.println(meuFile.getPath());
        System.out.println(meuFile.getAbsolutePath());

    }
}
