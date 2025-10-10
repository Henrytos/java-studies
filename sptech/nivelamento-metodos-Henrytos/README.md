[![Review Assignment Due Date](https://classroom.github.com/assets/deadline-readme-button-22041afd0340ce965d47ae6ef1cefeee28c7c493a6346c4f15d667ab976d596c.svg)](https://classroom.github.com/a/DZtdAwU2)
# Nivelamento - Métodos 📎

## Orientações Gerais: 🚨
1. Utilize **apenas** tipos **wrapper** para criar variáveis.
2. Verifique se **não** há **erros de compilação** no projeto antes de enviar.
3. Respeite os nomes de atributos e métodos definidos no exercício.
4. Tome cuidado com os argumentos especificados no exercício. Não adicione argumentos não solicitados e mantenha a ordem definida no enunciado.

### Exercício 1

Dentro da classe ExercicioMetodos escreva os seguintes métodos:

- `Boolean verificarMaioridade(Integer idade)`
  - Deve retornar `true` se a idade passada como argumento for maior ou igual a 18, e `false` caso contrário.


- `Double calcularMedia(Double valor1, Double valor2, Double valor3)`
  - Deve retornar a média dos valores passados como argumento.


- `Integer maiorNumero(Integer valor1, Integer valor2, Integer valor3)`
  - Deve retornar o maior número entre os valores passados como argumento.


- `Integer calcularFatorial(Integer valor)`
  - Deve retornar o fatorial do valor passado como argumento.
  - O fatorial de um número é o produto de todos os números inteiros positivos de 1 até o próprio número.
  - Exemplo: 5! = 5 * 4 * 3 * 2 * 1 = 120


- `Boolean verificarPrimo(Integer valor)`
  - Deve retornar `true` se o valor passado como argumento for um número primo, e `false` caso contrário.
  - Um número primo é um número natural maior que 1 que possui apenas dois divisores: 1 e ele mesmo.


- `Integer calcularPotencia(Integer base, Integer expoente)`
  - Deve retornar o resultado da potência do número base elevado ao expoente passado como argumento.
  - Exemplo: 2^3 = 2 * 2 * 2 = 8
  - Utilize um laço de repetição para calcular a potência.


- `Integer calcularTrocoEmBalas(Double valorCompra, Double valorRecebido)`
  - Deve retornar a quantidade de balas que será possível comprar com o troco da compra.
  - Considere que cada bala custa R$ 0,25.
  - Exemplo: Se a compra foi de R\$ 10,00 e o valor recebido foi de R\$ 20,00, o troco será de R\$ 10,00. Com esse troco é possível comprar 40 balas.
  - Caso o valor recebido seja menor que o valor da compra, retorne 0.
  - **Dica:** Utilize um laço de repetição

### Desafio - Verificar Palíndromo

- `Boolean verificarPalindromo(String palavra)`
  - Deve retornar `true` se a palavra passada como argumento for um palíndromo, e `false` caso contrário.
  - Um palíndromo é uma palavra, frase ou número que se lê da mesma forma de trás para frente.
  - Exemplo: "ovo", "arara", "12321", "Ame o poema"
  - Dica: Métodos úteis da classe String:
    - `replaceAll` retorna o texto substituindo um caractere de sua escolha por outro.
    - `toLowerCase` retorna o mesmo texto com letras minúsculas.
    - `charAt` obtém um caractere do texto.
    - `length` obtém o tamanho do texto.
