package ecomerce.dados.test.JPQLbasicoTest;

import ecomerce.dados.test.EntityManagerTest;
import ecommerce.model.Pedido;
import jakarta.persistence.TypedQuery;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;

public class OperadoresLogicosTest extends EntityManagerTest {





    @Test
    public void expressao_Operadores_Logicos() {
        String jqpl = "select  p from  Pedido p " +
                "where (p.status = 'AGUARDANDO' or  p.status = 'PAGO') and p.Total > 100 ";

        TypedQuery<Pedido> typedQuery = entityManager.createQuery(jqpl, Pedido.class);

        List<Pedido> pedidoList = typedQuery.getResultList();
        Assertions.assertFalse(pedidoList.isEmpty());


    }
}
