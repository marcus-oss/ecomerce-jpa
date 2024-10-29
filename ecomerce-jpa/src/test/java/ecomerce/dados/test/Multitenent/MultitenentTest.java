package ecomerce.dados.test.Multitenent;

import ecomerce.dados.test.EntityManagerFactoryTest;
import ecommerce.hibernate.EcmCurrentTenantIdentifierResolver;
import ecommerce.model.Produto;
import jakarta.persistence.EntityManager;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class MultitenentTest extends EntityManagerFactoryTest {


    @Test
    public void usarAbordagemPorSchema() {
        EcmCurrentTenantIdentifierResolver.setTenantIdentifier("ecommerce");
        EntityManager entityManager1 = entityManagerFactory.createEntityManager();
        Produto produto1 = entityManager1.find(Produto.class, 1);
        Assertions.assertEquals("Kindle", produto1.getNome());
        entityManager1.close();


        EcmCurrentTenantIdentifierResolver.setTenantIdentifier("loja_ecommerce");
        EntityManager entityManager2 = entityManagerFactory.createEntityManager();
        Produto produto2 = entityManager2.find(Produto.class, 1);
        Assertions.assertEquals("Kindle Paperwhite", produto2.getNome());
        entityManager2.close();
    }
}
