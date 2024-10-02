package ecomerce.dados.test.CriteriaApi;

import ecomerce.dados.test.EntityManagerTest;
import ecommerce.model.*;
import jakarta.persistence.TypedQuery;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Join;
import jakarta.persistence.criteria.Root;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;

public class JoinCriteriaTest extends EntityManagerTest {

    @Test
    public void fazer_Join() {
        CriteriaBuilder criteriaQueryBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Pedido> criteriaQuery = criteriaQueryBuilder.createQuery(Pedido.class);
        Root<Pedido> produtoRoot = criteriaQuery.from(Pedido.class);
        Join<Pedido, Pagamento> join = produtoRoot.join("pagamento");


        criteriaQuery.select(produtoRoot);
//        criteriaQuery.select(join);
//        criteriaQuery.where(criteriaQueryBuilder.equal(join.get("status"), StatusPagamento.PROCESSANDO));

        TypedQuery<Pedido> typedQuery = entityManager.createQuery(criteriaQuery);
        List<Pedido> lista = typedQuery.getResultList();
        Assertions.assertTrue(lista.size() == 5);

    }
}
