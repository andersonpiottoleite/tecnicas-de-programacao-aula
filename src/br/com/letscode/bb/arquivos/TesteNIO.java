package br.com.letscode.bb.arquivos;

import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.*;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class TesteNIO {

    public static void main(String[] args) throws IOException {

        Path pasta = Paths.get("minha-pasta-nio");
        if(Files.notExists(pasta)) {
            Files.createDirectory(pasta);
        }

        System.out.println(pasta.getFileName());
        System.out.println(pasta.toFile().isDirectory());
        System.out.println(pasta.toFile().getPath());

        if (Files.notExists(pasta)) {
            Files.createDirectory(pasta); // se ja existir lança Exception
        }

        // subDiretorios
        Path caminhoComSubPasta = Paths.get("minha-pasta-nio/pasta-pai/pasta");
        //Files.createDirectory(caminhoComSubPasta); // se não existir alguam pasta na hierarquia lança exception
        Files.createDirectories(caminhoComSubPasta);
        Files.createDirectories(caminhoComSubPasta); // se chamar varias vezes não lanã exception
        System.out.println(caminhoComSubPasta.getParent());
        System.out.println(caminhoComSubPasta.getParent().getParent());

        // criando arquivo
        Path arquivo = Paths.get("minha-pasta-nio/pasta-pai/pasta/file.txt");
        if(Files.notExists(arquivo)){
            Files.createFile(arquivo);
        }

        // deletar aquivo (2 maneira de fazer)
        //1°
        /*if(Files.exists(arquivo)){
            Files.delete(arquivo);
        }*/
        //2°
       // Files.deleteIfExists(arquivo);

        // copiando arquivo
        /*Path arquivoExistente = Paths.get(caminhoComSubPasta.toString(), "file.txt");
        Path arquivoParaCopiar = Paths.get(caminhoComSubPasta.toString(), "file.txt");
        Files.copy(arquivo, arquivoParaCopiar);*/

        // escrevendo
        String separator = System.lineSeparator();
        Files.write(arquivo, "TESTE".concat(separator).getBytes());
        Files.writeString(arquivo, "TESTE via String".concat(separator), StandardOpenOption.APPEND);
        Files.writeString(arquivo, "TESTE via String".concat(separator), StandardOpenOption.APPEND);

        Files.write(arquivo, List.of("Coração","Televisão","Teste3","Teste4"));
        Files.write(arquivo, List.of("Teste5", "Teste6"),StandardOpenOption.APPEND);

        // leitura
        List<String> conteudoArquivo = Files.readAllLines(arquivo);
        for (String s: conteudoArquivo) {
            System.out.println(s);
        }

        System.out.println("--------------------------");

        Files.readAllLines(arquivo).stream().forEach(System.out::println);
        System.out.println(Charset.defaultCharset());
        System.out.println(Charset.availableCharsets());

        System.out.println("--------------------------");

        Files.readAllLines(arquivo, Charset.availableCharsets().get("ISO-8859-16")).forEach(System.out::println);

        System.out.println("--------------------------");

        String conteudoArquivoString = Files.readString(arquivo);
        System.out.println(conteudoArquivoString);

        System.out.println("--------------------------");

        byte[] bytes = Files.readAllBytes(arquivo);
        System.out.println(new String(bytes));

        System.out.println("--------------------------");

        Files.lines(arquivo).collect(Collectors.toSet());

        // renomear
        //arquivo = minha-pasta-nio\pasta-pai\pasta\file.txt
        //minha-pasta-nio\pasta-pai\pasta\file2.txt
        //Path arquivoRenomeado = Paths.get(arquivo.getParent() + "/file2.txt");
        //Files.move(arquivo, arquivoRenomeado);

        // metodos uteis
        System.out.println(Files.isExecutable(arquivo));
        System.out.println(Files.isWritable(arquivo));
        System.out.println(Files.isReadable(arquivo));
        for (int i = 0; i < 500; i++) {
            System.out.println();
        }



        // conversoes
        File file = arquivo.toFile();
        Path path = file.toPath();

        // subpath
        Path subpath = path.subpath(0, 3);
        System.out.println(subpath);
        // saida: minha-pasta-nio\pasta-pai\pasta
        //System.out.println(subpath.getFileName());


    }
}
