package school.sptech;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Stack;

public class ExercicioVetores {
    Integer somar(Integer[] vetor) {
        Integer sum = 0;

        Integer l = 0;
        Integer r = vetor.length - 1;

        while (l < r) {
            sum += vetor[l] + vetor[r];

            l++;
            r--;
        }

        if (l.equals(r)) {
            sum += vetor[l];
        }

        return sum;
    }

    Double calcularMedia(Double[] notas) {
        Double sum = 0.0;

        Integer l = 0;
        Integer r = notas.length - 1;

        while (l < r) {
            sum += notas[l] + notas[r];

            l++;
            r--;
        }

        if (l.equals(r)) {
            sum += notas[l];
        }

        return sum / Double.valueOf(notas.length);

    }

    Integer buscarMaiorNumero(Integer[] vetor) {
        Integer maiorNumero = vetor[0];

        Integer l = 1;
        Integer r = vetor.length - 1;

        while (l < r) {
            if (vetor[l] > maiorNumero) {
                maiorNumero = vetor[l];
            }

            if (vetor[r] > maiorNumero) {
                maiorNumero = vetor[r];
            }

            l++;
            r--;
        }

        if (l.equals(r) && vetor[r] > maiorNumero) {
            maiorNumero = vetor[r];
        }

        return maiorNumero;
    }

    Integer calcularDecimal(Integer[] binario) {
        Integer acumulador = 0;
        for (int base = binario.length - 1; base >= 0; base--) {
            Integer indece = binario.length - 1 - base;

            if (binario[indece].equals(1)) acumulador += (int) Math.pow(2.0, base);
        }
        return acumulador;
    }

    Character[] inverter(Character[] vetor) {
        Integer l = 0;
        Integer r = vetor.length - 1;

        while (l <= r) {
            Character temp = vetor[r];

            vetor[r] = vetor[l];
            vetor[l] = temp;

            l++;
            r--;
        }

        return vetor;

    }

