package ecomerce.dados.test.padraoEntityManager;

import ecomerce.dados.test.EntityManagerTest;
import ecommerce.model.Pedido;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class GerenciamentoTransacoesTest extends EntityManagerTest {


    @Test
    public void abrir_fechar_cancelar_transacao() {
        Assertions.assertThrows(Exception.class, () -> erro_esperado_negocio());
    }

    private void erro_esperado_negocio() {

        try {
            entityManager.getTransaction().begin();
            metodoDeNegocio();
            entityManager.getTransaction().commit();
        } catch (Exception e) {
            entityManager.getTransaction().rollback();
            throw e;
        }

    }

    private void metodoDeNegocio() {
        Pedido pedido = entityManager.find(Pedido.class, 1);


        if (pedido.getPagamentoCartao() == null) {
            throw new RuntimeException("Pedido ainda não foi pago!!!");
        }
    }
}
