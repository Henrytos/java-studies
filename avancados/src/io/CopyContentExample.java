package io;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

public class CopyContentExample {

    public static void main(String[] args) {
        Path source = Paths.get("./sources/file.txt");
        Path dist = Paths.get("./dist/file.txt");


        try {
            Files.copy(source, dist, StandardCopyOption.REPLACE_EXISTING);

        } catch (IOException e) {
            throw new RuntimeException(e);
        }


    }

}
