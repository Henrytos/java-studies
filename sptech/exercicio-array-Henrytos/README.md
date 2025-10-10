[![Review Assignment Due Date](https://classroom.github.com/assets/deadline-readme-button-22041afd0340ce965d47ae6ef1cefeee28c7c493a6346c4f15d667ab976d596c.svg)](https://classroom.github.com/a/WR19KB6H)
# Exercício - Vetores 📎

## Orientações Gerais: 🚨
1. Utilize **apenas** tipos **wrapper** para criar variáveis.
2. Verifique se **não** há **erros de compilação** no projeto antes de enviar.
3. Respeite os nomes de atributos e métodos definidos no exercício.
4. Tome cuidado com os argumentos especificados no exercício. Não adicione argumentos não
   solicitados e mantenha a ordem definida no enunciado.

### Exercício 1 - Vetores

Dentro da classe ExercicioVetores crie os seguintes métodos:

* `Integer somar(Integer[] vetor)`
  * Deve retornar o resultado da soma de todos os elementos do vetor.


* `Double calcularMedia(Double[] notas)`
  * Deve retornar a média aritmética de todas as notas contidas no vetor `notas`.


* `Integer buscarMaiorNumero(Integer[] vetor)`
  * Deve retornar o maior valor dentro do vetor de números.


* `Integer calcularDecimal(Integer[] binario)`
  * Recebe um vetor contendo um valor binário e calcula o valor decimal correspondente.
  * Exemplo:
    * **Input:** binario = [1, 0, 1, 0]
    * **Output:** 10
  * Dica:
    * Para converter o Math.pow para o tipo Integer utilize "((int) Math.pow(2.0, 2.0))"


* `Character[] inverter(Character[] vetor)`
  * Deve inverter o vetor recebido como argumento e retornar.
  * Exemplo: `['j', 'a', 'v', 'a'] => ['a', 'v', 'a', 'j']`



* `Integer[] mesclar(Integer[] vetor1, Integer[] vetor2)`
  * Leia dois vetores de tamanhos diferentes (ordenados) e crie um terceiro vetor intercalando seus elementos.
  * Exemplo: \
    A = [1, 2, 5, 8] \
    B = [3, 4, 6] \
    Resultado = [1, 2, 3, 4, 5, 6, 8]


### Desafio - Two Sum

* `Integer[] somarDois(Integer[] vetor, Integer alvo)`
  * Dado um vetor de números inteiros e um inteiro alvo, retorne os índices dos dois números de forma que eles somem o número alvo.
  * Você pode assumir que cada entrada tem exatamente uma solução.
  * Exemplo 1:
    * **Input:** vetor = [2,7,11,15], alvo = 9 \
      **Output:** [0, 1]
  * Exemplo 2:
    * **Input:** vetor = [2,3,4], alvo = 6 \
      **Output:** [0, 2]
