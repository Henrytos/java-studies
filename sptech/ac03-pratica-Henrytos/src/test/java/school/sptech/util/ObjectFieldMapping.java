package school.sptech.util;

import java.lang.reflect.Field;

public record ObjectFieldMapping<T>(Class<T> clazz) {

  public ObjectFieldMapping {
    assert clazz != null;
  }

  public Field get(String fieldName) throws NoSuchFieldException {

    Class<?> currentClazz = clazz;

    while (currentClazz != null) {
      try {
        Field field = currentClazz.getDeclaredField(fieldName);
        field.setAccessible(true);
        return field;
      } catch (NoSuchFieldException e) {
        currentClazz = currentClazz.getSuperclass();
      }
    }

    assert clazz != null;
    throw new NoSuchFieldException("Field '" + fieldName + "' not found in class hierarchy of " + clazz.getName());
  }
}
