import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        List<Livro> livros = new ArrayList<>();
        List<Autor> autores = new ArrayList<>();
        List<Emprestimo> emprestimos = new ArrayList<>();
        List<Cliente> clientes = new ArrayList<>();

        popularLivraria(livros, autores);

        Biblioteca biblioteca = new Biblioteca(livros, autores, emprestimos, clientes);

        System.out.println("Olá bem vindo a rocket library 💜");
        System.out.format("desejaria ver os livros disponiveis(%d) sim/não ? ", biblioteca.getLivros().size());

        String escolha = scanner.nextLine();

        while (!escolha.equals("não")) {
            switch (escolha) {
                case "sim":
                    var conteudo = obterRelatorioDosLivros(livros);
                    System.out.println(conteudo);

                    System.out.println("se interessou em algum livro(sim/não)?");
                    escolha = scanner.nextLine();

                    switch (escolha) {
                        case "sim":
                            criarEmprestimo(biblioteca);

                            System.out.format("desejaria ver os livros disponiveis(%d) sim/não ? ",
                                    biblioteca.getQuantidadeLivrosDiponiveis());
                            escolha = scanner.nextLine();
                            break;
                        case "não":
                            System.out.println("finalizando programa");
                            break;
                        default:
                            break;
                    }

                    break;
                case "não":
                    System.out.println("finalizando programa");
                    return;
                default:
                    System.out.println("infelizmente opção invalida");
                    escolha = "não";
                    break;
            }

        }

    }

    static void popularLivraria(List<Livro> livros, List<Autor> autores) {

        Autor autor1 = new Autor("Machado de Assis", LocalDate.of(1839, 6, 21));
        Autor autor2 = new Autor("George Orwell", LocalDate.of(1903, 6, 25));
        Autor autor3 = new Autor("Clarice Lispector", LocalDate.of(1920, 12, 10));
        Autor autor4 = new Autor("J.K. Rowling", LocalDate.of(1965, 7, 31));
        Autor autor5 = new Autor("J.R.R. Tolkien", LocalDate.of(1892, 1, 3));

        autores.add(autor1);
        autores.add(autor2);
        autores.add(autor3);
        autores.add(autor4);
        autores.add(autor5);

        livros.add(new Livro(1, "Dom Casmurro", autor1, true, LocalDate.now(), LocalDate.now()));
        livros.add(new Livro(2, "1984", autor2, true, LocalDate.now(), LocalDate.now()));
        livros.add(new Livro(3, "A Hora da Estrela", autor3, false, LocalDate.now(), LocalDate.now()));
        livros.add(new Livro(4, "Harry Potter e a Pedra Filosofal", autor4, true, LocalDate.now(),
                LocalDate.now()));
        livros.add(new Livro(5, "O Senhor dos Anéis", autor5, true, LocalDate.now(), LocalDate.now()));
        livros.add(new Livro(6, "Memórias Póstumas de Brás Cubas", autor1, true, LocalDate.now(),
                LocalDate.now()));
        livros.add(
                new Livro(7, "Revolução dos Bichos", autor2, true, LocalDate.now(), LocalDate.now()));
        livros.add(new Livro(8, "Perto do Coração Selvagem", autor3, true, LocalDate.now(),
                LocalDate.now()));
        livros.add(new Livro(9, "Harry Potter e a Câmara Secreta", autor4, false, LocalDate.now(),
                LocalDate.now()));
        livros.add(new Livro(10, "O Hobbit", autor5, true, LocalDate.now(), LocalDate.now()));
    }

    static String obterRelatorioDosLivros(List<Livro> livros) {
        StringBuilder conteudo = new StringBuilder();
        livros.forEach(livro -> {
            if (livro.getDisponivel()) {
                String texto = String.format("(%d) %s - Autor: %s", livro.getId(),
                        livro.getTitulo(), livro.getAutor().getNome());

                conteudo.append(texto.concat(System.lineSeparator()));
            }
        });

        return conteudo.toString();
    }

    static void criarEmprestimo(Biblioteca biblioteca) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("informe o identificador do livro:");

        try {
            int idAlvo = scanner.nextInt();
            scanner.nextLine(); // limpa o \n pendente

            Livro livroAlvo = null;

            for (Livro livro : biblioteca.getLivros()) {
                if (idAlvo == livro.getId()) {
                    livroAlvo = livro;
                    break;
                }
            }
            if (livroAlvo == null) {
                System.out.println("infelizmente não foi encontrado livro...");
                System.out.println("finalizando programa");

                return;
            }

            System.out.println("por favor informe o email :");
            String email = scanner.nextLine();

            Cliente clienteAlvo = null;
            for (Cliente cliente : biblioteca.getClientes()) {
                if (cliente.getEmail().equals(email)) {
                    clienteAlvo = cliente;
                    break;
                }
            }

            if (clienteAlvo == null) {
                System.out.println("por favor informe o nome :");
                String nome = scanner.nextLine();

                System.out.println("por favor informe data nascimento(dd/mm/yyyy) :");
                String dataNascimento = scanner.nextLine();

                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
                LocalDate dataNascimentoFormatada = LocalDate.parse(dataNascimento, formatter);
                clienteAlvo = new Cliente(nome, email, dataNascimentoFormatada);
            }

            LocalDate dataDeEmprestimo = LocalDate.now();
            LocalDate dataDeDevolucao = dataDeEmprestimo.plusWeeks(1);

            Emprestimo novoEmprestimo = new Emprestimo(dataDeEmprestimo, dataDeDevolucao, clienteAlvo);

            biblioteca.getClientes().add(clienteAlvo);
            biblioteca.getEmprestimos().add(novoEmprestimo);
            livroAlvo.setDisponivel(false);

            System.out.format("Obrigado pela preferencia %s enviaremos avisos pelo seguinte correio eletronico: %s %s",
                    clienteAlvo.getNome(), clienteAlvo.getEmail(), System.lineSeparator());
        } catch (Exception e) {
            System.out.println("porfavor selecione um livro valido");
        }

    }
}
