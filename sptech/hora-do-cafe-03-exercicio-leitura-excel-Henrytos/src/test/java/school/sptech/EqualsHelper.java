package school.sptech;

import java.lang.reflect.Field;
import java.util.Objects;

public class EqualsHelper {

    public static boolean equals(Object obj1, Object obj2) {
        if (obj1 == obj2) {
            return true;
        }
        if (obj1 == null || obj2 == null) {
            return false;
        }
        if (!obj1.getClass().equals(obj2.getClass())) {
            return false;
        }

        Field[] fields = obj1.getClass().getDeclaredFields();
        for (Field field : fields) {
            field.trySetAccessible();
            try {
                Object value1 = field.get(obj1);
                Object value2 = field.get(obj2);

                if (!Objects.equals(value1, value2)) {
                    return false;
                }
            } catch (IllegalAccessException e) {
                throw new RuntimeException("Unable to access field: " + field.getName(), e);
            }
        }
        return true;
    }
}
