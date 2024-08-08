package ecomerce.dados.test.padraoEntityManager;

import ecomerce.dados.test.EntityManagerTest;
import ecommerce.model.Cliente;
import ecommerce.model.Pedido;
import ecommerce.model.StatusPedido;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class CallBacksTest extends EntityManagerTest {


    @Test
    public void adiconar_Callbacks() {
        Cliente cliente = entityManager.find(Cliente.class, 1);

        Pedido pedido = new Pedido();
        pedido.setCliente(cliente);
        pedido.setStatus(StatusPedido.AGUARDANDO);


        entityManager.getTransaction().begin();

        entityManager.persist(pedido);
        entityManager.flush();

        pedido.setStatus(StatusPedido.PAGO);
        entityManager.getTransaction().commit();

        entityManager.clear();

        Pedido pedidoVerificacao = entityManager.find(Pedido.class, pedido.getId());
        Assertions.assertNotNull(pedidoVerificacao.getDataConclusaoPedido());
        Assertions.assertNotNull(pedidoVerificacao.getData_Ultimo_Pedido());
    }
}
