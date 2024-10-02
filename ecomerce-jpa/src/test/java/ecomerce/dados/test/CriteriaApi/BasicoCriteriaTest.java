package ecomerce.dados.test.CriteriaApi;

import ecomerce.dados.test.EntityManagerTest;
import ecommerce.dto.ProdutoDTO;
import ecommerce.model.Pedido;
import ecommerce.model.Produto;
import jakarta.persistence.Tuple;
import jakarta.persistence.TypedQuery;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.List;

public class BasicoCriteriaTest extends EntityManagerTest {


    @Test
    public void selecionar_Um_Atributo_Para_Retorno() {
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<BigDecimal> criteriaQuery = criteriaBuilder.createQuery(BigDecimal.class);
        Root<Pedido> pedidoRoot = criteriaQuery.from(Pedido.class);

        criteriaQuery.select(pedidoRoot.get("Total"));
        criteriaQuery.where(criteriaBuilder.equal(pedidoRoot.get("id"), 1));


        TypedQuery<BigDecimal> typedQuery = entityManager.createQuery(criteriaQuery);
        BigDecimal bigDecimal = typedQuery.getSingleResult();

        Assertions.assertEquals(new BigDecimal("998.00"), bigDecimal);
    }

    @Test
    public void buscar_Por_Indentificador() {

        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Pedido> criteriaQuery = criteriaBuilder.createQuery(Pedido.class);
        Root<Pedido> pedidoRoot = criteriaQuery.from(Pedido.class);

        criteriaQuery.select(pedidoRoot);
        criteriaQuery.where(criteriaBuilder.equal(pedidoRoot.get("id"), 1));

        TypedQuery<Pedido> typedQuery = entityManager.createQuery(criteriaQuery);
    }

    @Test
    public void retornar_Todos_Os_Produtos_Exercicio() {
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Produto> criteriaQuery = criteriaBuilder.createQuery(Produto.class);
        Root<Produto> root = criteriaQuery.from(Produto.class);

        criteriaQuery.select(root);

        TypedQuery<Produto> typedQuery = entityManager.createQuery(criteriaQuery);
        List<Produto> lista = typedQuery.getResultList();
        Assertions.assertFalse(lista.isEmpty());
    }

    @Test
    public void projetar_Resultado() {
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Object[]> criteriaQuery = criteriaBuilder.createQuery(Object[].class);
        Root<Produto> pedidoRoot = criteriaQuery.from(Produto.class);

        criteriaQuery.multiselect(pedidoRoot.get("id"), pedidoRoot.get("nome"));

        TypedQuery<Object[]> typedQuery = entityManager.createQuery(criteriaQuery);

        List<Object[]> lista = typedQuery.getResultList();
        Assertions.assertFalse(lista.isEmpty());

        lista.forEach(arr -> System.out.println("ID: " + arr[0] + ", Nome: " + arr[1]));
    }


    @Test
    public void projetar_Tuple() {
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Tuple> criteriaQuery = criteriaBuilder.createQuery(Tuple.class);
        Root<Produto> pedidoRoot = criteriaQuery.from(Produto.class);

//        criteriaQuery.multiselect(pedidoRoot.get("id"), pedidoRoot.get("nome"));

        criteriaQuery.select(criteriaBuilder.tuple(pedidoRoot.get("id").alias("id"),
                pedidoRoot.get("nome").alias("nome")));

        TypedQuery<Tuple> typedQuery = entityManager.createQuery(criteriaQuery);

        List<Tuple> lista = typedQuery.getResultList();
        Assertions.assertFalse(lista.isEmpty());

        lista.forEach(tuple -> System.out.println("ID: " + tuple.get("id") + ", Nome: " + tuple.get("nome")));
    }


    @Test
    public void projetar_Resultado_DTO() {
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<ProdutoDTO> criteriaQuery = criteriaBuilder.createQuery(ProdutoDTO.class);
        Root<Produto> pedidoRoot = criteriaQuery.from(Produto.class);

        criteriaQuery.select(criteriaBuilder.construct(ProdutoDTO.class,
                pedidoRoot.get("id"), pedidoRoot.get("nome")));

        TypedQuery<ProdutoDTO> typedQuery = entityManager.createQuery(criteriaQuery);
        List<ProdutoDTO> lista = typedQuery.getResultList();


        Assertions.assertFalse(lista.isEmpty());


        lista.forEach(dto -> System.out.println("ID: " + dto.getId() + ", Nome: " + dto.getNome()));
    }
}
