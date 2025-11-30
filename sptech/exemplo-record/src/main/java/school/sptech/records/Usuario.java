package school.sptech.records;

// cria construtores
// cria getters
// cria equals e hashcode

// primary contrutor => kotlin
// constructor canonic => canonico => java
public record Usuario(
        Integer id,
        String nome,
        String email,
        String senha,
        String telefone
) {

    public Usuario(Integer id, String nome, String email, String senha){
        this(id, nome, email, senha, "");
    }

    public Usuario(Integer id) { // contrutor vazio
        this(id, "", "", "", "");
    }

    public Usuario {
        // regras de negocio

        if(id == null || id <= 0){
            throw new IllegalArgumentException("Não pode id nulo ou menor que zero");
        }
    }

    //canonico => sempre é chamdo no final
    //costumizado => argumentos parciais
    // compacto => corpo canonico
}