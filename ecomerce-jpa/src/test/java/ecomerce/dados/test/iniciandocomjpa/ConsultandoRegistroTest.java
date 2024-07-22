package ecomerce.dados.test.iniciandocomjpa;

import ecomerce.dados.test.EntityManagerTest;
import ecommerce.model.Produto;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class ConsultandoRegistroTest extends EntityManagerTest {


    @Test
    public void buscar_por_identificador() {
        Produto produto = entityManager.find(Produto.class, 1);


        Assertions.assertNotNull(produto);

        Assertions.assertEquals("Kindle", produto.getNome());

    }

    @Test
    public void atualizar_referencias() {
        Produto produto = entityManager.find(Produto.class, 1);

        produto.setNome("Microfone");

        entityManager.refresh(produto);

        Assertions.assertEquals("Kindle", produto.getNome());
    }

}
