package ecomerce.dados.test;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;

public class EntityManagerTest {

    protected static EntityManagerFactory entityManagerFactory;


    protected EntityManager entityManager;

    @BeforeAll
    public static void setUpBeforeClass() {

        entityManagerFactory = Persistence.createEntityManagerFactory("Ecommerce-PU");
    }

    @AfterAll
    public static void tearDowAfterClass() {
        entityManagerFactory.close();

    }

    @BeforeEach
    public void setUp() {
        entityManager = entityManagerFactory.createEntityManager();
    }

    @AfterEach
    public void tearDow() {
        entityManager.close();


    }

}
