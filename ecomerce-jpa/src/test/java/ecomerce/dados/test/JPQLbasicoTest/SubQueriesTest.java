package ecomerce.dados.test.JPQLbasicoTest;

import ecomerce.dados.test.EntityManagerTest;
import ecommerce.model.Cliente;
import ecommerce.model.Pedido;
import ecommerce.model.Produto;
import jakarta.persistence.TypedQuery;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;

public class SubQueriesTest extends EntityManagerTest {


    @Test
    public void pesquisar_Subqueries() {
        String jpql = "select c from Cliente c where " +
                "100 < (select sum(p.Total) from Pedido p where p.cliente = c)";

        TypedQuery<Cliente> typedQuery = entityManager.createQuery(jpql, Cliente.class);
        List<Cliente> listaObjects = typedQuery.getResultList();

        Assertions.assertFalse(listaObjects.isEmpty());
        listaObjects.forEach(obj -> System.out.println("ID: " + obj.getId() + ", Clientes: " + obj.getNome()));
    }

    @Test
    public void pesquisar_Subqueries_In() {
        String jpql = "select  p from Pedido p where p.id in " +
                "(select p2.id from ItemPedido i2 join i2.pedido p2 " +
                "join i2.produto" +
                " pro2 where pro2.preco > 100)";

        TypedQuery<Pedido> typedQuery = entityManager.createQuery(jpql, Pedido.class);
        List<Pedido> listaObjects = typedQuery.getResultList();

        Assertions.assertFalse(listaObjects.isEmpty());
        listaObjects.forEach(obj -> System.out.println("ID: " + obj.getId()));
    }


    @Test
    public void pesquisar_Subqueries_Exists() {
        String jpql = "select p from Produto p where  exists" +
                " (select 1 from ItemPedido ip2 " +
                " join ip2.produto p2 where p2 = p)";

        TypedQuery<Produto> typedQuery = entityManager.createQuery(jpql, Produto.class);
        List<Produto> listaObjects = typedQuery.getResultList();

        Assertions.assertFalse(listaObjects.isEmpty());
        listaObjects.forEach(obj -> System.out.println("ID: " + obj.getId()));
    }
}
