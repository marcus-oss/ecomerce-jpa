package ecomerce.dados.test.ValidationTest;

import ecomerce.dados.test.EntityManagerTest;
import ecommerce.model.Produto;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

public class ConversorTest extends EntityManagerTest {


    @Test
    public void Teste_converter_Atributo() {
        Produto produto = new Produto();
        produto.setDataCriacao(LocalDateTime.now());
        produto.setNome("Carregador de Notebook Dell");
        produto.setAtivo(Boolean.TRUE);

        entityManager.getTransaction().begin();

        entityManager.persist(produto);

        entityManager.getTransaction().commit();

        entityManager.clear();

        Produto produtoVerificacao = entityManager.find(Produto.class, produto.getId());
        Assertions.assertTrue(produtoVerificacao.getAtivo());
    }

}
