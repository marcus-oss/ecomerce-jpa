package ecomerce.dados.test.ValidationTest;

import ecomerce.dados.test.EntityManagerTest;
import ecommerce.model.Cliente;
import org.junit.jupiter.api.Test;

public class ValidacaoTest extends EntityManagerTest {


    @Test
    public void Teste_validacao_Cliente() {
        entityManager.getTransaction().begin();

        Cliente cliente = new Cliente();
        entityManager.merge(cliente);

        entityManager.getTransaction().commit();
    }
}
