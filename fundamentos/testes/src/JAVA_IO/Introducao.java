package testes.src.JAVA_IO;

import java.io.File;

public class Introducao {
    public static void main(String[] args) {
        File directory = new File("./uploads");

        if(!directory.exists())
            directory.mkdirs();

        File file = new File(directory,"file.csv");

        try {
            file.createNewFile();
        } catch (Exception e){
            System.err.println("error in create file");
        }
    }
}
