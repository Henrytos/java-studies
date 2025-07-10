import java.time.LocalDate;
import java.util.UUID;

public class Cliente extends Pessoa {
    private String email;

    public Cliente(UUID id, String nome, LocalDate dataNascimento, String email) {
        super(id, nome, dataNascimento);
        this.email = email;
    }

    public String getEmail() {
        return email;
    }

}