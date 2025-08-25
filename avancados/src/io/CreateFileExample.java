package io;

import java.io.File;
import java.io.IOException;

public class CreateFileExample {

    public static void main(String[] args) {

        File file = new File("./uploads/file-example.txt");

        try {
            boolean isCriado = file.createNewFile();

            System.out.println(isCriado);

        } catch (IOException e) {
            throw new RuntimeException(e);
        }


    }

}
