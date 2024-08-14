package ecomerce.dados.test.mapeamentoAvancado;

import ecomerce.dados.test.EntityManagerTest;
import ecommerce.model.Cliente;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class propriedadeTransientTest extends EntityManagerTest {

    @Test
    public void validar_Primeiro_Nome() {
        Cliente cliente = entityManager.find(Cliente.class, 1);

        Assertions.assertEquals("Matheus", cliente.getPrimeironome());
    }

}
