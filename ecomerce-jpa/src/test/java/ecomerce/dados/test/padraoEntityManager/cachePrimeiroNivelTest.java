package ecomerce.dados.test.padraoEntityManager;

import ecomerce.dados.test.EntityManagerTest;
import ecommerce.model.Produto;
import org.junit.jupiter.api.Test;

public class cachePrimeiroNivelTest extends EntityManagerTest {


    @Test
    public void verifcar_Cache() {
        Produto produto = entityManager.find(Produto.class, 1);

        System.out.println(produto.getNome());

        System.out.println("------------------");

        Produto produtoResgatado = entityManager.find(Produto.class, produto.getId());
        System.out.println(produtoResgatado.getNome());
    }
}
