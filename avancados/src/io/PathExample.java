package io;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class PathExample {
    public static void main(String[] args) {

        Path path = Paths.get("./uploads/file-example.txt");

        System.out.println("Nome do arquivo: ".concat(path.getFileName().toString()));
        System.out.println("Nome na pasta pai".concat(path.getParent().toString()));
        System.out.println(path.getRoot());
        System.out.println(path.getNameCount());


        try{
            Files.deleteIfExists(path);
        }catch (IOException e){
            System.out.println(e.getMessage());
        }

    }
}
