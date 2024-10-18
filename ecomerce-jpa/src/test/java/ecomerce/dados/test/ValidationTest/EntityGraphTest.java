package ecomerce.dados.test.ValidationTest;

import ecomerce.dados.test.EntityManagerTest;
import ecommerce.model.Cliente;
import ecommerce.model.Cliente_;
import ecommerce.model.Pedido;
import ecommerce.model.Pedido_;
import jakarta.persistence.EntityGraph;
import jakarta.persistence.Subgraph;
import jakarta.persistence.TypedQuery;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;

public class EntityGraphTest extends EntityManagerTest {


    @Test
    public void Teste_buscar_Atributos_Essenciais_De_Pedido() {
        EntityGraph<Pedido> entityGraph = entityManager.createEntityGraph(Pedido.class);
        entityGraph.addAttributeNodes(
                "dataConclusaoPedido", "status", "Total", "notaFiscal");
        /*
        Map<String, Object> properties = new HashMap<>();
        properties.put("javax.persistence.fetchgraph", entityGraph);
//        properties.put("javax.persistence.loadgraph", entityGraph);
        Pedido pedido = entityManager.find(Pedido.class, 1, properties);
        Assert.assertNotNull(pedido);
        */

        TypedQuery<Pedido> typedQuery = entityManager
                .createQuery("select p from Pedido p", Pedido.class);
        typedQuery.setHint("javax.persistence.fetchgraph", entityGraph);
        List<Pedido> lista = typedQuery.getResultList();
        Assertions.assertFalse(lista.isEmpty());
    }


    @Test
    public void Teste_buscar_Atributos_Essenciais_De_Pedido02() {
        EntityGraph<Pedido> entityGraph = entityManager.createEntityGraph(Pedido.class);
        entityGraph.addAttributeNodes(
                "dataConclusaoPedido", "status", "Total");


        Subgraph<Cliente> clienteSubgraph = entityGraph.addSubgraph("cliente", Cliente.class);
        clienteSubgraph.addAttributeNodes("nome", "cpf");

        TypedQuery<Pedido> typedQuery = entityManager
                .createQuery("select p from Pedido p", Pedido.class);
        typedQuery.setHint("javax.persistence.fetchgraph", entityGraph);
        List<Pedido> lista = typedQuery.getResultList();
        Assertions.assertFalse(lista.isEmpty());
    }


    @Test
    public void Teste_buscar_Atributos_Essenciais_De_Pedido03() {
        EntityGraph<Pedido> entityGraph = entityManager.createEntityGraph(Pedido.class);
        entityGraph.addAttributeNodes(
                Pedido_.DATA_CONCLUSAO_PEDIDO, Pedido_.STATUS, Pedido_.TOTAL);


        Subgraph<Cliente> clienteSubgraph = entityGraph.addSubgraph(Pedido_.cliente);
        clienteSubgraph.addAttributeNodes(Cliente_.NOME, Cliente_.CPF);

        TypedQuery<Pedido> typedQuery = entityManager
                .createQuery("select p from Pedido p", Pedido.class);
        typedQuery.setHint("javax.persistence.fetchgraph", entityGraph);
        List<Pedido> lista = typedQuery.getResultList();
        Assertions.assertFalse(lista.isEmpty());
    }

    @Test
    public void Teste_buscar_Atributos_Essenciais_De_Pedido04() {
        EntityGraph<?> entityGraph = entityManager.createEntityGraph("Pedido.dadosEssenciais");

        entityGraph.addSubgraph("pagamento").addAttributeNodes("status");

        TypedQuery<Pedido> typedQuery = entityManager
                .createQuery("select p from Pedido p", Pedido.class);
        typedQuery.setHint("javax.persistence.fetchgraph", entityGraph);


        List<Pedido> lista = typedQuery.getResultList();
        Assertions.assertFalse(lista.isEmpty());
    }

}
