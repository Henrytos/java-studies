package testes.src.JAVA_IO;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Stack;

public class Separadores {

    public static void main(String[] args) {
        Path path = Paths.get("./uploads/file.csv");

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
