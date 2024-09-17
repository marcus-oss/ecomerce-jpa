package ecomerce.dados.test.CasacataTest;

import ecomerce.dados.test.EntityManagerTest;
import ecommerce.model.ItemPedido;
import ecommerce.model.ItemPedidoId;
import ecommerce.model.Pedido;
import ecommerce.model.Produto;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class CascadeTypeRemoveTest extends EntityManagerTest {

    @Test
    public void remover_Relacao_Produto_Categoria() {
        Produto produto = entityManager.find(Produto.class, 1);

        Assertions.assertFalse(produto.getCategorias().isEmpty());

        entityManager.getTransaction().begin();
        produto.getCategorias().clear();
        entityManager.getTransaction().commit();

        entityManager.clear();

        Produto produtoVerificacao = entityManager.find(Produto.class, produto.getId());
        Assertions.assertTrue(produtoVerificacao.getCategorias().isEmpty());
    }

    //    @Test
    public void remover_Itens_Orfaos() {
        Pedido pedido = entityManager.find(Pedido.class, 1);

        Assertions.assertFalse(pedido.getItens().isEmpty());

        entityManager.getTransaction().begin();
        pedido.getItens().clear();
        entityManager.getTransaction().commit();

        entityManager.clear();

        Pedido pedidoVerificacao = entityManager.find(Pedido.class, pedido.getId());
        Assertions.assertTrue(pedidoVerificacao.getItens().isEmpty());
    }


    //    @Test
    public void remover_Pedido_E_Itens() {
        Pedido pedido = entityManager.find(Pedido.class, 1);

        entityManager.getTransaction().begin();
        entityManager.remove(pedido);
        entityManager.getTransaction().commit();

        entityManager.clear();

        Pedido pedidoVerificacao = entityManager.find(Pedido.class, pedido.getId());
        Assertions.assertNull(pedidoVerificacao);
    }


    @Test
    public void remover_ItemPedido_E_Pedido() {
        ItemPedido itemPedido = entityManager.find(
                ItemPedido.class, new ItemPedidoId(1, 1));

        entityManager.getTransaction().begin();
        entityManager.remove(itemPedido);
        entityManager.getTransaction().commit();

        entityManager.clear();

        Pedido pedidoVerificacao = entityManager.find(Pedido.class, itemPedido.getPedido().getId());
        Assertions.assertNull(pedidoVerificacao);
    }
}
