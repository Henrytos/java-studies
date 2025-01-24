
public class Main {
    public  static void main(String[] args){

        // criando usuario
        Address address = new Address("sp", "sp", "road", "001");
        User user = new User("henry","henry@gmail.com", address);
        user.showData();
        user.getAddress().showData();

        // salvando usuario em banco de dados em memoria
        UsersRepository usersRepository = new UsersRepository();
        usersRepository.create(user);

        // procurando usuario
        User currentUser = usersRepository.findById(user.getId());
    }
}