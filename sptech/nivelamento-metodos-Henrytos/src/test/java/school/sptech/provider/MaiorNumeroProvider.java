package school.sptech.provider;

import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.ArgumentsProvider;

import java.util.stream.Stream;

public class MaiorNumeroProvider implements ArgumentsProvider {

    @Override
    public Stream<? extends Arguments> provideArguments(ExtensionContext extensionContext) throws Exception {
        return Stream.of(
                Arguments.of(5,3,8,8),
                Arguments.of(5,5,3,5),
                Arguments.of(7,7,7,7),
                Arguments.of(-2,-5,-1,-1),
                Arguments.of(2,-3,0,2),
                Arguments.of(456,123,789,789),
                Arguments.of(200,200,150,200),
                Arguments.of(10,11,12,12),
                Arguments.of(1,100000,50,100000)
        );
    }
}
