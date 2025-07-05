package testes.src.DATES;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

public class TransformationDateTime {
    public static void main(String[] args) {
        LocalDateTime localDateTime = LocalDateTime.now();
        LocalDate localDate = localDateTime.toLocalDate();
        LocalTime localTime = localDateTime.toLocalTime();

        waitLocalDate(localDate);
        waitLocalTime(localTime);
        waitLocalDateTime(localDate.atTime(localTime));
        waitLocalDateTime(localTime.atDate(localDate));

        // localDateTime.toLocalTime(), localDateTime.toLocalTime()
        // obter data ou hora local de uma "LocalDateTime"
        // .atTime(time) adicionar um tempo a uma "LocalDate"
        // .atDate(date) adicionar uma data a uma "LocalTime"
    }

    static void waitLocalDate(LocalDate date){
        System.out.println("waitLocalDate:".concat(String.valueOf(date)));
    }

    static void waitLocalTime(LocalTime time){
        System.out.println("waitLocalTime:".concat(String.valueOf(time)));
    }

    static void waitLocalDateTime(LocalDateTime dateTime){
        System.out.println("waitLocalDateTime:".concat(String.valueOf(dateTime)));
    }
}
