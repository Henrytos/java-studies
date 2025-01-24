
public class User extends Entity {
    private String name;
    private String email;

    private Address address;

    public User(String name, String email, Address address){
        this.name = name;
        this.email = email;
        this.address = address;
    }

    public void setName(String name){
        this.name = name;
    }

    public void setEmail(String email){
        this.email = email;
    }

    public String getName(){
        return  this.name = name;
    }

    public String getEmail(){
        return  this.email;
    }

    public Address getAddress(){
        return this.address;
    }

    public void showData(){
        System.out.println("------- user data -------");
        System.out.println("id: "+this.getId());
        System.out.println("email: "+this.getEmail());
        System.out.println("name: "+this.getName());
    }

}

