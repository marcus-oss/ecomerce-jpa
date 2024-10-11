package ecomerce.dados.test.CriteriaApi;

import ecomerce.dados.test.EntityManagerTest;
import ecommerce.model.Categoria;
import ecommerce.model.Categoria_;
import ecommerce.model.Produto;
import ecommerce.model.Produto_;
import jakarta.persistence.Query;
import jakarta.persistence.criteria.*;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

public class OperacoesEmLoteTest extends EntityManagerTest {


    @Test
    public void Teste_Atualizar_Lote() {
        entityManager.getTransaction().begin();

        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaUpdate<Produto> criteriaUpdate = criteriaBuilder.createCriteriaUpdate(Produto.class);
        Root<Produto> root = criteriaUpdate.from(Produto.class);

        criteriaUpdate.set(root.get(Produto_.preco),
                criteriaBuilder.prod(root.get(Produto_.preco), new BigDecimal("1.1")));

        Subquery<Integer> subquery = criteriaUpdate.subquery(Integer.class);
        Root<Produto> subProdutoRoot = subquery.correlate(root);
        Join<Produto, Categoria> categoriaJoin = subProdutoRoot.join(Produto_.categorias);
        subquery.select(criteriaBuilder.literal(1));
        subquery.where(criteriaBuilder.equal(categoriaJoin.get(Categoria_.id), 2));


        criteriaUpdate.where(criteriaBuilder.exists(subquery));
        Query query = entityManager.createQuery(criteriaUpdate);
        query.executeUpdate();

        entityManager.getTransaction().commit();

    }

    @Test
    public void Teste_remover_Em_Lote_Exercicio() {
        entityManager.getTransaction().begin();


        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaDelete<Produto> criteriaDelete = criteriaBuilder.createCriteriaDelete(Produto.class);
        Root<Produto> root = criteriaDelete.from(Produto.class);

        criteriaDelete.where(criteriaBuilder.between(root.get(Produto_.id), 5, 12));

        Query query = entityManager.createQuery(criteriaDelete);
        query.executeUpdate();

        entityManager.getTransaction().commit();
    }

}
