package ecomerce.dados.test.JPQLbasicoTest;

import ecomerce.dados.test.EntityManagerTest;
import ecommerce.model.Pedido;
import ecommerce.model.Produto;
import jakarta.persistence.TypedQuery;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;

public class NamedQueryTest extends EntityManagerTest {


    @Test
    public void consultar_XML() {

        TypedQuery<Pedido> typedQuery = entityManager.createNamedQuery("Pedido.listar", Pedido.class);
        List<Pedido> lista = typedQuery.getResultList();

        Assertions.assertFalse(lista.isEmpty());
    }


    @Test
    public void consultar() {

        TypedQuery<Produto> typedQuery = entityManager.createNamedQuery("Produto.listarPorCategoria",
                Produto.class);
        typedQuery.setParameter("categoria", 2);

        List<Produto> lista = typedQuery.getResultList();
        Assertions.assertFalse(lista.isEmpty());

    }

}
