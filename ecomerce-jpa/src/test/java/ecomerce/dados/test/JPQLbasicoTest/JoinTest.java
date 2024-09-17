package ecomerce.dados.test.JPQLbasicoTest;

import ecomerce.dados.test.EntityManagerTest;
import jakarta.persistence.TypedQuery;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;

public class JoinTest extends EntityManagerTest {

    @Test

    public void fazer_Join() {
        String jqpl = "select p  from  Pedido p  join p.itens i join i.produto pro  where pro.id = 1 ";

        TypedQuery<Object[]> typedQuery = entityManager.createQuery(jqpl, Object[].class);


        List<Object[]> pedidoList = typedQuery.getResultList();
        Assertions.assertTrue(pedidoList.size() == 2);

    }

}
