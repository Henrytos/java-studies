package school.sptech.fixture;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import school.sptech.Produto;
import school.sptech.factory.ProdutoFactory;

public class ProdutoFixture {

    public static Produto getProduto() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        return ProdutoFactory.build(4, "Smartphone Galaxy S23", 5200.00, "Samsung",
              LocalDate.parse("09/03/2025", formatter));
    }

    public static Produto getProduto2() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        return ProdutoFactory.build(30, "Câmera DSLR EOS 250D", 3900.00, "Canon",
              LocalDate.parse("11/09/2025", formatter));
    }
}
