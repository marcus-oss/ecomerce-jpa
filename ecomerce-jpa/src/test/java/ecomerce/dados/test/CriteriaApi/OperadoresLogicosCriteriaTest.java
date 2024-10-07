package ecomerce.dados.test.CriteriaApi;

import ecomerce.dados.test.EntityManagerTest;
import ecommerce.model.Pedido;
import ecommerce.model.Pedido_;
import ecommerce.model.StatusPedido;
import jakarta.persistence.TypedQuery;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.List;

public class OperadoresLogicosCriteriaTest extends EntityManagerTest {


    @Test
    public void usar_Operadores_() {
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Pedido> criteriaQuery = criteriaBuilder.createQuery(Pedido.class);
        Root<Pedido> root = criteriaQuery.from(Pedido.class);

        criteriaQuery.select(root);

        criteriaQuery.where(
                criteriaBuilder.or(
                        criteriaBuilder.equal(
                                root.get(Pedido_.status), StatusPedido.AGUARDANDO),
                        criteriaBuilder.equal(
                                root.get(Pedido_.status), StatusPedido.PAGO)
                ),
                criteriaBuilder.greaterThan(
                        root.get(Pedido_.TOTAL), new BigDecimal(499))
        );

        TypedQuery<Pedido> typedQuery = entityManager.createQuery(criteriaQuery);

        List<Pedido> lista = typedQuery.getResultList();
        Assertions.assertFalse(lista.isEmpty());

        lista.forEach(p -> System.out.println(
                "ID: " + p.getId() + ", Total: " + p.getTotal()));
    }

}
