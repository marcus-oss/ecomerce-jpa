package ecomerce.dados.test.CriteriaApi;

import ecomerce.dados.test.EntityManagerTest;
import ecommerce.model.NotaFiscal;
import ecommerce.model.Pedido;
import jakarta.persistence.TemporalType;
import jakarta.persistence.TypedQuery;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.ParameterExpression;
import jakarta.persistence.criteria.Root;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class PassandoParametroCriteriaTest extends EntityManagerTest {

    @Test
    public void passar_Parametro() {

        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Pedido> criteriaQuery = criteriaBuilder.createQuery(Pedido.class);
        Root<Pedido> pedidoRoot = criteriaQuery.from(Pedido.class);

        criteriaQuery.select(pedidoRoot);
        ParameterExpression<Integer> parameterExpression = criteriaBuilder.parameter(Integer.class, "id");
        criteriaQuery.where(criteriaBuilder.equal(pedidoRoot.get("id"), parameterExpression));

        TypedQuery<Pedido> typedQuery = entityManager.createQuery(criteriaQuery);
        typedQuery.setParameter("id", 1);

        Pedido pedido = typedQuery.getSingleResult();
        Assertions.assertNotNull(pedido);
    }

    @Test
    public void passar_Parametro_Date() {

        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<NotaFiscal> criteriaQuery = criteriaBuilder.createQuery(NotaFiscal.class);
        Root<NotaFiscal> pedidoRoot = criteriaQuery.from(NotaFiscal.class);

        criteriaQuery.select(pedidoRoot);
        ParameterExpression<Date> parameterExpression = criteriaBuilder.parameter(Date.class, "dataInicial");
        criteriaQuery.where(criteriaBuilder.greaterThan(pedidoRoot.get("dataEmissao"), parameterExpression));

        Calendar dataInicial = Calendar.getInstance();
        dataInicial.add(Calendar.DATE, -30);


        TypedQuery<NotaFiscal> typedQuery = entityManager.createQuery(criteriaQuery);
        typedQuery.setParameter("dataInicial", dataInicial.getTime(), TemporalType.TIMESTAMP);

        List<NotaFiscal> notaFiscals = typedQuery.getResultList();
        Assertions.assertFalse(notaFiscals.isEmpty());
    }

}
