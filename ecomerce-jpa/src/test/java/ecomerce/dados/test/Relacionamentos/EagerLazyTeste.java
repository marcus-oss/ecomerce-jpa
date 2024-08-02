package Relacionamentos;

import ecomerce.dados.test.EntityManagerTest;
import ecommerce.model.Pedido;
import org.junit.jupiter.api.Test;

public class EagerLazyTeste extends EntityManagerTest {

    @Test
    public void verificar_Comportamento() {
        Pedido pedido = entityManager.find(Pedido.class, 1);

//        pedido.getItens().isEmpty();


    }
}
