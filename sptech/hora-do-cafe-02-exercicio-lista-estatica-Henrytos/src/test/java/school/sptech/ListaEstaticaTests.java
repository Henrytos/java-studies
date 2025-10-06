package school.sptech;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.Arrays;
import java.util.Objects;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ArgumentsSource;
import school.sptech.providers.AdicionarProvider;
import school.sptech.providers.BuscaProvider;
import school.sptech.providers.RemoveElementoProvider;
import school.sptech.providers.RemoveIndiceProvider;
import school.sptech.providers.ToStringProvider;

@DisplayName("Exercício - Lista Estática")
class ListaEstaticaTests {

    private void setListaEstaticaState(ListaEstatica obj, Integer[] elementos)
          throws ReflectiveOperationException {
        var clazz = ListaEstatica.class;
        var vetorField = clazz.getDeclaredField("vetor");
        vetorField.trySetAccessible();
        var nroElemField = clazz.getDeclaredField("nroElem");
        nroElemField.trySetAccessible();

        vetorField.set(obj, elementos);
        nroElemField.set(obj, (int) Arrays.stream(elementos).filter(Objects::nonNull).count());
    }

    private String buildArrayMessage(Integer[] expected, Integer[] current) {
        return "Falha no array. Esperado: " + Arrays.toString(expected) +
              ", Atual: " + Arrays.toString(current);
    }

    @Nested
    @DisplayName("Método - construtor")
    class MetodoConstrutor {

        @Test
        @DisplayName("Deve inicializar vetor com a capacidade informada")
        void testConstrutorCapacidade() throws ReflectiveOperationException {
            Class<ListaEstatica> clazz = ListaEstatica.class;
            var constructor = clazz.getDeclaredConstructor(int.class);
            constructor.trySetAccessible();

            Object obj = constructor.newInstance(5);
            var vetorField = clazz.getDeclaredField("vetor");
            vetorField.trySetAccessible();
            var nroElemField = clazz.getDeclaredField("nroElem");
            nroElemField.trySetAccessible();

            Integer[] vetor = (Integer[]) vetorField.get(obj);

            assertNotNull(vetor);
            assertEquals(5, vetor.length, "vetor deve ser inicializado com a capacidade informada");
            assertEquals(0, (int) nroElemField.get(obj), "nrmElem deve ser atualizado com sucesso");
        }
    }

    @Nested
    @DisplayName("Método - adicionar")
    class MetodoAdicionar {

        @ParameterizedTest
        @DisplayName("Deve adicionar elementos corretamente")
        @ArgumentsSource(AdicionarProvider.class)
        void testAdicionar(Integer[] elementosAtuais, int capacidade, Integer elemento,
              Integer[] expected, int nroElemExpected)
              throws ReflectiveOperationException {

            var clazz = ListaEstatica.class;
            var method = clazz.getDeclaredMethod("adicionar", Integer.class);
            method.trySetAccessible();
            var nroElemField = clazz.getDeclaredField("nroElem");
            nroElemField.trySetAccessible();

            ListaEstatica obj = clazz.getDeclaredConstructor(int.class).newInstance(capacidade);
            setListaEstaticaState(obj, elementosAtuais);

            method.invoke(obj, elemento);

            var vetorField = clazz.getDeclaredField("vetor");
            vetorField.trySetAccessible();

            assertArrayEquals(expected, (Integer[]) vetorField.get(obj),
                  buildArrayMessage(expected, (Integer[]) vetorField.get(obj)));
            assertEquals(nroElemExpected, nroElemField.get(obj), "nrmElem deve ser atualizado com sucesso");
        }
    }


    @Nested
    @DisplayName("Método - busca")
    class MetodoBusca {

