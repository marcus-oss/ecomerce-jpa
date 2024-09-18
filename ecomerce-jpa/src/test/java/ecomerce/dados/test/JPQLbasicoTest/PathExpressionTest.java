package ecomerce.dados.test.JPQLbasicoTest;

import ecomerce.dados.test.EntityManagerTest;
import ecommerce.model.Pedido;
import jakarta.persistence.TypedQuery;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;

public class PathExpressionTest extends EntityManagerTest {


    @Test
    public void buscar_Pedidos_Com_Produto_Especifico() {

        String jpql = "select p from Pedido p join p.itens i where i.produto.id = 1";

        TypedQuery<Pedido> typedQuery = entityManager.createQuery(jpql, Pedido.class);
        List<Pedido> lista = typedQuery.getResultList();


        Assertions.assertTrue(lista.size() == 2);
    }

    @Test
    public void Path_Expression() {
        String jpql = "select  p.cliente.nome from Pedido p";

        TypedQuery<Object[]> typedQuery = entityManager.createQuery(jpql, Object[].class);
        List<Object[]> lista = typedQuery.getResultList();

        Assertions.assertFalse(lista.isEmpty());
    }


}
