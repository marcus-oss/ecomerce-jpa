package ecomerce.dados.test.mapeamentobasico;

import ecomerce.dados.test.EntityManagerTest;
import ecommerce.model.EnderecoEntregaPedido;
import ecommerce.model.Pedido;
import ecommerce.model.StatusPedido;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class MapeamentoObjetoEmbutidoTest extends EntityManagerTest {

    @Test
    public void analisar_Mapeamento_Em_Butido() {
         Cliente cliente = entityManager.find(Cliente.class, 1);

        EnderecoEntregaPedido endereco = new EnderecoEntregaPedido();
        endereco.setCep("0009-90000");
        endereco.setLogradouro("Rua teste 200");
        endereco.setNumero("1122");
        endereco.setBairro("centro");
        endereco.setCidade("Uberlandia");
        endereco.setComplemento("MG");

        Pedido pedido = new Pedido();

        pedido.setDataPedido(LocalDateTime.now());
        pedido.setStatus(StatusPedido.AGUARDANDO);
        pedido.setTotal(new BigDecimal(2000));
        pedido.setEntregaPedido(endereco);
        pedido.setCliente(cliente);

        entityManager.getTransaction().begin();
        entityManager.persist(pedido);
        entityManager.getTransaction().commit();


        entityManager.clear();

        Pedido pedidoVerificacao = entityManager.find(Pedido.class, pedido.getId());
        Assertions.assertNotNull(pedidoVerificacao);
        Assertions.assertNotNull(pedidoVerificacao.getEntregaPedido());
        Assertions.assertNotNull(pedidoVerificacao.getEntregaPedido().getCep());
    }

}
