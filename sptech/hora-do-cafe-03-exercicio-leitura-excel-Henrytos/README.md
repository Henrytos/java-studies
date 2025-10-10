# Hora do Café - 003 ☕ - Análise de Excel com Apache POI 📊

> "Sem dados, você é apenas mais uma pessoa com uma opinião." <br>
> *W. Edwards Deming*

## Orientações Gerais: 🚨

1. Utilize **apenas** as bibliotecas **Apache POI** e classes da **API padrão do Java**.
2. Utilize **apenas** tipos **wrapper** para criar variáveis quando necessário.
3. Verifique se **não** há **erros de compilação** no projeto antes de enviar.
4. Respeite os nomes de atributos e métodos definidos no exercício.
5. Tome cuidado com os argumentos especificados no exercício. Não adicione argumentos não
   solicitados e mantenha a ordem definida no enunciado.
6. Crie um construtor vazio para os testes.
7. Sobrescreva o método toString para ajudar na visualização dos testes.

---

### 🎷 Exercício 1 - Ler músicas

`List<Musica> lerMusicas(String caminhoArquivo)`

* Excel de referência: **`musicas.xlsx`** e **`musicas2.xlsx`**
* O método deve:

    * Ler o Excel.
    * Retornar uma lista de músicas presentes no arquivo.
    * A classe `Musica` representa uma música e deve conter:

        * id (Integer) — identificador único da música
        * nome (String) — nome da música
        * artista (String) — nome do artista
        * album (String) — nome do álbum
        * dataLancamento (LocalDate) — data de lançamento da música
    * Lembre de deixar um construtor vazio para testes.
    * Crie o método toString() para ajudar na análise do código.

---

### 🤑 Exercício 2 - Produto mais caro

`Produto produtoMaisCaro(String caminhoArquivo)`

* Excel de referência: **`produtos.xlsx`** e **`produtos2.xlsx`**
* O método deve:

    * Ler o Excel.
    * Retornar o **produto com maior valor**.
    * A classe `Produto` representa um item à venda e deve conter:

        * id (Integer) — identificador do produto
        * nome (String) — nome do produto
        * valor (Double) — preço do produto
        * marca (String) — marca do produto
        * dataLancamento (LocalDate) — data de lançamento do produto
    * Lembre de deixar um construtor vazio para testes.
    * Crie o método toString() para ajudar na análise do código.

---

### 💰 Exercício 3 - Faturamento total

`Double faturamentoTotal(String caminhoArquivo)`

* Excel de referência: **`vendas.xlsx`** ou  **`vendas2.xlsx`**
* O método deve:

    * Ler o Excel.
    * Ignorar linhas com valores inválidos (`quantidadeVendida` ou `precoUnitario` vazio ou igual a
      0).
    * Retornar o **faturamento total** das vendas.
    * O faturamento é calculado como:

      ```
      quantidadeVendida × precoUnitario × (1 − desconto/100)
      ```

##### Exemplo 💡

| quantidadeVendida | precoUnitario | % descontoAplicado | cálculo linha         | valor linha |
|-------------------|---------------|--------------------|-----------------------|-------------|
| 2                 | 120.0         | 20.0               | 2 × 120 × (1 − 0.20)  | 192.0       |
| 3                 | 150.0         | 10.0               | 3 × 150 × (1 − 0.10)  | 405.0       |
| 1                 | 3500.0        | 20.0               | 1 × 3500 × (1 − 0.20) | 2800.0      |

##### Retorno Esperado ✅️️

- 192.0 + 405.0 + 2800.0 = **3397.0**

---

🚀 EXTRA – DESAFIO OPCIONAL 

---

### 💪🔥 DESAFIO (nível hard)  - Faturamento por cidade 🏆

`List<VendasCidade> faturamentoPorCidade(String caminhoArquivo)`

* Excel de referência: **`vendas.xlsx`** ou  **`vendas2.xlsx`**
* O método deve:

    * Ler o Excel.
    * Ignorar linhas com valores inválidos (`quantidadeVendida` ou `precoUnitario` vazio ou igual a
      0).
    * Agrupar as vendas por cidade, retornando uma lista de objetos `VendasCidade`. A agregação deve
      ignorar diferenças de **case** (maiúsculas/minúsculas) entre os nomes da cidade.
    * No objeto `VendasCidade`, o nome da cidade deve sempre aparecer em **lowerCase**.
    * A classe `VendasCidade` representa o faturamento total de uma cidade e deve conter:

        * cidade (String) — nome da cidade em lowercase
        * faturamentoTotal (Double) — soma do faturamento das vendas da cidade
        * Lembre de deixar um construtor vazio para testes.
        * Crie o método toString() para ajudar na análise do código.

##### Exemplo 💡

| cidadeEntrega  | quantidadeVendida | precoUnitario | % descontoAplicado |
|----------------|-------------------|---------------|--------------------|
| São Paulo      | 2                 | 120.00        | 20.0               |
| Rio de Janeiro | 5                 | 6.50          |                    |
| Belo Horizonte | 1                 | 3500.00       | 20.0               |
| são Paulo      | 3                 | 150.00        | 10.0               |

---

##### Retorno Esperado ✅️

| cidade         | faturamentoTotal |
|----------------|------------------|
| são paulo      | 597.0            |
| rio de janeiro | 32.5             |
| belo horizonte | 2800.0           |
