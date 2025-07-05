package testes.src.JAVA_IO;

import java.io.File;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class LeituraEscrita {
    public static void main(String[] args) {
        try{
            // LEER BYTES DE UM ARQUIVO
            Path path = Paths.get("./uploads/text.txt");
            byte[] bytesFile = Files.readAllBytes(path);

            // LEER CONTEUDO DE UM ARQUIVO
            String content  = new String(bytesFile);
            System.out.println(content);

            // LEER CONTEUDO DE UM ARQUIVO
            content = Files.readString(path);
            System.out.println(content);

            // LEER CONTEUDO DE UM ARQUIVO EM LIST
            List<String> lines = Files.readAllLines(path);
            lines.forEach(System.out::println); // lines.forEach(l -> System.out.println(l));


            // ESCREVER CONTEUDO DE UM ARQUIVO(STRING)
            content = "Henry Franz Ramos Arcaya";
            Files.writeString(path, content); // Files.write(path, content.getBytes(StandardCharsets.UTF_8));

            List<String> names = new ArrayList<String>();
            names.add("henry");
            names.add("lucas");
            names.add("matheus");
            names.add("ezequiel");

            // ESCREVER CONTEUDO DE UM ARQUIVO(STRING BUILDER)
            StringBuilder contentBuilder = new StringBuilder();
            names.forEach(name-> contentBuilder.append(name.concat("\n")));

            Files.writeString(path, contentBuilder.toString()); // Files.writeString(path, contentBuilder.toString().getBytes(StandardCharsets.UTF_8));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
