import java.util.ArrayList;

public  class UsersRepository{

    private ArrayList<User> items = new ArrayList<User>();

    public void create(User entity){
        this.items.add(entity);
    }

    public User findById(double id){
        User user ;

        for(int position = 0; position < this.items.size(); position++ ){
            double currentUserId = this.items.get(position).getId();
            if(currentUserId == id){
                user = this.items.get(position);
                return user;
            }
        }

        throw new Error("user not found");
    }
}