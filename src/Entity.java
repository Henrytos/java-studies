import java.util.Date;

public class Entity {
    protected double id ;
    protected Date createdAt;

    public Entity(){
        this.id = Math.random() * 1000;
        this.createdAt = new Date();
    }

    public  double getId(){
        return this.id;
    }

    public void showData(){
        System.out.println("id: "+this.getId());
        System.out.println("created at: "+this.createdAt);
    }
}
