package ecomerce.dados.test.Cache;

import ecommerce.model.Pedido;
import jakarta.persistence.*;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Assertions;

import java.util.HashMap;
import java.util.Map;

public class CachecTest {


    
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
    public void busca_Do_Cache() {
        EntityManager entityManager1 = entityManagerFactory.createEntityManager();
        EntityManager entityManager2 = entityManagerFactory.createEntityManager();

        System.out.println("Buscando a partir da instância 1:");
        entityManager1.find(Pedido.class, 1);

        System.out.println("Buscando a partir da instância 2:");
        entityManager2.find(Pedido.class, 1);


        entityManager1.close();
        entityManager2.close();

    }

    @Test
    public void adicionar_Pedidos_No_Cache() {
        EntityManager entityManager1 = entityManagerFactory.createEntityManager();
        EntityManager entityManager2 = entityManagerFactory.createEntityManager();

        System.out.println("Buscando a partir da instância 1:");
        entityManager1.createQuery("select p from Pedido p", Pedido.class)
                .getResultList();

        System.out.println("Buscando a partir da instância 2:");
        entityManager2.find(Pedido.class, 1);


        entityManager1.close();
        entityManager2.close();
    }

    @Test
    public void reomver_Pedidos_No_Cache() {
        Cache cache = entityManagerFactory.getCache();

        EntityManager entityManager1 = entityManagerFactory.createEntityManager();
        EntityManager entityManager2 = entityManagerFactory.createEntityManager();

        System.out.println("Buscando a partir da instância 1:");
        entityManager1.createQuery("select p from Pedido p", Pedido.class)
                .getResultList();

        System.out.println("Removendo do  cache");
        cache.evictAll();

        System.out.println("Buscando a partir da instância 2:");
        entityManager2.find(Pedido.class, 1);
        entityManager2.find(Pedido.class, 2);

        entityManager1.close();
        entityManager2.close();

    }

    @Test
    public void verificar_se_Esta_No_Cache() {
        Cache cache = entityManagerFactory.getCache();

        EntityManager entityManager1 = entityManagerFactory.createEntityManager();


        System.out.println("Buscando a partir da instância 1:");
        entityManager1.createQuery("select p from Pedido p", Pedido.class)
                .getResultList();


        Assertions.assertTrue(cache.contains(Pedido.class, 1));
        Assertions.assertTrue(cache.contains(Pedido.class, 2));

        entityManager1.close();

    }

    @Test
    public void analisarOpcoes_Cache() {
        Cache cache = entityManagerFactory.getCache();

        EntityManager entityManager1 = entityManagerFactory.createEntityManager();


        System.out.println("Buscando a partir da instância 1:");
        entityManager1.createQuery("select p from Pedido p", Pedido.class)
                .getResultList();

        Assertions.assertTrue(cache.contains(Pedido.class, 1));

        entityManager1.close();

    }

    @Test
    public void controlar_Cache_Dinamicamente() {
        Cache cache = entityManagerFactory.getCache();

        System.out.println("Buscando todos os pedidos..........................");
        EntityManager entityManager1 = entityManagerFactory.createEntityManager();
        entityManager1.setProperty("javax.persistence.cache.storeMode", CacheStoreMode.BYPASS);

        entityManager1
                .createQuery("select p from Pedido p", Pedido.class)
                .setHint("javax.persistence.cache.storeMode", CacheStoreMode.USE)
                .getResultList();

        System.out.println("Buscando o pedido de ID igual a 2..................");
        EntityManager entityManager2 = entityManagerFactory.createEntityManager();
        Map<String, Object> propriedades = new HashMap<>();
//        propriedades.put("javax.persistence.cache.storeMode", CacheStoreMode.BYPASS);
//        propriedades.put("javax.persistence.cache.retrieveMode", CacheRetrieveMode.BYPASS);
        entityManager2.find(Pedido.class, 2, propriedades);

        System.out.println("Buscando todos os pedidos (de novo)..........................");
        EntityManager entityManager3 = entityManagerFactory.createEntityManager();
        entityManager3
                .createQuery("select p from Pedido p", Pedido.class)
//                .setHint("javax.persistence.cache.retrieveMode", CacheRetrieveMode.BYPASS)
                .getResultList();


        entityManager1.close();
        entityManager2.close();

    }


    @Test
    public void ehcache() {
        Cache cache = entityManagerFactory.getCache();

        EntityManager entityManager1 = entityManagerFactory.createEntityManager();
        EntityManager entityManager2 = entityManagerFactory.createEntityManager();

        log("Buscando e incluindo no cache...");
        entityManager1
                .createQuery("select p from Pedido p", Pedido.class)
                .getResultList();
        log("---");

        esperar(1);
        Assertions.assertTrue(cache.contains(Pedido.class, 2));
        entityManager2.find(Pedido.class, 2);

        esperar(3);
        Assertions.assertFalse(cache.contains(Pedido.class, 2));


        entityManager1.close();
        entityManager2.close();
    }
}
