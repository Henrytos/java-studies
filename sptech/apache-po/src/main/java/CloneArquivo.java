import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

public class CloneArquivo {

    public static void main(String[] args) throws IOException {

        try (
                Workbook workbook = new XSSFWorkbook("data.xlsx");
                OutputStream outputStream = new FileOutputStream("data-clone.xlsx");
        ) {
            workbook.write(outputStream);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

}
