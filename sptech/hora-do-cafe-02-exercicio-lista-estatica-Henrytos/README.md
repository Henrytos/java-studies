# Hora do Café - 002 ☕ - Lista Estática 📋️

> "A lista é a origem da cultura. É parte da história da arte e da literatura. O que a cultura quer? Tornar o infinito compreensível."
> *Umberto Eco*

## Orientações Gerais: 🚨

1. Utilize **apenas** tipos **wrapper** para criar variáveis.
2. Verifique se **não** há **erros de compilação** no projeto antes de enviar.
3. Tome **cuidado** com os **argumentos** especificados no exercício.
   **Não** adicione argumentos não solicitados e mantenha a ordem definida no enunciado.
4. Verifique se **não** há **erros de compilação** no projeto antes de enviar.
5. As classes devem seguir as regras de encapsulamento.

## Lista Estática: 📚️

**Lista** é um tipo de estrutura de dados, caracterizada por conter informações do mesmo tipo.

As listas podem ser implementadas de forma estática ou dinâmica.
A implementação de uma lista dinâmica permite que o seu tamanho seja alterado em tempo de execução.
Nesse exercício, trabalharemos com a implementação de uma lista estática do zero.

As **listas estáticas** podem ser implementadas por **vetores (ou arrays)**.
Os vetores caracterizam-se por armazenar elementos do mesmo tipo.
O tamanho do vetor é definido no momento da criação do vetor, de forma estática (não é alterado em
tempo de execução).

O **vetor** tem o seu **índice variando entre 0 e n-1** (sendo n o número de elementos do vetor)

Vamos implementar uma classe que representa uma Lista Estática.
Inicialmente, vamos implementar uma lista estática de inteiros.

Segue a descrição de como será a classe:

### Classe `ListaEstatica` 🗂️

#### Atributos: 📌

A classe possui os seguintes atributos:

* `Integer[] vetor`
* `int nroElem` - Representa a quantidade de elementos realmente inserida no vetor e também
  representa o índice onde será inserido o próximo valor no vetor.

#### Construtor: 🏗️

* `ListaEstatica(capacidade: int)`

    * Recebe a capacidade máxima do vetor.
    * Inicializa o vetor com o tamanho informado.
    * Inicializa nroElem.

#### Métodos: 🛠️

Implemente os seguintes métodos:

* `adicionar(elemento: Integer): void` ➕

    * Recebe o elemento a ser inserido.
    * Se o vetor estiver “cheio”, exibe uma mensagem de “Lista cheia” e não adiciona o elemento no
      vetor.


* `busca(elemento: Integer): Integer` 🔍

    * Recebe o elemento a ser procurado
    * Devolve o índice do vetor onde está o elemento ou -1 se não encontrou.


* `removePeloIndice(indice: int): Boolean` ❌

    * Recebe o índice do elemento a ser removido
    * Devolve true se removeu ou false se índice inválido
    * Reorganiza o vetor caso necessário.
    * Exemplo:
        * Vetor inicial: [1, 2, 3]
        * Remover índice: 1
        * Resultado: [1, 3, null]


* `removeElemento(elemento: Integer): Boolean` 🧹

    * Recebe o elemento a ser removido
    * Procura o elemento a ser removido
    * Devolve true se removeu e false se não encontrou
    * Reorganiza o vetor caso necessário.
    * Exemplo:
        * Vetor inicial: [10, 20, 30]
        * Remover elemento: 20
        * Resultado: [10, 30, null]


* `toString(): String` 📝

    * Retorna os elementos do vetor em formato texto.
    * Ex: \[10, 90, 80, 4, 8]
