package school.sptech;

import java.util.ArrayList;
import java.util.List;

public class Hotel {

    private String nome;
    private List<Hospede> hospedes;

    public Hotel() {
        this.hospedes = new ArrayList<>();
    }

    public Hotel(String nome) {
        this();
        this.nome = nome;
    }

    public void checkIn(Hospede hospede, Integer quarto){
        if(quarto<=0){
            throw new RuntimeException("quarto invalido, menor que zero idiota");
        }

        for (Hospede hospedeAtual : this.hospedes) {
            if(hospedeAtual.getQuarto().equals(quarto))
                throw new RuntimeException("Quarto já ocupado");
        }

        hospede.setQuarto(quarto);
        this.hospedes.add(hospede);
    }

    public Boolean checkOut(Hospede hospede){
        return this.hospedes.remove(hospede);
    }


    public Boolean verificarDisponibilidadeDoQuarto(Integer quarto){
        return this.hospedes.stream().anyMatch(h->h.getQuarto().equals(quarto));
    }

    public void exibirHospedes(){
        for (Hospede hospede : this.hospedes) {
            String mensagem = """
                    -------------------------------------------
                    nome: %s
                    documento: %s
                    quarto: %s
                    dias: %s
                    consumo: %s
                    -------------------------------------------
                    """.formatted(hospede.getNome(), hospede.getDocumento(), hospede.getQuarto(), hospede.getDias(), hospede.getConsumo());
            System.out.println(mensagem);
        }
    }

    public List<Hospede> getTuristas(){
        List<Hospede> hospedes = new ArrayList<>();

        for (Hospede hospede : this.hospedes) {
            if(hospede instanceof Turista turista)
                hospedes.add(turista);
        }

        return hospedes;
    }

    public List<Hospede> getClientesVip(){
        List<Hospede> hospedes = new ArrayList<>();

        for (Hospede hospede : this.hospedes) {
            if(hospede instanceof Executivo executivo)
                if(executivo.getVip())
                    hospedes.add(executivo);
        }

        return hospedes;
    }

    public String getNome() {
        return nome;
    }

    public List<Hospede> getHospedes() {
        return hospedes;
    }

    @Override
    public String toString() {
        return "Hotel{" +
               "nome='" + nome + '\'' +
               ", hospedes=" + hospedes +
               '}';
    }
}
