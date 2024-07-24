package ecomerce.dados.test.iniciandocomjpa;

import ecomerce.dados.test.EntityManagerTest;
import ecommerce.model.Produto;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

public class OperacaoComTransacaoTest extends EntityManagerTest {

    @Test
    public void inserir_o_Primeiro_Objeto_com_Merge() {
        Produto produto = new Produto();

        produto.setId(4);
        produto.setNome("Microfone video Maker");
        produto.setPreco(new BigDecimal(9000));

        entityManager.getTransaction().begin();
        entityManager.merge(produto);
        entityManager.getTransaction().commit();


        entityManager.clear();

        Produto produtoverificado = entityManager.find(Produto.class, produto.getId());
        Assertions.assertNotNull(produtoverificado);
    }


    @Test
    public void inserir_o_Primeiro_Objeto_com_Persist() {
        Produto produto = new Produto();

        produto.setId(5);
        produto.setNome("Nitro 5 com amd");
        produto.setPreco(new BigDecimal(12000));

        entityManager.getTransaction().begin();
        entityManager.persist(produto);
        produto.setNome("Notebook nitro");
        entityManager.getTransaction().commit();


        entityManager.clear();

        Produto produtopersist = entityManager.find(Produto.class, produto.getId());
        Assertions.assertNotNull(produtopersist);
    }


    @Test
    public void inserir_o_Primeiro_Objeto() {
        Produto produto = new Produto();

        produto.setId(2);
        produto.setNome("Camera");
        produto.setPreco(new BigDecimal(6000));

        entityManager.getTransaction().begin();
        entityManager.persist(produto);
        entityManager.getTransaction().commit();


        entityManager.clear();

        Produto produtoverificado = entityManager.find(Produto.class, produto.getId());
        Assertions.assertNotNull(produtoverificado);
    }


    @Test
    public void abrir_Fechar_transacao() {


        entityManager.getTransaction().begin();


        entityManager.getTransaction().commit();


    }


    @Test

    public void remover_Objeto() {
        Produto produto = entityManager.find(Produto.class, 3);

        produto.setId(3);

        entityManager.getTransaction().begin();
        entityManager.remove(produto);
        entityManager.getTransaction().commit();

        Produto produtoVerificacao = entityManager.find(Produto.class, 3);
        Assertions.assertNull(produtoVerificacao);

    }

    @Test
    public void atualizar_Objeto() {
        Produto produto = new Produto();

        produto.setId(1);
        produto.setNome("Kindle new");
        produto.setPreco(new BigDecimal(400));

        entityManager.getTransaction().begin();
        entityManager.merge(produto);
        entityManager.getTransaction().commit();

        entityManager.clear();

        Produto produtoverificado = entityManager.find(Produto.class, produto.getId());

        Assertions.assertNotNull(produtoverificado);
        Assertions.assertEquals("Kindle new", produtoverificado.getNome());
    }

    @Test
    public void atualizar_Objeto_Grenciado() {
        Produto produto = entityManager.find(Produto.class, 1);


        entityManager.getTransaction().begin();
        produto.setNome("Kindle new");
        entityManager.getTransaction().commit();

        entityManager.clear();

        Produto produtoverificado = entityManager.find(Produto.class, produto.getId());
        Assertions.assertEquals("Kindle new", produtoverificado.getNome());
    }
}
