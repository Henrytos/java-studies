import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.IOException;
import java.util.Iterator;

public class LeituraDeTabela {

    /**
    * Pasta de trabalho(WorkBook): um arquivo de pasta de trabalho do Excel (.xlsx ou .xls).
    * Planilha (Sheet): Uma planilha em um arquivo de pasta de trabalho do Excel.
    * Linha (Row): Uma linha de células em uma planilha do Excel.
    * Célula (Cell): Uma célula em uma planilha do Excel.
    * */
    public static void main(String[] args) throws IOException {
        Workbook workbook = new XSSFWorkbook("./data.xlsx");

        Sheet sheet = workbook.getSheetAt(0);

        Iterator<Row> rowIterator =  sheet.rowIterator();
        while (rowIterator.hasNext()){
            Row row = rowIterator.next();
            Iterator<Cell> cellIterator = row.cellIterator();

            while (cellIterator.hasNext()){
                Cell cell = cellIterator.next();
                System.out.println(cell);
            }
        }

        workbook.close();
    }

}
