package school.sptech;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.Arrays;
import java.util.stream.Stream;
import org.apache.commons.lang3.StringUtils;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;

@DisplayName("VendasCidade")
public class VendasCidadeTest {

    @Nested
    @DisplayName("1. Atributos")
    class AtributosTest {


        @Test
        @DisplayName("1. Validar Atributos")
        void test1() {
            Class<VendasCidade> clazz = VendasCidade.class;

            assertAll(
                  () -> assertDoesNotThrow(() -> clazz.getDeclaredField("cidade"),
                        "Deve possuir o atributo cidade"),
                  () -> assertDoesNotThrow(() -> clazz.getDeclaredField("faturamentoTotal"),
                        "Deve possuir o atributo faturamentoTotal")
            );
        }
    }

    @Nested
    @DisplayName("2. Encapsulamento")
    class EncapsulamentoTest {

        @Test
        @DisplayName("1. Atributos Privados")
        void test1() {
            Class<VendasCidade> clazz = VendasCidade.class;
            Field[] campos = clazz.getDeclaredFields();

            Stream<Executable> validacoes = Arrays.stream(campos)
                  .map((campo) -> () -> assertTrue(Modifier.isPrivate(campo.getModifiers()),
                        String.format("%s deve ser privado", campo.getName())));

            assertAll(validacoes);
        }

        @Test
        @DisplayName("2. Atributos devem possuir getters e setters")
        void test3() {
            Class<VendasCidade> clazz = VendasCidade.class;
            Field[] campos = clazz.getDeclaredFields();

            Stream<Executable> validacoesGetter = Arrays.stream(campos)
                  .map((campo) -> () -> {
                      String getName = String.format("get%s",
                            StringUtils.capitalize(campo.getName()));
                      assertDoesNotThrow(() -> {
                          Method getter = clazz.getDeclaredMethod(getName);
                          int getModifier = getter.getModifiers();
                          assertTrue(Modifier.isPublic(getModifier),
                                String.format("Getter %s deve ser público", getName));
                      }, String.format("Deve possuir o getter %s", getName));
                  });

            Stream<Executable> validacoesSetter = Arrays.stream(campos)
                  .map((campo) -> () -> {
                      String setName = String.format("set%s",
                            StringUtils.capitalize(campo.getName()));
                      assertDoesNotThrow(() -> {
                          Method setter = clazz.getDeclaredMethod(setName, campo.getType());
                          int setModifier = setter.getModifiers();
                          assertTrue(Modifier.isPublic(setModifier),
                                String.format("Setter %s deve ser público", setName));
                      }, String.format("Deve possuir o setter %s", setName));
                  });

            assertAll(Stream.concat(validacoesGetter, validacoesSetter));
        }
    }
}
