package io;

/*
*   Buffer é um espaço de memoria temporal para
* tranferencia de dados em diferentes compontes do
* computado, muito utilizado em leitura e escrita de dados(BufferedReader && BufferedWriter)
* */

import java.io.*;

public class InputOutput {
    public static void main(String[] args) {
        File file = new File("texto.txt");  // referencia ao arquivo

        try {
            BufferedReader bufferedReader = new BufferedReader(
                    new FileReader(file)
            ); // leito de arquivo

            String line ;

            while((line = bufferedReader.readLine()) != null){ // ler a prixima linha do leitor do arquivo
                System.out.println(line);
            }
        }
        catch (IOException e) {
            throw new RuntimeException(e.getMessage());
        }

    }
}
