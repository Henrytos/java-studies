package lambdas;

@FunctionalInterface
public interface OperadorBinario<T> {

    T somar(T elementoUm, T elementoDois);

}
