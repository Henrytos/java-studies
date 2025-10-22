package school.sptech;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ListaEstatica {

    private Integer[] vetor;
    private int nroElem;

    public ListaEstatica(int capacidade) {
        this.vetor = new Integer[capacidade];
    }

    Integer lastIndex() {
        for (int i = 0; i < vetor.length; i++) {
            if (this.vetor[i] == null) {
                return i;
            }
        }

        return -1;
    }


    void adicionar(Integer elemento) {
        if (this.lastIndex().equals(-1)) {
            System.out.println("Lista cheia");
            return;
        }

        this.vetor[this.lastIndex()] = elemento;
        nroElem = this.lastIndex();
    }

    Integer busca(Integer elemento) {
        for (int i = 0; i < this.vetor.length; i++) {
            if (this.vetor[i] != null && this.vetor[i].equals(elemento)) {
                return i;
            }
        }

        return -1;
    }


    Boolean removePeloIndice(int indice) {
        if (indice > this.vetor.length || indice < 0) {
            return false;
        }

        if (this.vetor[indice] == null) {
            return false;
        }

        this.vetor[indice] = null;
        this.nroElem--;

        ListaEstatica listaEstatica = new ListaEstatica(this.vetor.length);

        for (int i = 0; i < this.vetor.length; i++) {
            if (this.vetor[i] != null) {
                listaEstatica.adicionar(this.vetor[i]);
            }
        }

        this.vetor = listaEstatica.vetor;
        return true;
    }

    Boolean removeElemento(Integer elemento) {
        Integer index = this.busca(elemento);

        if (index.equals(-1))
            return false;

        removePeloIndice(index);
        return true;
    }

    @Override
    public String toString() {
        List<Integer> listValida = new ArrayList<>();

        for (Integer item : this.vetor) {
            if(item != null)
                listValida.add(item);
        }

        return Arrays.toString(listValida.toArray());
    }
}