        @ParameterizedTest
        @DisplayName("Deve buscar elementos corretamente")
        @ArgumentsSource(BuscaProvider.class)
        void testBusca(Integer[] elementos, int capacidade, Integer elemento, int expected)
              throws ReflectiveOperationException {

            Class<ListaEstatica> clazz = ListaEstatica.class;
            var method = clazz.getDeclaredMethod("busca", Integer.class);
            method.trySetAccessible();

            ListaEstatica obj = clazz.getDeclaredConstructor(int.class).newInstance(capacidade);
            setListaEstaticaState(obj, elementos);

            int result = (int) method.invoke(obj, elemento);
            assertEquals(expected, result, "Deve retornar o indice corretamente");
        }
    }

    @Nested
    @DisplayName("Método - removePeloIndice")
    class MetodoRemovePeloIndice {

        @ParameterizedTest
        @DisplayName("Deve remover pelo índice corretamente")
        @ArgumentsSource(RemoveIndiceProvider.class)
        void testRemovePeloIndice(Integer[] elementos, int capacidade, int indice,
              Integer[] expected, boolean expectedResult, int nroElemExpected)
              throws ReflectiveOperationException {

            Class<ListaEstatica> clazz = ListaEstatica.class;
            var method = clazz.getDeclaredMethod("removePeloIndice", int.class);
            method.trySetAccessible();
            var nroElemField = clazz.getDeclaredField("nroElem");
            nroElemField.trySetAccessible();

            ListaEstatica obj = clazz.getDeclaredConstructor(int.class).newInstance(capacidade);
            setListaEstaticaState(obj, elementos);

            boolean result = (boolean) method.invoke(obj, indice);

            var vetorField = clazz.getDeclaredField("vetor");
            vetorField.trySetAccessible();

            assertEquals(expectedResult, result, "Deve retornar se removeu com sucesso");
            assertArrayEquals(expected, (Integer[]) vetorField.get(obj),
                  buildArrayMessage(expected, (Integer[]) vetorField.get(obj)));
            assertEquals(nroElemExpected, (int) nroElemField.get(obj),
                  "nrmElem deve ser atualizado com sucesso");
        }
    }

    @Nested
    @DisplayName("Método - removeElemento")
    class MetodoRemoveElemento {

        @ParameterizedTest
        @DisplayName("Deve remover elemento corretamente")
        @ArgumentsSource(RemoveElementoProvider.class)
        void testRemoveElemento(Integer[] elementos, int capacidade, Integer elemento,
              Integer[] expected, boolean expectedResult, int nroElemExpected)
              throws ReflectiveOperationException {

            Class<ListaEstatica> clazz = ListaEstatica.class;
            var method = clazz.getDeclaredMethod("removeElemento", Integer.class);
            method.trySetAccessible();
            var nroElemField = clazz.getDeclaredField("nroElem");
            nroElemField.trySetAccessible();

            ListaEstatica obj = clazz.getDeclaredConstructor(int.class).newInstance(capacidade);
            setListaEstaticaState(obj, elementos);

            boolean result = (boolean) method.invoke(obj, elemento);

            var vetorField = clazz.getDeclaredField("vetor");
            vetorField.trySetAccessible();

            assertEquals(expectedResult, result,"Deve retornar se removeu com sucesso");
            assertArrayEquals(expected, (Integer[]) vetorField.get(obj),
                  buildArrayMessage(expected, (Integer[]) vetorField.get(obj)));
            assertEquals(nroElemExpected, (int) nroElemField.get(obj),"nrmElem deve ser atualizado com sucesso");
        }
    }

    @Nested
    @DisplayName("Método - toString")
    class MetodoToString {

        @ParameterizedTest
        @DisplayName("Deve converter lista para string corretamente")
        @ArgumentsSource(ToStringProvider.class)
        void testToString(Integer[] elementos, int capacidade, String expected)
              throws ReflectiveOperationException {

            Class<ListaEstatica> clazz = ListaEstatica.class;
            var method = clazz.getDeclaredMethod("toString");
            method.trySetAccessible();

            ListaEstatica obj = clazz.getDeclaredConstructor(int.class).newInstance(capacidade);
            setListaEstaticaState(obj, elementos);

            String result = (String) method.invoke(obj);
            assertEquals(expected, result);
        }
    }
}
