package ecomerce.dados.test.Consultasnativas;

import ecomerce.dados.test.EntityManagerTest;
import ecommerce.model.ItemPedido;
import ecommerce.model.Produto;
import jakarta.persistence.Query;
import org.junit.jupiter.api.Test;

import java.util.List;

public class consultasNativasTest extends EntityManagerTest {


    @Test
    public void Teste_Executar_sql() {
        String sql = "select id, nome from produto";
        Query query = entityManager.createNativeQuery(sql);

        List<Object[]> lista = query.getResultList();

        lista.stream().forEach(arr -> System.out.println(
                String.format("Produto => ID: %s, Nome: %s", arr[0], arr[1])));
    }

    @Test
    public void Teste_Retornar_Entidade() {
        String sql = "select id, nome, " +
                "            null data_criacao, null data_ultima_atualizacao, " +
                "            preco, null foto " +
                " from produto";
        Query query = entityManager.createNativeQuery(sql, Produto.class);

        List<Produto> lista = query.getResultList();

        lista.stream().forEach(obj -> System.out.println(
                String.format("Produto => ID: %s, Nome: %s", obj.getId(), obj.getNome())));
    }

    @Test
    public void Teste_PassarParametros() {
        String sql = "select prd_id id, prd_nome nome, prd_descricao descricao, " +
                "            prd_data_criacao data_criacao, prd_data_ultima_atualizacao data_ultima_atualizacao, " +
                "            prd_preco preco, prd_foto foto " +
                " from ecm_produto where prd_id = :id";
        Query query = entityManager.createNativeQuery(sql, Produto.class);
        query.setParameter("id", 201);

        List<Produto> lista = query.getResultList();

        lista.stream().forEach(obj -> System.out.println(
                String.format("Produto => ID: %s, Nome: %s", obj.getId(), obj.getNome())));
    }

    @Test
    public void Teste_Usar_Sql_ResultMapping() {
        String sql = "select id, nome, " +
                "            null data_criacao, null data_ultima_atualizacao, " +
                "            preco, null foto " +
                " from produto_loja";
        Query query = entityManager.createNativeQuery(sql, "produto_loja.Produto");

        List<Produto> lista = query.getResultList();

        lista.stream().forEach(obj -> System.out.println(
                String.format("Produto => ID: %s, Nome: %s", obj.getId(), obj.getNome())));
    }

    @Test
    public void Teste_Usar_Sql_ResultMapping02() {
        String sql = "select * from item_pedido ip join produto p on p.id = ip.produto_id";
        Query query = entityManager.createNativeQuery(sql, "item_pedido-produto.ItemPedido-Produto");

        List<Object[]> lista = query.getResultList();

        lista.stream().forEach(arr -> System.out.println(
                String.format("Pedido => ID: %s --- Produto => ID: %s, Nome: %s",
                        ((ItemPedido) arr[0]).getId().getPedidoId(),
                        ((Produto) arr[1]).getId(), ((Produto) arr[1]).getNome())));
    }
      @Test
    public void Teste_Usar_FiltResult() {
        String sql = "select * from ecm_produto";
        Query query = entityManager.createNativeQuery(sql, "ecm_produto.Produto");

        List<Produto> lista = query.getResultList();
        lista.stream().forEach(obj -> System.out.println(
                String.format("Produto => ID: %s, Nome: %s", obj.getId(), obj.getNome())));

    }

    @Test
    public void Teste_Usar_ColumResultDTO() {
        String sql = "select * from ecm_produto";
        Query query = entityManager.createNativeQuery(sql, "ecm_produto.ProdutoDTO");

        List<ProdutoDTO> lista = query.getResultList();
        lista.stream().forEach(obj -> System.out.println(
                String.format("ProdutoDTO => ID: %s, Nome: %s", obj.getId(), obj.getNome())));

    }

    @Test
    public void Teste_Usar_NamedNativeQuery() {
        Query query = entityManager.createNamedQuery("produto_loja.listar");

        List<Produto> lista = query.getResultList();
        lista.stream().forEach(obj -> System.out.println(
                String.format("Produto => ID: %s, Nome: %s", obj.getId(), obj.getNome())));
    }

    @Test
    public void Teste_Usar_NamedNativeQuery02() {
        Query query = entityManager.createNamedQuery("ecm_produto.listar");

        List<Produto> lista = query.getResultList();
        lista.stream().forEach(obj -> System.out.println(
                String.format("Produto => ID: %s, Nome: %s", obj.getId(), obj.getNome())));
    }

    @Test
    public void Teste_Usar_XML() {
        Query query = entityManager.createNamedQuery("ecm_categoria.listar");

        List<Categoria> lista = query.getResultList();
        lista.stream().forEach(obj -> System.out.println(
                String.format("Produto => ID: %s, Nome: %s", obj.getId(), obj.getNome())));
    }
}
