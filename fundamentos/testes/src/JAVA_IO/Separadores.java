package testes.src.JAVA_IO;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class Separadores {
// qual diferença entre ArrayList e List ?
    public static void main(String[] args) {

    }

    static void  leitura()throws IOException{
        Path path = Paths.get("./uploads/file.csv");

            List<String> lines  =  Files.readAllLines(path);
            ArrayList<Pessoa> pessoas = new ArrayList();

            for (String  line: lines){
                String[] data = line.split(";");

                Pessoa pessoa = new Pessoa(String.valueOf(data[0]), String.valueOf(data[1]), LocalDate.parse(data[2]));
                pessoas.add(pessoa);
                System.out.format("nome:%s email:%s data de nascimento:%s %s",pessoa.getNome(), pessoa.getEmail(), pessoa.getDataDeNascimento().toString(), System.lineSeparator());
            }
            System.out.format("Quantidade de pessoas %d", pessoas.size());

    }

    static void escrevendo(){
        ArrayList<Pessoa> pessoas = new ArrayList<>();
        pessoas.add(new Pessoa("henry", "henry@example.com", LocalDate.now().minusYears(18)));
        pessoas.add(new Pessoa("nathalia", "nathalia@example.com", LocalDate.now().minusYears(19)));
        pessoas.add(new Pessoa("maria", "maria@example.com", LocalDate.now().minusYears(20)));
        pessoas.add(new Pessoa("joao", "joao@example.com", LocalDate.now().minusYears(21)));
        pessoas.add(new Pessoa("ana", "ana@example.com", LocalDate.now().minusYears(22)));
        pessoas.add(new Pessoa("lucas", "lucas@example.com", LocalDate.now().minusYears(23)));

        Path path = Paths.get("./uploads/file.csv");

        StringBuilder stringBuilder = new StringBuilder();
        String separator  = ";";
        pessoas.forEach(pessoa -> {
            stringBuilder.append(pessoa.getNome().concat(separator));
            stringBuilder.append(pessoa.getEmail().concat(separator));
            stringBuilder.append(pessoa.getDataDeNascimento());
            stringBuilder.append(System.lineSeparator());
        });
        try{
            Files.writeString(path, stringBuilder.toString());
        }catch (Exception e){
            System.out.println(e.getMessage());
        }finally {
            System.out.println(stringBuilder);
            System.out.println("finalizando programa");
        }
    }
}
