package school.sptech;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class ExercicioDataHora {

    Boolean isPassado(LocalDateTime dataHoraInicio, LocalDateTime dataHoraFim) {
        return dataHoraInicio.isBefore(dataHoraFim);
    }

    Integer calcularIdade(LocalDate dataNascimento, LocalDate dataAtual) {


        return Period.between(dataNascimento, dataAtual).getYears();
    }

    Boolean isFinalDeSemana(LocalDate data) {
        System.out.println(data.getDayOfWeek().getValue());
        return data.getDayOfWeek().getValue() == 7 || data.getDayOfWeek().getValue() == 6;
    }

    LocalDate proximoDiaUtil(LocalDate data) {
        if (this.isFinalDeSemana(data)) {
            if (data.getDayOfWeek().getValue() == 6)
                return data.plusDays(2);
            return data.plusDays(1);
        }

        return data;
    }

    String formatarDataHora(LocalDateTime dataHora) {
        String sigla = dataHora.getHour() >= 12 ? "PM" : "AM";
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("MM/dd/yyyy hh:mm:ss '%s' ('nanosegundos': SSS)".formatted(sigla));

        return dataHora.format(dateTimeFormatter);
    }

    List<LocalDate> gerarReunioesSemanais(LocalDate dataComeco, LocalDate dataFim, List<Integer> diasDaSemana) {
        List<LocalDate> datasAgendadas = new ArrayList<>();

        if (dataComeco.equals(dataFim) && diasDaSemana.contains(1)) {
            datasAgendadas.add(dataComeco);

            return datasAgendadas;
        }

        while (dataComeco.isBefore(dataFim)) {
            if (diasDaSemana.contains(dataComeco.getDayOfWeek().getValue())) {
                datasAgendadas.add(dataComeco);
            }

            dataComeco = dataComeco.plusDays(1);
        }

        return datasAgendadas;
    }

    LocalDate calcularDiaDosPais(Integer ano) {
        LocalDate date = LocalDate.of(ano, 8, 1);
        Integer plusDays = 14 - date.getDayOfWeek().getValue();

        return date.plusDays(plusDays);
    }
}
