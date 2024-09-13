package ecomerce.dados.test.CasacataTest;

import ecomerce.dados.test.EntityManagerTest;
import ecommerce.model.*;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Arrays;

public class CascadeTypePersistTest extends EntityManagerTest {


    @Test
    public void persistir_Pedido_Com_Itens() {
        Cliente cliente = entityManager.find(Cliente.class, 1);
        Produto produto = entityManager.find(Produto.class, 1);

        Pedido pedido = new Pedido();
        pedido.setData_Ultimo_Pedido(LocalDateTime.now());
        pedido.setCliente(cliente);
        pedido.setTotal(produto.getPreco());
        pedido.setStatus(StatusPedido.AGUARDANDO);

        ItemPedido itemPedido = new ItemPedido();
        itemPedido.setId(new ItemPedidoId());
        itemPedido.setPedido(pedido);
        itemPedido.setProduto(produto);
        itemPedido.setQuantidade(1);
        itemPedido.setPrecoProduto(produto.getPreco());

        pedido.setItens(Arrays.asList(itemPedido)); // CascadeType.PERSIST

        entityManager.getTransaction().begin();
        entityManager.persist(pedido);
        entityManager.getTransaction().commit();

        entityManager.clear();

        Pedido pedidoVerificacao = entityManager.find(Pedido.class, pedido.getId());
        Assertions.assertNotNull(pedido);
        Assertions.assertFalse(pedido.getItens().isEmpty());

    }

    @Test
    public void persistir_Item_Pedido_Com_Pedido() {
        Cliente cliente = entityManager.find(Cliente.class, 1);
        Produto produto = entityManager.find(Produto.class, 1);

        Pedido pedido = new Pedido();
        pedido.setDataConclusaoPedido(LocalDateTime.now());
        LocalDateTime.now();
        pedido.setCliente(cliente);
        pedido.setTotal(produto.getPreco());
        pedido.setStatus(StatusPedido.AGUARDANDO);

        ItemPedido itemPedido = new ItemPedido();
        itemPedido.setId(new ItemPedidoId());
        itemPedido.setPedido(pedido);// Não é necessário CascadeType.PERSIST porque possui @MapsId.
        itemPedido.setProduto(produto);
        itemPedido.setQuantidade(1);
        itemPedido.setPrecoProduto(produto.getPreco());

        entityManager.getTransaction().begin();
        entityManager.persist(itemPedido);
        entityManager.getTransaction().commit();

        entityManager.clear();

        Pedido pedidoVerificacao = entityManager.find(Pedido.class, pedido.getId());
        Assertions.assertNotNull(pedido);
    }

    //    @Test
    public void persistir_Pedido_Com_Cliente() {
        Cliente cliente = new Cliente();
        cliente.setDataNascimento(LocalDate.of(1980, 1, 1));
        cliente.setSexo(SexoCliente.MASCULINO);
        cliente.setNome("José Carlos");
        cliente.setCpf("01234567890");

        Pedido pedido = new Pedido();
        pedido.setData_Ultimo_Pedido(LocalDateTime.now());
        pedido.setCliente(cliente); // CascadeType.PERSIST
        pedido.setTotal(BigDecimal.ZERO);
        pedido.setStatus(StatusPedido.AGUARDANDO);

        entityManager.getTransaction().begin();
        entityManager.persist(pedido);
        entityManager.getTransaction().commit();

        entityManager.clear();

        Cliente clienteVerificacao = entityManager.find(Cliente.class, cliente.getId());
        Assertions.assertNotNull(cliente);

    }
}
