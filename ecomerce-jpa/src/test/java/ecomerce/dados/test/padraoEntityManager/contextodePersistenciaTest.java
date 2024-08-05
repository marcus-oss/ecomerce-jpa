package ecomerce.dados.test.padraoEntityManager;

import ecomerce.dados.test.EntityManagerTest;
import ecommerce.model.Produto;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

public class contextodePersistenciaTest extends EntityManagerTest {


    @Test
    public void usar_Contexto_Persistencia() {
        entityManager.getTransaction().begin();

        Produto produto = entityManager.find(Produto.class, 1);
        produto.setPreco(new BigDecimal(200));

        Produto produto2 = new Produto();
        produto2.setNome("Caneca para café");
        produto2.setPreco(new BigDecimal(23));
        entityManager.persist(produto2);


        Produto produto3 = new Produto();
        produto3.setNome("Caneca para  terere");
        produto3.setPreco(new BigDecimal(30));
        entityManager.merge(produto3);

        entityManager.getTransaction().commit();


    }

}
