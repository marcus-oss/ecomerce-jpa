package ecomerce.dados.test.JPQLbasicoTest;

import ecomerce.dados.test.EntityManagerTest;
import ecommerce.model.Categoria;
import jakarta.persistence.TypedQuery;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;

public class PaginacaoJpqlTest extends EntityManagerTest {

    @Test
    public void paginacao() {
        String jpql = "select  c from Categoria c order by c.nome";

        TypedQuery<Categoria> typedQuery = entityManager.createQuery(jpql, Categoria.class);
        typedQuery.setFirstResult(0);
        typedQuery.setMaxResults(2);

        List<Categoria> categorias = typedQuery.getResultList();
        Assertions.assertFalse(categorias.isEmpty());

    }
}
