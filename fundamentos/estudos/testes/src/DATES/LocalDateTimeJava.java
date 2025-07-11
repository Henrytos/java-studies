package testes.src.DATES;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class LocalDateTimeJava {
    public static void main(String[] args) {
        LocalDateTime currentDateTime = LocalDateTime.now(); //of(year, month, day , hour, minute, second, nano)
        System.out.println(currentDateTime);// 2025-07-04T19:01:38.142931719

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
        String currentDateTimeFormat = currentDateTime.format(formatter);
        System.out.println(currentDateTimeFormat);

        currentDateTime = LocalDateTime.parse(currentDateTimeFormat, formatter);
        System.out.println(currentDateTime);// 2025-07-04T19:01:38

        LocalDateTime currentDateTimeAfter = currentDateTime.plusSeconds(1);
        System.out.println("currentDateTime.isAfter(currentDateTimeAfter):".concat(String.valueOf(currentDateTime.isAfter(currentDateTimeAfter)))); //false
        System.out.println("currentDateTime.isAfter(currentDateTimeAfter):".concat(String.valueOf(currentDateTime.isBefore(currentDateTimeAfter)))); //true
        System.out.println("currentDateTime.isEqual(currentDateTimeAfter):".concat(String.valueOf(currentDateTime.isEqual(currentDateTimeAfter)))); //false
    }
}
