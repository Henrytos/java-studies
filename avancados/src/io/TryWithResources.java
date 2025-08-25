package io;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.StringReader;
import java.io.StringWriter;

public class TryWithResources {

    public static void main(String[] args) {

        try (
                BufferedReader bufferedReader = new BufferedReader(
                        new StringReader("Olá mundo") // BufferedReade espera um Reader in
                );

                StringWriter stringWriter = new StringWriter();
        ) {

            stringWriter.write(bufferedReader.readLine());

            System.out.println(stringWriter.toString());
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }

    }
}
