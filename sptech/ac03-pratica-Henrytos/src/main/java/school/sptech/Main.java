package school.sptech;

public class Main {

  /**
   * <h1>Code snippets</h1>
   *
   * O código abaixo mostra como iterar uma lista com for tradicional
   * <blockquote><pre>
   * for (int i = 0; i < filmes.size(); i++) {
   *     Filme filme = filmes.get(i);
   *     System.out.println(filme);
   * }
   * </pre></blockquote>
   *
   * O código abaixo mostra como iterar uma lista com enhanced for
   * <blockquote><pre>
   * for (Filme filme : filmes) {
   *     System.out.println(filme);
   * }
   * </pre></blockquote>
   *
   * O código abaixo mostra como utilizar o método {@code isBlank()}
   * para validar {@code " "} ou {@code ""}
   *
   * <blockquote><pre>
   * if (nome.isBlank()) {
   *     System.out.println("Está vazio");
   * } else {
   *     System.out.println("Não está vazio");
   * }
   * </pre></blockquote>
   *
   * O código abaixo mostra como fazer o "casting" de objetos dentro de uma lista
   *
   * <blockquote><pre>
   * List<Animal> animais = new ArrayList<>();
   * animais.add(new Cachorro("Caramelo"));
   * animais.add(new Gato("Marrom"));
   * animais.add(new Galinha("Gizele"));
   *
   * // Obtém o animal na primeira posição valida o tipo e realiza o "casting"
   * Animal animal = animais.get(0);
   *
   * if (animal instanceof Cachorro) {
   *     Cachorro cachorro = (Cachorro) animal;
   *     cachorro.latir();
   * }
   *
   * // Obtém o animal na segunda posição valida o tipo e faz o casting com pattern variable
   * animal = animais.get(1);
   *
   * if (animal instanceof Cachorro cachorro) {
   *     cachorro.latir();
   * } else if (animal instanceof Gato gato) {
   *     gato.latir();
   * }
   * </pre></blockquote>
   *
   * // O código abaixo mostra como converter um Integer para Double
   *
   * <blockquote><pre>
   *   Integer inteiro = 10;
   *   Double decimal = inteiro.doubleValue();
   *   System.out.println(decimal); // 10.0
   * </pre></blockquote>
   *
   */
  public static void main(String[] args) {

  }
}