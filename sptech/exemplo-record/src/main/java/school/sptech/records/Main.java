package school.sptech.records;

public class Main {
    public static void main(String[] args) {
        Usuario usuario1 = new Usuario(1, "henry", "henry@gmail.com", "urubu100", "119676083");
        Usuario usuario2 = new Usuario(2, "matheus", "matheus@gmail.com", "urubu100", "119676081");
        Usuario usuario3 = new Usuario(3, "henry", "henry@gmail.com", "urubu100", "119676083");

        if(usuario1.toString().equals(usuario3.toString())){
            System.out.println("São iguais");
        }else {
            System.out.println("São diferentes");
        }

        System.out.println(usuario1);
    }
}
