public class Address {
    private String state;
    private String city;
    private String road;
    private String number;

    public Address(String state, String city, String road, String number){
        this.state = state;
        this.city = city;
        this.road = road;
        this.number = number;
    }

    public String getState(){
        return this.state;
    }

    public String getCity() {
        return this.city;
    }

    public String getRoad() {
        return this.road;
    }

    public String getNumber() {
        return this.number;
    }

    public void showData(){
        System.out.println("------- address data -------");
        System.out.println("state: " + this.state);
        System.out.println("city: " + this.city);
        System.out.println("road: " + this.road);
        System.out.println("number: " + this.number);

    }
}
