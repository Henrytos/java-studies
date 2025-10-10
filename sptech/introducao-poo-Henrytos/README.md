[![Review Assignment Due Date](https://classroom.github.com/assets/deadline-readme-button-22041afd0340ce965d47ae6ef1cefeee28c7c493a6346c4f15d667ab976d596c.svg)](https://classroom.github.com/a/o_GvuYu-)
# Lista 5 - Introdução a POO 📎

## Orientações Gerais: 🚨
1. Utilize **apenas** tipos **wrapper** para criar atributos e métodos.
2. **Respeite** os nomes de atributos e métodos definidos no exercício.
3. Tome **cuidado** com os **argumentos** especificados no exercício. 
**Não** adicione argumentos não solicitados e mantenha a ordem definida no enunciado.
4. Verifique se **não** há **erros de compilação** no projeto antes de enviar.

## Dicas: 💡
* Tome cuidado ao dividir valores no Java. Dividir valores inteiros sempre resulta em uma divisão inteira.
  ```java
  // Divisão inteira de dois inteiros
  Double valor = salario * 10 / 100;
  // > Resultado: 10 / 100 = 0
  
  // Divisão de um inteiro e um double
  Double valor2 = salario * 10 / 100.0;
  // > Resultado: 10 / 100.0 = 0.1
  ```

## Exercício 1 - Confeitaria 🚩

A confeitaria da Dona Maria, precisa de um sistema para gestão dos bolos vendidos em sua loja, que
possibilite a geração de um relatório de vendas para cada tipo de bolo, para implementação do sistema em
Java com OO (orientação a objetos), crie:

A classe `Bolo` precisa conter os seguintes atributos e métodos:

**Atributos:**
* sabor (Texto) (ex: chocolate, morango ou abacaxi)
* valor (Número real) (ex: 30.00 e 50.00)
* quantidadeVendida (Número inteiro) (ex: 10)
* quantidadeEmEstoque (Número inteiro) (ex: 100)

**Métodos:**
* venderBolo: 
  * **recebe** um número inteiro que é a quantidade desejada do bolo.
  * **atualiza** o atributo quantidadeVendida com a nova quantidade.
  * **atualiza** o atributo quantidadeEmEstoque com a nova quantidade após a venda do bolo.
  * caso a quantidade solicitada seja um valor negativo **não faça nada**.
  * caso a quantidade solicitada ultrapasse o limite disponível **não faça nada**.
  

* aumentarEstoque:
  * **recebe** um número inteiro que é a quantidade a ser adicionada ao estoque.
  * **atualiza** o atributo quantidadeEmEstoque com a nova quantidade.
  * caso a quantidade informada seja um valor negativo **não faça nada**.


* quantidadeDisponivel:
  * **retorna** quantas unidades desse bolo estão disponíveis para venda.


* totalVendido:
  * **retorna** a quantidade em dinheiro de bolos vendidos.

Utilize o método `main` na classe `TesteConfeitaria` para realizar os testes necessários.

## Exercício 2 - Encomendas 🚩

Crie um programa para gerenciar as encomendas de uma transportadora, que gere etiquetas para
envio, e calcule o frete.

A classe `Encomenda` precisa conter os seguintes atributos e métodos:

**Atributos:**
* tamanho (Texto) (ex: P, M, G)
* enderecoRemetente (Texto) (Ex: Rua Santos da Glória, 18)
* enderecoDestinatario (Texto) (Ex: Av Dr. Pedro, 255)
* distancia (Número real) (ex: 42.2)
* valorProduto (Número real) (ex: 87.50)

**Métodos:**

* calcularFrete:
  * **retorna** o valor do frete do produto levando em conta o **tamanho** e **distância** definida.
  * use a seguinte tabela para o calculo:

    | Tamanho | Valor adicionado 💸                |
    |---------|------------------------------------|
    | **P**   | 1% do valor da encomenda           |
    | **M**   | 3% do valor da encomenda           |
    | **G**   | 5% do valor da encomenda           |

    | Distância              | Valor adicionado 💸 |
    |------------------------|---------------------|
    | **Até 50km**           | R$ 3,00             |
    | **Entre 50km e 200km** | R$ 5,00             |
    | **Acima de 200km**     | R$ 7,00             |




* aplicarCupomDeDesconto:
  * **recebe** o percentual de desconto a ser aplicado no valor do produto ex: 20, 15... 
  **(número inteiro)**
  * **altera** o valor do produto para o novo valor com desconto.


* valorTotalDaEncomenda:
  * **retorna** o valor total da encomenda (valor do produto + frete)

Utilize o método `main` na classe `TesteEncomendas` para realizar os testes necessários.

## Exercício 3 - Empresa 🚩

Crie um programa para gerenciar os funcionários de uma empresa.

A classe `Funcionario` precisa conter os seguintes atributos e métodos:

**Atributos:**
* nome (Texto) (ex: William)
* cargo (Texto) (Ex: Analista Desenvolvedor)
* salario (Número real) (Ex: 8000.0)

**Métodos:**

* reajustarSalario:
  * **recebe** o valor percentual do reajuste (ex: 10, 20) (número inteiro)
  * **atualiza** o atributo `salario` com o novo valor reajustado.


* calcularValorHora:
  * **retorna** o valor da hora trabalhada por mês dado o `salario` do `Funcionario`.
  * considere que cada funcionario trabalha 220 horas por mês.


* calcularHoraExtra:
  * **recebe** a quantidade de horas trabalhadas fora do horário convencional de trabalho (valor inteiro)
  * **recebe** o percentual de adicional noturno a ser recebido (valor inteiro).
  * **retorna** o valor a ser recebido em horas extras dado (**salário**, **horas extras trabalhadas** e **adicional noturno**)

Utilize o método `main` na classe `TesteEmpresa` para realizar os testes necessários.


