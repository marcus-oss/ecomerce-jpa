package ecomerce.dados.test.concorrencia;

import ecommerce.model.Produto;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.LockModeType;
import jakarta.persistence.Persistence;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

public class LockPessimistaTest {


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

    private static void log(Object obj, Object... args) {
        System.out.println(
                String.format("[LOG " + System.currentTimeMillis() + "] " + obj, args)
        );
    }

    private static void esperar(int segundos) {
        try {
            Thread.sleep(segundos * 1000);
        } catch (InterruptedException e) {
        }
    }

    @Test
    public void usarLockPessimista_LockMode_TypePessimisticRead() {
        Runnable runnable1 = () -> {
            log("Iniciando Runnable 01.");

            String novoNome = "Nome Novo. CTM: " + System.currentTimeMillis();

            EntityManager entityManager1 = entityManagerFactory.createEntityManager();
            entityManager1.getTransaction().begin();

            log("Runnable 01 vai carregar o produto 1.");
            Produto produto = entityManager1.find(
                    Produto.class, 1, LockModeType.PESSIMISTIC_READ);

            log("Runnable 01 vai alterar o produto.");
            produto.setNome(novoNome);

            log("Runnable 01 vai esperar por 3 segundo(s).");
            esperar(3);

            log("Runnable 01 vai confirmar a transação.");
            entityManager1.getTransaction().commit();
            entityManager1.close();

            log("Encerrando Runnable 01.");
        };

        Runnable runnable2 = () -> {
            log("Iniciando Runnable 02.");

            String novoNome = "Nome Novo. CTM: " + System.currentTimeMillis();

            EntityManager entityManager2 = entityManagerFactory.createEntityManager();
            entityManager2.getTransaction().begin();

            log("Runnable 02 vai carregar o produto 2.");
            Produto produto = entityManager2.find(
                    Produto.class, 1, LockModeType.PESSIMISTIC_READ);

            log("Runnable 02 vai alterar o produto.");
            produto.setNome(novoNome);

            log("Runnable 02 vai esperar por 1 segundo(s).");
            esperar(1);

            log("Runnable 02 vai confirmar a transação.");
            entityManager2.getTransaction().commit();
            entityManager2.close();

            log("Encerrando Runnable 02.");
        };

        Thread thread1 = new Thread(runnable1);
        Thread thread2 = new Thread(runnable2);

        thread1.start();

        esperar(1);
        thread2.start();

        try {
            thread1.join();
            thread2.join();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        EntityManager entityManager3 = entityManagerFactory.createEntityManager();
        Produto produto = entityManager3.find(Produto.class, 1);
        entityManager3.close();

        Assertions.assertTrue(produto.getNome().startsWith("Nome Novo"));

        log("Encerrando método de teste.");
    }

        @Test
        public void usarLockPessimistaLockModeTypePessimisticWrite() {
        Runnable runnable1 = () -> {
            log("Iniciando Runnable 01.");

            String novoNome = "Nome detalhado. CTM: " + System.currentTimeMillis();

            EntityManager entityManager1 = entityManagerFactory.createEntityManager();
            entityManager1.getTransaction().begin();

            log("Runnable 01 vai carregar o produto 1.");
            Produto produto = entityManager1.find(
                    Produto.class, 1, LockModeType.PESSIMISTIC_WRITE);

            log("Runnable 01 vai alterar o produto.");
            produto.setNome(novoNome);

            log("Runnable 01 vai esperar por 3 segundo(s).");
            esperar(3);

            log("Runnable 01 vai confirmar a transação.");
            entityManager1.getTransaction().commit();
            entityManager1.close();

            log("Encerrando Runnable 01.");
        };

        Runnable runnable2 = () -> {
            log("Iniciando Runnable 02.");

            String novoNome = "Nome detalhado. CTM: " + System.currentTimeMillis();

            EntityManager entityManager2 = entityManagerFactory.createEntityManager();
            entityManager2.getTransaction().begin();

            log("Runnable 02 vai carregar o produto 2.");
            Produto produto = entityManager2.find(
                    Produto.class, 1, LockModeType.PESSIMISTIC_WRITE);

            log("Runnable 02 vai alterar o produto.");
            produto.setNome(novoNome);

            log("Runnable 02 vai esperar por 1 segundo(s).");
            esperar(1);

            log("Runnable 02 vai confirmar a transação.");
            entityManager2.getTransaction().commit();
            entityManager2.close();

            log("Encerrando Runnable 02.");
        };

        Thread thread1 = new Thread(runnable1);
        Thread thread2 = new Thread(runnable2);

        thread1.start();

        esperar(1);
        thread2.start();

        try {
            thread1.join();
            thread2.join();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        EntityManager entityManager3 = entityManagerFactory.createEntityManager();
        Produto produto = entityManager3.find(Produto.class, 1);
        entityManager3.close();

        Assertions.assertTrue(produto.getNome().startsWith("Nome detalhado. CTM:"));

        log("Encerrando método de teste.");
    }

    @Test
    public void MisturarTiposdeLock() {
        Runnable runnable1 = () -> {
            log("Iniciando Runnable 01.");

            String novoNome = "Nome detalhado. CTM: " + System.currentTimeMillis();

            EntityManager entityManager1 = entityManagerFactory.createEntityManager();
            entityManager1.getTransaction().begin();

            log("Runnable 01 vai carregar o produto 1.");
            Produto produto = entityManager1.find(
                    Produto.class, 1, LockModeType.PESSIMISTIC_READ);

            log("Runnable 01 vai alterar o produto.");
            produto.setNome(novoNome);

            log("Runnable 01 vai esperar por 3 segundo(s).");
            esperar(3);

            log("Runnable 01 vai confirmar a transação.");
            entityManager1.getTransaction().commit();
            entityManager1.close();

            log("Encerrando Runnable 01.");
        };

        Runnable runnable2 = () -> {
            log("Iniciando Runnable 02.");

            String novoNome = "Nome detalhado. CTM: " + System.currentTimeMillis();

            EntityManager entityManager2 = entityManagerFactory.createEntityManager();
            entityManager2.getTransaction().begin();

            log("Runnable 02 vai carregar o produto 2.");
            Produto produto = entityManager2.find(
                    Produto.class, 1, LockModeType.PESSIMISTIC_WRITE);

            log("Runnable 02 vai alterar o produto.");
            produto.setNome(novoNome);

            log("Runnable 02 vai esperar por 1 segundo(s).");
            esperar(1);

            log("Runnable 02 vai confirmar a transação.");
            entityManager2.getTransaction().commit();
            entityManager2.close();

            log("Encerrando Runnable 02.");
        };

        Thread thread1 = new Thread(runnable1);
        Thread thread2 = new Thread(runnable2);

        thread1.start();

        esperar(1);
        thread2.start();

        try {
            thread1.join();
            thread2.join();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        EntityManager entityManager3 = entityManagerFactory.createEntityManager();
        Produto produto = entityManager3.find(Produto.class, 1);
        entityManager3.close();

        Assertions.assertTrue(produto.getNome().startsWith("Nome detalhado. CTM:"));

        log("Encerrando método de teste.");
    }

    @Test
    public void usarLockNaTypedQuery() {
        Runnable runnable1 = () -> {
            log("Iniciando Runnable 01.");

            String novoNome = "Novo Nome do produto" + System.currentTimeMillis();

            EntityManager entityManager1 = entityManagerFactory.createEntityManager();
            entityManager1.getTransaction().begin();

            log("Runnable 01 vai carregar todos os produtos.");
            List<Produto> lista = entityManager1
                    .createQuery("select p from Produto p where p.id in (3,4,5)")
                    .setLockMode(LockModeType.PESSIMISTIC_READ)
                    .getResultList();

            Produto produto = lista
                    .stream()
                    .filter(p -> p.getId().equals(3))
                    .findFirst()
                    .get();

            log("Runnable 01 vai alterar o produto de ID igual a 1.");
            produto.setNome(novoNome);

            log("Runnable 01 vai esperar por 3 segundo(s).");
            esperar(3);

            log("Runnable 01 vai confirmar a transação.");
            entityManager1.getTransaction().commit();
            entityManager1.close();

            log("Encerrando Runnable 01.");
        };

        Runnable runnable2 = () -> {
            log("Iniciando Runnable 02.");

            String novoNome = "Novo Nome do produto" + System.currentTimeMillis();

            EntityManager entityManager2 = entityManagerFactory.createEntityManager();
            entityManager2.getTransaction().begin();

            log("Runnable 02 vai carregar o produto 2.");
            Produto produto = entityManager2.find(
                    Produto.class, 1, LockModeType.PESSIMISTIC_WRITE);

            log("Runnable 02 vai alterar o produto.");
            produto.setNome(novoNome);

            log("Runnable 02 vai esperar por 1 segundo(s).");
            esperar(1);

            log("Runnable 02 vai confirmar a transação.");
            entityManager2.getTransaction().commit();
            entityManager2.close();

            log("Encerrando Runnable 02.");
        };

        Thread thread1 = new Thread(runnable1);
        Thread thread2 = new Thread(runnable2);

        thread1.start();

        esperar(1);
        thread2.start();

        try {
            thread1.join();
            thread2.join();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        EntityManager entityManager3 = entityManagerFactory.createEntityManager();
        Produto produto = entityManager3.find(Produto.class, 1);
        entityManager3.close();

        Assertions.assertTrue(produto.getNome().startsWith("Novo Nome do produto"));

        log("Encerrando método de teste.");
    }
}
