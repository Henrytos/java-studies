package school.sptech.fixture;

import java.util.List;
import school.sptech.VendasCidade;
import school.sptech.factory.VendasCidadeFactory;

public class VendasCidadeFixture {

    public static List<VendasCidade> getVendasCidade() {
        return List.of(
              VendasCidadeFactory.build("são paulo", 2485.6132),
              VendasCidadeFactory.build("rio de janeiro", 654.98),
              VendasCidadeFactory.build("belo horizonte", 6887.52)
        );
    }


    public static List<VendasCidade> getVendasCidade2() {
        return List.of(
              VendasCidadeFactory.build("são paulo", 1248.684),
              VendasCidadeFactory.build("rio de janeiro", 483.68),
              VendasCidadeFactory.build("curitiba", 10876.5),
              VendasCidadeFactory.build("porto alegre", 4396.0)
        );
    }
}
