import java.time.LocalDate;

public class Cliente extends Pessoa {
    private String email;

    public Cliente(String nome, String email, LocalDate dataNascimento) {
        super(nome, dataNascimento);
        this.email = email;
    }

    public String getEmail() {
        return email;
    }

}