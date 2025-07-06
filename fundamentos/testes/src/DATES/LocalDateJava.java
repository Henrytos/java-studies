package testes.src.DATES;

import java.time.LocalDate;
import java.time.Month;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;

public class LocalDateJava {
    public static void main(String[] args) {
        //        INCIALIZAÇÃO
        LocalDate localDate = LocalDate.now(); // metodo util de contrução
        System.out.println(localDate);
        System.out.println(localDate.getYear());
        System.out.println(localDate.getDayOfWeek());
        System.out.println(localDate.getMonth());
        System.out.println(localDate.getMonthValue());

        //        DEFINIÇÂO
        LocalDate meuAniversario = LocalDate.of(2006, 10, 13);
        LocalDate meuAniversario2 = LocalDate.of(2006, Month.OCTOBER, 13);
        System.out.println(meuAniversario);
        System.out.println(meuAniversario2);

        //        FORMATAÇÂO
        String meuAniversarioEmTexto = "2006-10-13";
        LocalDate meuAniversario3 = LocalDate.parse(meuAniversarioEmTexto);
        System.out.println(meuAniversario3);

        String meuAniversarioEmTexto2 = "13/10/2006";
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy"); // formatação de datas
        LocalDate meuAniversario4 = LocalDate.parse(meuAniversarioEmTexto2, formatter);
        System.out.println(meuAniversario4);

        //        ATRIBUIÇÂO
        LocalDate anoAleatorio = LocalDate.of(2000, 1, 1);
        LocalDate anoAleatorioNoPassado = anoAleatorio.minusYears(2);
        LocalDate anoAleatorioNoFuturo = anoAleatorio.plusYears(2);
        System.out.format("ANO ALEATORIO %s e seu passado é %s e seu futuro é %s", anoAleatorio, anoAleatorioNoPassado, anoAleatorioNoFuturo);
        //        COMPARAÇÂO
        System.out.println(anoAleatorio.isAfter(anoAleatorioNoPassado));
        System.out.println(anoAleatorioNoPassado.isBefore(anoAleatorio));
        System.out.println(anoAleatorioNoFuturo.isEqual(anoAleatorioNoFuturo));

        //        FORMATAÇÂO DE EXIBIÇAO

        String dataFormatada = meuAniversario.format(DateTimeFormatter.ofLocalizedDate(FormatStyle.FULL));
        System.out.println(dataFormatada); // sexta-feira, 13 de outubro de 2006

        dataFormatada = meuAniversario.format(DateTimeFormatter.ofLocalizedDate(FormatStyle.LONG));
        System.out.println(dataFormatada); // 13 de outubro de 2006

        dataFormatada = meuAniversario.format(DateTimeFormatter.ofLocalizedDate(FormatStyle.MEDIUM));
        System.out.println(dataFormatada); // 13 de out. de 2006

        dataFormatada = meuAniversario.format(DateTimeFormatter.ofLocalizedDate(FormatStyle.SHORT));
        System.out.println(dataFormatada); // 13/10/2006
    }
}