    Integer[] mesclar(Integer[] vetor1, Integer[] vetor2) {
//        - 1 juntar os 2 vetores

//        - 1.1 criar um vetor do tamanho dos 2 vetores
        Integer tamanho = vetor1.length + vetor2.length;
        Integer[] vetorMescla = new Integer[tamanho];

//         - 1.2 percorrer um vetorA e adicionar no novo vetor
        for (int i = 0; i < vetor1.length; i++) {
            vetorMescla[i] = vetor1[i];
        }
//        - 1.3 percorrer um vetorB e adicionar no novo vetor
        for (int i = 0; i < vetor2.length; i++) {
            Integer posicao = vetor1.length+i;
            vetorMescla[posicao] = vetor2[i];
        }

        System.out.println(Arrays.toString(vetor1));
        System.out.println(Arrays.toString(vetor2));
        System.out.println(Arrays.toString(vetorMescla));


//        - 2 criar um vetor ordenado
//        - 2.1 criar um vetor de tamanho de todos elementos
        Integer[] vetorOrdenado = new Integer[tamanho];
        Integer ultimaPosicaoPrenchida = 0;
//        - 2.2 enquanto o vetor não estiver totalmente prenchido
//        adicione o menor numero ao seu inicio valido

        while (ultimaPosicaoPrenchida < tamanho){

            Integer indeceDoMenorElemento = -1;

//        - 2.2.1 encontrar o menor elemento e seu indice
            for (int i = 0; i < vetorMescla.length; i++) {
                if(vetorMescla[i] != null) {
                    indeceDoMenorElemento = i;
                    break;
                }
            }

            Integer menorElemento = vetorMescla[indeceDoMenorElemento]; // procurar um indice valido entre nulos
            for (int i = 0; i < tamanho ; i++) {
                    if( vetorMescla[i] !=null && vetorMescla[i] < menorElemento ){
                        menorElemento = vetorMescla[i];
                        indeceDoMenorElemento=i;
                    }
            }
            System.out.println("Indice do menor elemento "+ indeceDoMenorElemento);

            vetorOrdenado[ultimaPosicaoPrenchida] = menorElemento;
//        - 2.3 após adicionar este elemento elimine do vetorOriginal(vetorMescla)
            vetorMescla[indeceDoMenorElemento] = null;
            ultimaPosicaoPrenchida++;
        }

    return vetorOrdenado;
    }

//    Integer[] mesclar(Integer[] vetor1, Integer[] vetor2) {
//        Integer tamanho = vetor1.length + vetor2.length;
//        if (tamanho.equals(0))
//            return vetor1;
//
//        Integer[] mescla = new Integer[tamanho];
//
//        Integer contador = 0;
//        while(contador < tamanho){
//            if(contador < vetor1.length){
//                mescla[contador] = vetor1[contador];
//            }else{
//                mescla[contador] = vetor2[contador-vetor1.length];
//            }
//
//            contador++;
//        }
//
//        Integer[] ordenado = new Integer[tamanho];
//        Integer ultimoIndex = 0;
//
//        while (ultimoIndex<tamanho) {
//            // pegar menor numero da mescla e seu indice
//            Integer menorIndexEnumerado = menorIndexEnumerado(mescla);
//
//            Integer menorNumero = mescla[menorIndexEnumerado];
//            Integer indiceDoMenorNumero = menorIndexEnumerado;
//
//            for (int i = 0; i < mescla.length; i++) {
//                if (mescla[i] != null && mescla[i] < menorNumero) {
//                    menorNumero = mescla[i];
//                    indiceDoMenorNumero = i;
//                }
//            }
//
//            ordenado[ultimoIndex] = menorNumero;
//            mescla[indiceDoMenorNumero] = null;
//            ultimoIndex++;
//        }
//
//        return ordenado;
//    }
//
//
//    static Integer menorIndexEnumerado(Integer[] numeros) {
//        Integer index = -1;
//
//        for (int i = 0; i < numeros.length; i++) {
//            if (numeros[i] != null) {
//                index = i;
//                break;
//            }
//        }
//
//        return index;
//    }


//    Integer[] somarDois(Integer[] vetor, Integer alvo) {
//        HashMap<Integer, Integer> map = new HashMap<>();
//        for (int i = 0; i < vetor.length; i++) {
//            map.put(vetor[i],i);
//        }
//
//        Integer[] ordenado = new Integer[vetor.length];
//        Integer ultimoIndex = 0;
//        while (ultimoIndex < vetor.length) {
//            Integer menorIndexEnumerado = menorIndexEnumerado(vetor);
//
//            Integer menorNumero = vetor[menorIndexEnumerado];
//
//            for (int i = 0; i < vetor.length && vetor[i] != null ; i++) {
//                    if( vetor[i] < menorNumero) {
//                        menorNumero = vetor[i];
//                        menorIndexEnumerado = i;
//                    }
//            }
//
//            ordenado[ultimoIndex] = menorNumero;
//            vetor[menorIndexEnumerado] = null;
//            ultimoIndex++;
//        }
//
//        Integer l = 0;
//        Integer r = ordenado.length - 1;
//
//        Integer[] resposta = new Integer[2];
//        while (l < r) {
//            if (ordenado[l] + ordenado[r] == alvo) {
//                Integer indexOne = map.get(ordenado[l]);
//                Integer indexTwo = map.get(ordenado[r]);
//
//                Integer temp;
//
//                if(indexOne > indexTwo){
//                    temp = indexOne;
//                    indexOne = indexTwo;
//                    indexTwo = temp;
//                }
//                resposta[0] = indexOne;
//                resposta[1] = indexTwo;
//                break;
//            } else if (ordenado[l] + ordenado[r] > alvo) {
//                r--;
//            } else {
//                l++;
//            }
//        }
//
//        return  resposta;
//
//    }

//    Integer[] somarDois(Integer[] vetor, Integer alvo) {
//        for (int x = 0; x < vetor.length; x++) {
//            for (int y = vetor.length - 1; y > x; y--) {
//                if (vetor[x] + vetor[y] == alvo) {
//                    return new Integer[]{x, y};
//                }
//            }
//        }
//        return null;
//    }


    Integer[] somarDois(Integer[] vetor, Integer alvo) {
        HashMap<Integer, Integer> map = new HashMap<>();

        for (int i = 0; i < vetor.length; i++) {
            map.put(vetor[i], i);
        }

        for (int i = 0; i < vetor.length; i++) {
            Integer diff = Math.abs(alvo - vetor[i]);
            if (map.containsKey(diff) && !diff.equals(vetor[i])) {
                return new Integer[]{i, map.get(diff)};
            }
        }

        return new Integer[]{0, 0};
    }

}