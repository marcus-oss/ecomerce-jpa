package Relacionamentos;

import ecomerce.dados.test.EntityManagerTest;
import ecommerce.model.*;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class RelacionamentosManyToOneTest extends EntityManagerTest {


    @Test
    public void verificarRelacionamento() {
        Cliente cliente = entityManager.find(Cliente.class, 1);

        Pedido pedido = new Pedido();
        pedido.setStatus(StatusPedido.AGUARDANDO);
        pedido.setDataPedido(LocalDateTime.now());
        pedido.setTotal(BigDecimal.TEN);

        pedido.setCliente(cliente);

        entityManager.getTransaction().begin();
        entityManager.persist(pedido);
        entityManager.getTransaction().commit();

        entityManager.clear();

        Pedido pedidoVerificacao = entityManager.find(Pedido.class, pedido.getId());
        Assertions.assertNotNull(pedidoVerificacao.getCliente());
    }

    @Test
    public void verificar_Relacionamento_Item_Pedido() {
       entityManager.getTransaction().begin();

        Cliente cliente = entityManager.find(Cliente.class, 1);
        Produto produto = entityManager.find(Produto.class, 1);

        Pedido pedido = new Pedido();
        pedido.setStatus(StatusPedido.AGUARDANDO);
        pedido.setData_Ultimo_Pedido(LocalDateTime.now());
        pedido.setTotal(BigDecimal.TEN);
        pedido.setCliente(cliente);

        entityManager.persist(pedido);

        entityManager.flush();

        ItemPedido itemPedido = new ItemPedido();
//        itemPedido.setPedidoId(pedido.getId());
//        itemPedido.setProdutoId(produto.getId());
//        itemPedido.setId(new ItemPedidoId(pedido.getId(), produto.getId()));
        itemPedido.setId(new ItemPedidoId());
        itemPedido.setPrecoProduto(produto.getPreco());
        itemPedido.setQuantidade(1);
        itemPedido.setPedido(pedido);
        itemPedido.setProduto(produto);

        entityManager.persist(itemPedido);
        entityManager.persist(pedido);

        entityManager.getTransaction().commit();

        entityManager.clear();

        ItemPedido itemPedidoVerificacao = entityManager.find(
                ItemPedido.class, new ItemPedidoId(pedido.getId(), produto.getId()));
        Assertions.assertNotNull(itemPedidoVerificacao.getPedido());
        Assertions.assertNotNull(itemPedidoVerificacao.getProduto());
    }
}
