import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.NumberFormat;
import java.util.List;
import java.util.Locale;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) throws Exception {

        Path path = Paths.get("./data.csv");
        List<String> lines = Files.readAllLines(path);

        StringBuilder builder = new StringBuilder();
        String separator = ",";

        for (int i = 1 ; i < lines.size() && i < 10;i++) {
            String line = lines.get(i);
            String[] data = line.split(separator);
            int index = Integer.valueOf(data[0]).intValue();
            String airline = data[1];
            String flight = data[2];
            String source_city = data[3];
            String departure_time = data[4];
            String stops = data[5];
            String arrival_time = data[6];
            String destination_city = data[7];
            String classe = data[8];
            String duration = data[9];
            String days_left = data[10];
            String price = data[11];

            Locale localeBR = new Locale("pt", "BR");
            NumberFormat currencyFormatter = NumberFormat.getCurrencyInstance(localeBR);
            String valorFormatadoBR = currencyFormatter.format(Double.valueOf(price));

            String content = String.format("Eu sai de %s para %s e custou: %s",source_city.toUpperCase(), destination_city.toUpperCase(), valorFormatadoBR.toString()).concat(System.lineSeparator());
            builder.append(content);
        }

        System.out.println(builder);
    }
}