package ecomerce.dados.test.ValidationTest;

import ecomerce.dados.test.EntityManagerTest;
import ecommerce.model.Pedido;
import jakarta.persistence.EntityGraph;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;

public class ProblemaN1Test extends EntityManagerTest {

    @Test
    public void resolver_Com_EntityGraph() {
        EntityGraph<Pedido> entityGraph = entityManager.createEntityGraph(Pedido.class);
        entityGraph.addAttributeNodes("cliente", "notaFiscal", "pagamento");

        List<Pedido> lista = entityManager
                .createQuery("select p from Pedido p ", Pedido.class)
                .setHint("javax.persistence.loadgraph", entityGraph)
                .getResultList();

        Assertions.assertFalse(lista.isEmpty());
    }

    @Test
    public void resolver_Com_Fetch() {
        List<Pedido> lista = entityManager
                .createQuery("select p from Pedido p " +
                        " join fetch p.cliente c " +
                        " join fetch p.pagamento pag " +
                        " join fetch p.notaFiscal nf", Pedido.class)
                .getResultList();

        Assertions.assertFalse(lista.isEmpty());
    }
}
