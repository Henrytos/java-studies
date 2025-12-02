package school.sptech.util;

public class EnumConstantMapping {

  public static <E> E getEnumConstant(Class<E> enumClass, String constantName) {
        for (E constant : enumClass.getEnumConstants()) {
            Enum<?> enumConstant = (Enum<?>) constant;
            if (enumConstant.name().equalsIgnoreCase(constantName)) {
                return constant;
            }
        }
        throw new IllegalArgumentException("No enum constant " + enumClass.getCanonicalName() + "." + constantName);
    }
}
