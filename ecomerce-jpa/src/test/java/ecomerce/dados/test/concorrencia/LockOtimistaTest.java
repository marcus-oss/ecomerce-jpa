package ecomerce.dados.test.concorrencia;

import ecommerce.model.Produto;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class LockOtimistaTest {

    private static final Logger log = LoggerFactory.getLogger(LockOtimistaTest.class);
    protected static EntityManagerFactory entityManagerFactory;

    @BeforeAll
    public static void setUpBeforeClass() {
        entityManagerFactory = Persistence
                .createEntityManagerFactory("Ecommerce-PU");
    }

    @AfterAll
    public static void tearDownAfterClass() {
        entityManagerFactory.close();
    }

    private static void esperar(int segundos) {
        try {
            Thread.sleep(segundos * 1000);
        } catch (InterruptedException e) {
        }
    }

    private static void log(Object obj) {
        System.out.println("[LOG " + System.currentTimeMillis() + "] " + obj);
    }

    @Test
    public void usar_Lock() {
        Runnable runnable = () -> {

            EntityManager entityManager = entityManagerFactory.createEntityManager();
            entityManager.getTransaction().begin();

            log("Runnable vai Carregar o Produto");
            Produto produto = entityManager.find(Produto.class, 1);


            log("Runnable  vai espera por 3 segundos");
            esperar(3);

            log("Runnable vai alterar o Produto");
            produto.setNome("Algum Nome");

            log("Runnable vai confirmar a transação");
            entityManager.getTransaction().commit();
            entityManager.close();


        };

        Runnable runnable2 = () -> {

            EntityManager entityManager2 = entityManagerFactory.createEntityManager();
            entityManager2.getTransaction().begin();

            log("Runnable2 vai Carregar o Produto 1");
            Produto produto = entityManager2.find(Produto.class, 1);


            log("Runnable2  vai esperar por 1 segundo");
            esperar(1);

            log("Runnable2 vai alterar o Produto");
            produto.setNome("Algum Nome");

            log("Runnable2 vai confirmar a transação");
            entityManager2.getTransaction().commit();
            entityManager2.close();
        };

        Thread thread = new Thread(runnable);
        Thread thread2 = new Thread(runnable2);

        thread.start();
        thread2.start();

        try {
            thread.join();
            thread2.join();

        } catch (InterruptedException e) {
            throw new RuntimeException(e);

        }

        EntityManager entityManager3 = entityManagerFactory.createEntityManager();
        Produto produto = entityManager3.find(Produto.class, 1);
        entityManager3.close();

        Assertions.assertEquals("Algum Nome", produto.getNome());

        log("Encerrando método de teste.");
    }
}
