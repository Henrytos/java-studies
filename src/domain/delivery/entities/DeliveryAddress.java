package domain.delivery.entities;

import java.util.UUID;

public class DeliveryAddress {
    private UUID id = UUID.randomUUID();
    private String state;
    private String city;
    private String road;
    private String number;

    public DeliveryAddress(String state, String city, String road, String number) {
        this.state = state;
        this.city = city;
        this.road = road;
        this.number = number;
    }


    public void showData() {
        System.out.println("------- address data -------");
        System.out.println("state: " + this.state);
        System.out.println("city: " + this.city);
        System.out.println("road: " + this.road);
        System.out.println("number: " + this.number);

    }

    public UUID getId() {
        return this.id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getRoad() {
        return road;
    }

    public void setRoad(String road) {
        this.road = road;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }
}
