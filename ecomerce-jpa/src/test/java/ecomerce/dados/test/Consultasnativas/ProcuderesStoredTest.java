package ecomerce.dados.test.Consultasnativas;

import ecomerce.dados.test.EntityManagerTest;
import ecommerce.model.Cliente;
import jakarta.persistence.ParameterMode;
import jakarta.persistence.StoredProcedureQuery;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.List;

public class ProcuderesStoredTest extends EntityManagerTest {


    @Test
    public void Teste_Procedure_Parametros() {
        StoredProcedureQuery storedProcedureQuery = entityManager.
                createStoredProcedureQuery("buscar_nome_produto");

        storedProcedureQuery.registerStoredProcedureParameter("produto_id",
                Integer.class, ParameterMode.IN);
        storedProcedureQuery.registerStoredProcedureParameter("produto_nome",
                String.class, ParameterMode.OUT);

        storedProcedureQuery.setParameter("produto_id", 3);
        String nome = (String) storedProcedureQuery.getOutputParameterValue("produto_nome");

        Assertions.assertEquals("Câmera GoPro Hero 7", nome);

    }

    @Test
    public void Teste_Procedure_Lista() {
        StoredProcedureQuery storedProcedureQuery = entityManager.
                createStoredProcedureQuery("compraram_acima_media", Cliente.class);

        storedProcedureQuery.registerStoredProcedureParameter("ano",
                Integer.class, ParameterMode.IN);

        storedProcedureQuery.setParameter("ano", 2020);

        List<Cliente> lista = storedProcedureQuery.getResultList();

        Assertions.assertFalse(lista.isEmpty());

    }

    @Test
    public void Teste_atualizar_Preco_Produto_Exercicio() {
        StoredProcedureQuery storedProcedureQuery = entityManager
                .createStoredProcedureQuery("ajustar_preco_produto", Cliente.class);

        storedProcedureQuery.registerStoredProcedureParameter(
                "produto_id", Integer.class, ParameterMode.IN);

        storedProcedureQuery.registerStoredProcedureParameter(
                "percentual_ajuste", BigDecimal.class, ParameterMode.IN);

        storedProcedureQuery.registerStoredProcedureParameter(
                "preco_ajustado", BigDecimal.class, ParameterMode.OUT);

        storedProcedureQuery.setParameter("produto_id", 1);
        storedProcedureQuery.setParameter("percentual_ajuste", new BigDecimal("0.1"));

        BigDecimal precoAjustado = (BigDecimal) storedProcedureQuery
                .getOutputParameterValue("preco_ajustado");

        Assertions.assertEquals(new BigDecimal("878.9"), precoAjustado);
    }


    @Test
    public void Teste_NamedStoredProcedure() {
        StoredProcedureQuery storedProcedureQuery = entityManager.createNamedStoredProcedureQuery(
                "compraram_acima_media");

        storedProcedureQuery.setParameter("ano",2024);


        List<Cliente> lista = storedProcedureQuery.getResultList();

        Assertions.assertFalse(lista.isEmpty());

    }
}
