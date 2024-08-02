package Relacionamentos;

import ecomerce.dados.test.EntityManagerTest;
import ecommerce.model.Pedido;
import org.junit.jupiter.api.Test;

public class OptionalTest extends EntityManagerTest {

    @Test
    public void teste_Optional() {
        Pedido pedido = entityManager.find(Pedido.class, 1);


    }
}
