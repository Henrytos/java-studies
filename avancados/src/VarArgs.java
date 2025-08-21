import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

class ToDoList<T>{
    private final List<T> list = new ArrayList<>();

    //Ela informa ao compilador que o método é seguro ao
    // lidar com o array implícito gerado pelo
    // varargs, evitando avisos de segurança e
    // possíveis erros de tipo
    @SafeVarargs
    public final void addTodo(T... todo){
        list.addAll(Arrays.stream(todo).toList());
    }

    public void showAll(){
        for (T todo: list){
            System.out.println(todo);
        }
    }

}

public class VarArgs {
    public static void main(String[] args) {
        ToDoList<String> toDoList = new ToDoList<>();

        toDoList.addTodo("acordar");
        toDoList.addTodo("tomar banho");
        toDoList.addTodo("escovar os dentes");
        toDoList.addTodo("ir para academia");
        toDoList.addTodo("estagiar");
        toDoList.addTodo("estudar");
        toDoList.addTodo("ir para faculdade");
        toDoList.addTodo("dormir");

        toDoList.showAll();
    }
}
