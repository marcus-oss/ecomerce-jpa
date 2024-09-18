package ecomerce.dados.test.JPQLbasicoTest;

import ecomerce.dados.test.EntityManagerTest;
import ecommerce.model.NotaFiscal;
import jakarta.persistence.TemporalType;
import jakarta.persistence.TypedQuery;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Date;
import java.util.List;

public class PassandoParametroTest extends EntityManagerTest {


    @Test
    public void parametro_Test() {
        String jpql = "select nf from NotaFiscal  nf where nf.dataEmissao <= ?1";

        TypedQuery<NotaFiscal> typedQuery = entityManager.createQuery(jpql, NotaFiscal.class);
        typedQuery.setParameter(1, new Date(), TemporalType.TIMESTAMP);


        List<NotaFiscal> notaFiscals = typedQuery.getResultList();
        Assertions.assertTrue(notaFiscals.size() == 1);
    }


}
