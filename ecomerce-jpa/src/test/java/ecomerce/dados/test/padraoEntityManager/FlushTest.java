package ecomerce.dados.test.padraoEntityManager;

import ecomerce.dados.test.EntityManagerTest;
import ecommerce.model.Pedido;
import ecommerce.model.StatusPedido;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class FlushTest extends EntityManagerTest {


    @Test
    public void chamar_flush() {
        Assertions.assertThrows(Exception.class, () -> erroAoChamarFlush());
    }

    private void erroAoChamarFlush() {

        try {
            entityManager.getTransaction().begin();

            Pedido pedido = entityManager.find(Pedido.class, 1);
            pedido.setStatus(StatusPedido.PAGO);

            entityManager.flush();

            if (pedido.getPagamentoCartao() == null) {
                throw new RuntimeException("Pedido ainda não foi pago!!");
            }
            entityManager.getTransaction().commit();
        } catch (Exception e) {
            entityManager.getTransaction().rollback();
            throw e;
        }
    }

}
