package testes.src.DATES;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class LocalTimeJava {
    public static void main(String[] args) {
        LocalTime localTime = LocalTime.of(12,30,10,0);
        System.out.println(localTime); // HH:mm:ss

        LocalTime localTimePlusOneHour = localTime.plusHours(1);
        System.out.println(localTimePlusOneHour); // HH:mm:ss
        System.out.println("localTime.isAfter(localTimePlusOneHour) ".concat(String.valueOf(localTime.isAfter(localTimePlusOneHour))));
        System.out.println("localTime.isBefore(localTimePlusOneHour) ".concat(String.valueOf(localTime.isBefore(localTimePlusOneHour))));
        System.out.println("localTime.equals(localTimePlusOneHour) ".concat(String.valueOf(localTime.equals(localTimePlusOneHour))));
        System.out.println("localTime.equals(localTime) ".concat(String.valueOf(localTime.equals(localTime))));

        String currentTimeInText = localTime.toString();
        LocalTime currentTime = LocalTime.parse(currentTimeInText);

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH-mm-ss");
        String currentTimeFormat =  currentTime.format(formatter);
        System.out.println(currentTimeInText.concat(" ").concat(currentTimeFormat));

        currentTime = LocalTime.parse(currentTimeFormat, formatter);
        System.out.println(currentTime);
    }
}
