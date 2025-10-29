[![Review Assignment Due Date](https://classroom.github.com/assets/deadline-readme-button-22041afd0340ce965d47ae6ef1cefeee28c7c493a6346c4f15d667ab976d596c.svg)](https://classroom.github.com/a/8Pnxj-sG)
# Hora do Café - 004 ☕ - Desafio Matriz 🎮️

> “A lógica é a anatomia do pensamento. A matemática é a estrutura do mundo.” <br>
> *William Ross Ashby*

## Orientações Gerais: 🚨
1. Utilize **apenas** tipos **wrapper** para criar variáveis.
2. **Respeite** os nomes de atributos e métodos definidos no exercício.
3. Tome **cuidado** com os **argumentos** especificados no exercício.
   **Não** adicione argumentos não solicitados e mantenha a ordem definida no enunciado.
4. Verifique se **não** há **erros de compilação** no projeto antes de enviar.

--- 

## Exercício Jogo da Velha 🧓

Você foi contratado por uma empresa de jogos eletrônicos para contribuir no desenvolvimento 
de um jogo da velha em Java. 

A equipe já desenvolveu a engine do jogo e os elementos visuais 
que aparecem na tela, mas estão com dificuldade em implementar as regras do jogo da velha. 
Eles confiaram a você a tarefa de terminar o jogo pela sua vasta experiência trabalhando com 
matrizes no Java.

As classes `Main`, `GameCanvas` e `Configuracao` já estão completas então não faça alterações 
em nenhuma delas para implementar seu código.

### Exercício - Implementação do Jogo

Dentro da classe `Partida` implemente os seguintes métodos:

* `public boolean inserirValor(int x, int y)`
   * Insere o número do jogador na posição recebida como parâmetro.
   * O número do jogador não pode ser inserido caso a posição solicitada já esteja preenchida.
   * O número do jogador não pode ser inserido caso já haja um vencedor
   * Retorna true caso o valor tenha sido inserido com sucesso e false caso não tenha inserido.
   * OBS: o retorno é importante para o bom funcionamento do jogo


* `public void alternarJogador()`
   * Atualiza o atributo `jogadorAtual` alternando para o outro jogador.
   * Se `jogadorAtual` for 1, altera para 2
   * Se `jogadorAtual` for 2, altera para 1


* `public void validarJogada()`
   * Analisa o tabuleiro e procura se existe um vencedor ou a partida empatou.
   * Caso haja um vencedor atualiza o atributo `vencedor` para 1 ou 2 dependendo do jogador que venceu.
   * Em caso de empate atualiza o atributo `vencedor` para 0.


* `public String getMensagem()`
   * Retorna a mensagem que será exibida conforme o estado atual da partida.
   * Caso haja um vencedor na partida retorne "O jogador X venceu!" onde X é o número do jogador atual.
   * Caso haja empate retorne "Empate!"
   * Caso não haja nenhum vencedor retorne "Vez do jogador X" onde X é o número do jogador atual.

Após implementar os métodos rode a classe `Main` para testar o resultado.

### Extra - Personalizar cores ✨

* Abra a classe `Configuracao` e altere as cores para personalizar o jogo!
