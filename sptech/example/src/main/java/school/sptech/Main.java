package school.sptech;

public class Main {

    public static void main(String[] args) {
        Turista turista = new Turista("henry", "1", 1, 10, 10.0, true);
        Executivo executivo = new Executivo("manoel", "2", 2, 10, 10.0, true, true);

        Hotel hotel = new Hotel("sptech hotel");
        System.out.println(turista.calcularDiaria());;
        System.out.println(executivo.calcularDiaria());

        hotel.checkIn(turista, 1);
        hotel.checkIn(executivo, 2);

        hotel.exibirHospedes();

        hotel.checkOut(turista);
        hotel.exibirHospedes();

    }
}