package ecomerce.dados.test.JPQLbasicoTest;

import ecomerce.dados.test.EntityManagerTest;
import ecommerce.model.Categoria;
import jakarta.persistence.EntityManager;
import jakarta.persistence.Persistence;
import jakarta.persistence.TypedQuery;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.List;

public class AbordagemHibridaTest extends EntityManagerTest {

    @BeforeAll
    public static void setUpBeforeClass() {
        entityManagerFactory = Persistence.createEntityManagerFactory("Ecommerce-PU");

        EntityManager em = entityManagerFactory.createEntityManager();

        TypedQuery<Categoria> typedQuery = em.createQuery("select c from Categoria c", Categoria.class);
        entityManagerFactory.addNamedQuery("Categoria.listar", typedQuery);
    }

    @Test
    public void abordagem_Hibrida() {

        TypedQuery<Categoria> typedQuery = entityManager.createNamedQuery("Categoria.listar", Categoria.class);
        List<Categoria> lista = typedQuery.getResultList();

        Assertions.assertFalse(lista.isEmpty());
    }
}
