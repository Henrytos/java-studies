package school.sptech.util;

import net.bytebuddy.ByteBuddy;
import net.bytebuddy.dynamic.DynamicType;
import net.bytebuddy.dynamic.loading.ClassLoadingStrategy;
import net.bytebuddy.implementation.MethodDelegation;
import org.objenesis.Objenesis;
import org.objenesis.ObjenesisStd;

import java.lang.reflect.*;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class ObjectFieldBuilder <T> {

  private final ObjectFieldMapping<T> mapping;
  private final Map<String, Object> fieldValues;
  private final Map<String, Object> abstractMethodDelegations;
  private final Class<T> clazz;

  public ObjectFieldBuilder(Class<T> clazz) {
    this.clazz = clazz;
    this.fieldValues = new HashMap<>();
    this.abstractMethodDelegations = new HashMap<>();
    this.mapping = new ObjectFieldMapping<>(clazz);
  }

  public ObjectFieldBuilder<T> with(String fieldName, Object value) {
    fieldValues.put(fieldName, value);
    return this;
  }

  public ObjectFieldBuilder<T> withEnum(String fieldName, String enumValue) throws ReflectiveOperationException {
    Field field = mapping.get(fieldName);
    Method valueOfMethod = field.getType().getMethod("valueOf", String.class);

    Object enumConstant = valueOfMethod.invoke(null, enumValue);

    fieldValues.put(fieldName, enumConstant);
    return this;
  }

  public ObjectFieldBuilder<T> withAbstractMethod(String methodName, Object delegation) {
    abstractMethodDelegations.put(methodName, delegation);
    return this;
  }

  public T build() throws ReflectiveOperationException {
    if (clazz.isRecord()) {
      RecordComponent[] recordComponents = clazz.getRecordComponents();

      Class<?>[] componentTypes = Arrays.stream(recordComponents)
          .map(RecordComponent::getType)
          .toArray(Class<?>[]::new);

      Constructor<T> constructor = clazz.getDeclaredConstructor(componentTypes);

      Object[] initargs = Arrays.stream(recordComponents)
          .map(RecordComponent::getName)
          .map(name -> fieldValues.getOrDefault(name, null))
          .toArray();

      return constructor.newInstance(initargs);
    }

    if (Modifier.isAbstract(clazz.getModifiers())) {
      DynamicType.Builder<? extends T> builder = new ByteBuddy().subclass(clazz);

      for (Map.Entry<String, Object> entry : abstractMethodDelegations.entrySet()) {
        String methodName = entry.getKey();
        Object delegation = entry.getValue();

        Method methodToImplement = Arrays.stream(clazz.getDeclaredMethods())
            .filter(m -> m.getName().equals(methodName))
            .findFirst()
            .orElseThrow(() -> new NoSuchMethodException(
                String.format("Método %s não encontrado na classe %s", methodName, clazz.getName())
            ));

        builder = builder.defineMethod(methodName, methodToImplement.getReturnType(), Modifier.PUBLIC)
            .withParameters(methodToImplement.getParameterTypes())
            .intercept(MethodDelegation.to((Class<?>) delegation));
      }

      Class<? extends T> dynamicType = builder
          .make()
          .load(clazz.getClassLoader(), ClassLoadingStrategy.Default.INJECTION)
          .getLoaded();

      return createObjectFromClass(dynamicType);
    }

    return createObjectFromClass(this.clazz);
  }

  private T createObjectFromClass(Class<? extends T> clazz) throws IllegalAccessException, NoSuchFieldException {
    Objenesis objenesis = new ObjenesisStd();
    T obj = objenesis.newInstance(clazz);

    for (Map.Entry<String, Object> entry : fieldValues.entrySet()) {
      mapping.get(entry.getKey()).set(obj, entry.getValue());
    }

    return obj;
  }
}
