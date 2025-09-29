import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class CriacaoDeTabela {

    public static void main(String[] args) throws IOException {
        Workbook workbook = new XSSFWorkbook();
        Sheet sheet = workbook.createSheet("planilha");

        Row headerRow = sheet.createRow(0);
        String[] headerStrings = new String[]{
                "Nome",
                "Email",
                "Senha"
        };

        for (int i = 0; i < headerStrings.length; i++) {
            headerRow.createCell(i).setCellValue(headerStrings[i]);
        }

        String[][] bodyString = new String[][]{
                {"henry", "henry@gmail.com", "123456"},
                {"nathalia", "nathalia@gmail.com", "123456"},
                {"mariana", "mariana@gmail.com", "123456"}
        };

        for (int i = 1; i <= bodyString.length; i++) {
            Row rowTarget = sheet.createRow(i);

            for (int c = 0; c < bodyString[i - 1].length; c++) {
                rowTarget.createCell(c).setCellValue(bodyString[i - 1][c]);
            }
        }

        try (
                FileOutputStream outputStream = new FileOutputStream("create-data.xlsx");

        ) {
            workbook.write(outputStream);

        }


    }

}
