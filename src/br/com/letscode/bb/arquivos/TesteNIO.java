package br.com.letscode.bb.arquivos;

import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.*;
import java.util.List;
import java.util.stream.Collectors;

public class TesteNIO {

    public static void main(String[] args) throws IOException {

        Path pasta = criaPasta();

        Path caminhoComSubPasta = criaPastaESubPasta();

        Path arquivo = criaArquivo();

        //deletaArquivo(arquivo);

        //copiaArquivo(caminhoComSubPasta, arquivo);

        escreverNoArquivo(arquivo);

        lerDoArquivo(arquivo);

        //renomearArquivo(arquivo);

        algunsMetodosUteis(arquivo);

        Path path = conversoes(arquivo);

        subPath(path);

    }

    private static void subPath(Path path) {
        Path subpath = path.subpath(0, 4);
        System.out.println(subpath);
    }

    private static Path conversoes(Path arquivo) {
        File file = arquivo.toFile();
        Path path = file.toPath();
        return path;
    }

    private static void algunsMetodosUteis(Path arquivo) {
        System.out.println(Files.isExecutable(arquivo));
        System.out.println(Files.isWritable(arquivo));
        System.out.println(Files.isReadable(arquivo));
    }

    private static void renomearArquivo(Path arquivo) throws IOException {
        Path arquivoRenomeado = Paths.get(arquivo.getParent() + "/file2.txt");
        Files.move(arquivo, arquivoRenomeado);
    }

    private static void lerDoArquivo(Path arquivo) throws IOException {
        List<String> conteudoArquivo = Files.readAllLines(arquivo);
        for (String s: conteudoArquivo) {
            System.out.println(s);
        }

        Files.readAllLines(arquivo).stream().forEach(System.out::println);
        System.out.println(Charset.defaultCharset());
        System.out.println(Charset.availableCharsets());

        Files.readAllLines(arquivo, Charset.availableCharsets().get("ISO-8859-16")).forEach(System.out::println);

        String conteudoArquivoString = Files.readString(arquivo);
        System.out.println(conteudoArquivoString);

        byte[] bytes = Files.readAllBytes(arquivo);
        System.out.println(new String(bytes));

        Files.lines(arquivo).forEach(System.out::println);
    }

    private static void escreverNoArquivo(Path arquivo) throws IOException {
        String separator = System.lineSeparator();
        Files.write(arquivo, "TESTE".concat(separator).getBytes());
        Files.writeString(arquivo, "TESTE via String".concat(separator), StandardOpenOption.APPEND);
        Files.writeString(arquivo, "TESTE via String".concat(separator), StandardOpenOption.APPEND);

        Files.write(arquivo, List.of("Coração","Televisão","Teste3","Teste4"));
        Files.write(arquivo, List.of("Teste5", "Teste6"),StandardOpenOption.APPEND);
    }

    private static void copiaArquivo(Path caminhoComSubPasta, Path arquivo) throws IOException {
        Path arquivoExistente = Paths.get(caminhoComSubPasta.toString(), "file.txt");
        Path arquivoParaCopiar = Paths.get(caminhoComSubPasta.toString(), "file.txt");
        Files.copy(arquivo, arquivoParaCopiar);
    }

    private static void deletaArquivo(Path arquivo) throws IOException {
        // 2 maneiras de fazer:
        //1°
        if(Files.exists(arquivo)){
            Files.delete(arquivo);
        }
        //2°
        // Files.deleteIfExists(arquivo);
    }

    private static Path criaArquivo() throws IOException {
        Path arquivo = Paths.get("minha-pasta-nio/pasta-pai/pasta/file.txt");
        if(Files.notExists(arquivo)){
            Files.createFile(arquivo);
        }
        return arquivo;
    }

    private static Path criaPastaESubPasta() throws IOException {
        Path caminhoComSubPasta = Paths.get("minha-pasta-nio/pasta-pai/pasta");
        //Files.createDirectory(caminhoComSubPasta); // se não existir alguma das pastas da hierarquia lança exception
        Files.createDirectories(caminhoComSubPasta);
        Files.createDirectories(caminhoComSubPasta); // se chamar varias vezes não lança exception.

        System.out.println(caminhoComSubPasta.getParent());
        System.out.println(caminhoComSubPasta.getParent().getParent());

        return caminhoComSubPasta;
    }

    private static Path criaPasta() throws IOException {
        Path pasta = Paths.get("minha-pasta-nio");
        if(Files.notExists(pasta)) {
            Files.createDirectory(pasta); // se ja existir lança Exception, por isso validamos
        }

        System.out.println(pasta.getFileName());
        System.out.println(pasta.toFile().isDirectory());
        System.out.println(pasta.toFile().getPath());

        return pasta;
    }
}
