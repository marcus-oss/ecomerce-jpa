package ecomerce.dados.test.mapeamentobasico;

import ecomerce.dados.test.EntityManagerTest;
import ecommerce.model.Categoria;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class EstrategiaChavePrimariaTest extends EntityManagerTest {

    @Test
    public void teste_Estrategia_Auto() {
        Categoria categoria = new Categoria();
        categoria.setNome("Eletronicos");

        entityManager.getTransaction().begin();
        entityManager.persist(categoria);
        entityManager.getTransaction().commit();

        entityManager.clear();

        Categoria categoriaverirficacao = entityManager.find(Categoria.class, categoria.getId());
        Assertions.assertNotNull(categoriaverirficacao);
    }


    @Test
    public void teste_Estrategia_Chave() {
        Categoria categoria = new Categoria();
        categoria.setNome("Eletronicos");

        entityManager.getTransaction().begin();
        entityManager.persist(categoria);
        entityManager.getTransaction().commit();

        entityManager.clear();

        Categoria categoriaverirficacao = entityManager.find(Categoria.class, categoria.getId());
        Assertions.assertNotNull(categoriaverirficacao);
    }
}
