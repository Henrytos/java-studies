package school.sptech;

import org.apache.commons.lang3.StringUtils;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import school.sptech.utils.ContaCorrenteUtils;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.lang.reflect.Parameter;
import java.util.Arrays;

@DisplayName("Encapsulamento")
public class EncapsulamentoTests {

    @Nested
    @DisplayName("Operacao")
    class OperacaoTests {

        @Nested
        @DisplayName("Todos os atributos de Operacao devem estar privados")
        class TodosAtributosOperacaoPrivadosTests {

            @Test
            @DisplayName("Cenário Único")
            void test1() {

                Class<Operacao> clazz = Operacao.class;
                Field[] fields = clazz.getDeclaredFields();

                for (Field field : fields) {
                    int modifier = field.getModifiers();
                    Assertions.assertTrue(Modifier.isPrivate(modifier));
                }
            }
        }

        @Nested
        @DisplayName("Todos os atributos de Operacao devem possuir getters e setters")
        class TodosAtributosOperacaoDevemPossuirGetterESettersTests {

            @Test
            @DisplayName("Cenário Único")
            void test1() throws NoSuchMethodException {

                Class<Operacao> clazz = Operacao.class;
                Field[] fields = clazz.getDeclaredFields();

                for (Field field : fields) {

                    String getName = String.format("get%s", StringUtils.capitalize(field.getName()));
                    String setName = String.format("set%s", StringUtils.capitalize(field.getName()));

                    Method getter = clazz.getDeclaredMethod(getName);
                    Method setter = clazz.getDeclaredMethod(setName, field.getType());

                    int getModifier = getter.getModifiers();
                    int setModifier = setter.getModifiers();

                    Assertions.assertTrue(Modifier.isPublic(getModifier));
                    Assertions.assertTrue(Modifier.isPublic(setModifier));
                }
            }
        }

        @Nested
        @DisplayName("A classe Operacao deve possuir um construtor que inicialize os atributos")
        class OperacaoConstrutorTests {

            @Test
            @DisplayName("Cenário Único")
            void test1() throws NoSuchMethodException {

                Class<Operacao> clazz = Operacao.class;
                Constructor<?>[] constructors = clazz.getDeclaredConstructors();

                Assertions.assertTrue(constructors.length > 0);

                boolean hasFullConstructor = Arrays.stream(constructors)
                    .anyMatch(constructor -> {

                        Parameter[] parameters = constructor.getParameters();

                        long qtdString = Arrays.stream(parameters)
                            .filter(parameter -> parameter.getType().equals(String.class))
                            .count();

                        long qtdDouble = Arrays.stream(parameters)
                            .filter(parameter -> parameter.getType().equals(Double.class))
                            .count();

                        return qtdString >= 2 && qtdDouble >= 1;
                    });

                Assertions.assertTrue(hasFullConstructor);
            }
        }
    }

    @Nested
    @DisplayName("ContaCorrente")
    class ContaCorrenteTests {

        @Nested
        @DisplayName("Todos os atributos de ContaCorrente devem estar privados")
        class TodosAtributosContaCorrentePrivadosTests {

            @Test
            @DisplayName("Cenário Único")
            void test1() {

                Class<ContaCorrente> clazz = ContaCorrente.class;
                Field[] fields = clazz.getDeclaredFields();

                for (Field field : fields) {
                    int modifier = field.getModifiers();
                    Assertions.assertTrue(Modifier.isPrivate(modifier));
                }
            }
        }

        @Nested
        @DisplayName("Todos os atributos de ContaCorrente devem possuir getters e setters excepto o atributo operacoes")
        class TodosAtributosContaCorrenteDevemPossuirGetterESettersTests {

            @Test
            @DisplayName("Cenário Único")
            void test1() throws NoSuchMethodException {

                Class<ContaCorrente> clazz = ContaCorrente.class;
                Field[] fields = clazz.getDeclaredFields();

                Field[] fieldsFiltered = Arrays.stream(fields)
                        .filter(field -> !field.getName().equals(ContaCorrenteUtils.ATRIBUTO_OPERACOES))
                        .toArray(Field[]::new);

                for (Field field : fieldsFiltered) {

                    String getName = String.format("get%s", StringUtils.capitalize(field.getName()));
                    String setName = String.format("set%s", StringUtils.capitalize(field.getName()));

                    Method getter = clazz.getDeclaredMethod(getName);
                    Method setter = clazz.getDeclaredMethod(setName, field.getType());

                    int getModifier = getter.getModifiers();
                    int setModifier = setter.getModifiers();

                    Assertions.assertTrue(Modifier.isPublic(getModifier));
                    Assertions.assertTrue(Modifier.isPublic(setModifier));
                }
            }
        }

        @Nested
        @DisplayName("A classe ContaCorrente deve possuir um construtor que inicialize os atributos")
        class ContaCorrenteConstrutorTests {

            @Test
            @DisplayName("Cenário Único")
            void test1() {

                Class<ContaCorrente> clazz = ContaCorrente.class;
                Constructor<?>[] constructors = clazz.getDeclaredConstructors();

                Assertions.assertTrue(constructors.length > 0);

                boolean hasFullConstructor = Arrays.stream(constructors)
                        .anyMatch(constructor -> {

                            Parameter[] parameters = constructor.getParameters();

                            long qtdString = Arrays.stream(parameters)
                                    .filter(parameter -> parameter.getType().equals(String.class))
                                    .count();

                            return qtdString >= 3;
                        });

                Assertions.assertTrue(hasFullConstructor);
            }
        }

        @Nested
        @DisplayName("Atributo operacoes não deve conter getters e setters")
        class AtributoOperacoesNaoDeveConterGettersESettersTests {

            @Test
            @DisplayName("Cenário Único")
            void test1() throws NoSuchFieldException, NoSuchMethodException {

                Class<ContaCorrente> clazz = ContaCorrente.class;
                Field field = clazz.getDeclaredField(ContaCorrenteUtils.ATRIBUTO_OPERACOES);

                String getName = String.format("get%s", StringUtils.capitalize(field.getName()));
                String setName = String.format("set%s", StringUtils.capitalize(field.getName()));

                Assertions.assertThrows(NoSuchMethodException.class, () -> {
                    clazz.getDeclaredMethod(getName);
                });

                Assertions.assertThrows(NoSuchMethodException.class, () -> {
                    clazz.getDeclaredMethod(setName, field.getType());
                });
            }
        }

        @Nested
        @DisplayName("A lista de operacaoes deve ser inicializada no momento da criação do objeto")
        class AtributoOperacoesDeveSerInicializadoNaCriacaoDoObjetoTests {

            @Test
            @DisplayName("Cenário Único")
            void test1() throws NoSuchFieldException, InstantiationException, IllegalAccessException, InvocationTargetException {

                Class<ContaCorrente> clazz = ContaCorrente.class;

                Field field = clazz.getDeclaredField(ContaCorrenteUtils.ATRIBUTO_OPERACOES);

                Constructor<?> constructor = clazz.getConstructors()[0];

                Object[] args = new Object[constructor.getParameterCount()];

                Object instance = constructor.newInstance(args);

                field.setAccessible(true);
                Object value = field.get(instance);

                Assertions.assertNotNull(value);
            }
        }
    }
}
