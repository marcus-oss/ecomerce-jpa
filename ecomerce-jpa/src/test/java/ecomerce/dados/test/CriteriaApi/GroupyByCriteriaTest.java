package ecomerce.dados.test.CriteriaApi;

import ecomerce.dados.test.EntityManagerTest;
import ecommerce.model.*;
import jakarta.persistence.TypedQuery;
import jakarta.persistence.criteria.*;
import org.junit.jupiter.api.Test;

import java.util.List;

public class GroupyByCriteriaTest extends EntityManagerTest {


    @Test
    public void agrupar_Resultado_GroupyBy() {
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Object[]> criteriaQuery = criteriaBuilder.createQuery(Object[].class);
        Root<Categoria> root = criteriaQuery.from(Categoria.class);
        Join<Categoria, Produto> categoriaProdutoJoin = root.join(Categoria_.produtos, JoinType.LEFT);

        criteriaQuery.multiselect(
                root.get(Categoria_.nome),
                criteriaBuilder.count(categoriaProdutoJoin.get(Produto_.id))

        );

        criteriaQuery.groupBy(root.get(Categoria_.id));

        TypedQuery<Object[]> typedQuery = entityManager.createQuery(criteriaQuery);
        List<Object[]> lista = typedQuery.getResultList();


        lista.forEach(arr -> System.out.println("Nome: " + arr[0] + ", Count: " + arr[1]));
    }

    @Test
    public void agrupar_Resultado_GroupyBy_02() {
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Object[]> criteriaQuery = criteriaBuilder.createQuery(Object[].class);
        Root<ItemPedido> root = criteriaQuery.from(ItemPedido.class);
        Join<ItemPedido, Produto> produtoJoin = root.join(ItemPedido_.produto);
        Join<Produto, Categoria> categoriaJoin = produtoJoin.join(Produto_.categorias);

        criteriaQuery.multiselect(
                categoriaJoin.get(Categoria_.nome),
                criteriaBuilder.count(root.get(ItemPedido_.precoProduto))
        );

        criteriaQuery.groupBy(categoriaJoin.get(Categoria_.id));

        TypedQuery<Object[]> typedQuery = entityManager.createQuery(criteriaQuery);
        List<Object[]> lista = typedQuery.getResultList();


        lista.forEach(arr -> System.out.println("Nome categoria: " + arr[0] + ", Sum: " + arr[1]));
    }

    @Test
    public void agrupar_Resultado_Com_Funcoes() {
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Object[]> criteriaQuery = criteriaBuilder.createQuery(Object[].class);
        Root<Pedido> root = criteriaQuery.from(Pedido.class);

        Expression<Integer> anoCriacaoPedido = criteriaBuilder
                .function("year", Integer.class, root.get(Pedido_.DATA_CONCLUSAO_PEDIDO));
        Expression<Integer> mesCriacaoPedido = criteriaBuilder
                .function("month", Integer.class, root.get(Pedido_.DATA_CONCLUSAO_PEDIDO));
        Expression<String> nomeMesCriacaoPedido = criteriaBuilder
                .function("monthname", String.class, root.get(Pedido_.dataConclusaoPedido));

        Expression<String> anoMesConcat = criteriaBuilder.concat(
                criteriaBuilder.concat(anoCriacaoPedido.as(String.class), "/"),
                nomeMesCriacaoPedido
        );

        criteriaQuery.multiselect(
                anoMesConcat,
                criteriaBuilder.sum(root.get(Pedido_.TOTAL))
        );

        criteriaQuery.groupBy(anoCriacaoPedido, mesCriacaoPedido);

        TypedQuery<Object[]> typedQuery = entityManager.createQuery(criteriaQuery);
        List<Object[]> lista = typedQuery.getResultList();

        lista.forEach(arr -> System.out.println("Ano/Mês: " + arr[0] + ", Sum: " + arr[1]));
    }

}
