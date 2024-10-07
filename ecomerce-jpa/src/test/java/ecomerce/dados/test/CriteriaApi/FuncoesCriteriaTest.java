package ecomerce.dados.test.CriteriaApi;

import ecomerce.dados.test.EntityManagerTest;
import ecommerce.model.*;
import jakarta.persistence.TypedQuery;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Join;
import jakarta.persistence.criteria.Root;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;

public class FuncoesCriteriaTest extends EntityManagerTest {


    @Test
    public void aplicar_Funcao_String() {
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Object[]> criteriaQuery = criteriaBuilder.createQuery(Object[].class);
        Root<Cliente> root = criteriaQuery.from(Cliente.class);

        criteriaQuery.multiselect(
                root.get(Cliente_.nome),
                criteriaBuilder.concat("Nome do cliente: ", root.get(Cliente_.nome)),
                criteriaBuilder.length(root.get(Cliente_.nome)),
                criteriaBuilder.locate(root.get(Cliente_.nome), "a"),
                criteriaBuilder.substring(root.get(Cliente_.nome), 1, 2),
                criteriaBuilder.lower(root.get(Cliente_.nome)),
                criteriaBuilder.upper(root.get(Cliente_.nome)),
                criteriaBuilder.trim(root.get(Cliente_.nome))
        );

        criteriaQuery.where(criteriaBuilder.equal(
                criteriaBuilder.substring(root.get(Cliente_.nome), 1, 1), "M"));

        TypedQuery<Object[]> typedQuery = entityManager.createQuery(criteriaQuery);

        List<Object[]> lista = typedQuery.getResultList();
        Assertions.assertFalse(lista.isEmpty());

        lista.forEach(arr -> System.out.println(
                arr[0]
                        + ", concat: " + arr[1]
                        + ", length: " + arr[2]
                        + ", locate: " + arr[3]
                        + ", substring: " + arr[4]
                        + ", lower: " + arr[5]
                        + ", upper: " + arr[6]
                        + ", trim: |" + arr[7] + "|"));
    }


    @Test
    public void aplicar_Funcao_Data_() {
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Object[]> criteriaQuery = criteriaBuilder.createQuery(Object[].class);
        Root<Pedido> root = criteriaQuery.from(Pedido.class);
        Join<Pedido, Pagamento> pagamentoJoin = root.join(Pedido_.PAGAMENTO);
        Join<Pedido, PagamentoBoleto> boletoPagamentoJoin = criteriaBuilder.treat(pagamentoJoin, PagamentoBoleto.class);
        criteriaQuery.multiselect(
                root.get(Pedido_.ID),
                criteriaBuilder.currentDate(),
                criteriaBuilder.currentTime(),
                criteriaBuilder.currentTimestamp()
        );

        criteriaQuery.where(criteriaBuilder.between(criteriaBuilder.currentDate(), root.get(Pedido_.data_Ultimo_Pedido)
                                .as(java.sql.Date.class),
                        boletoPagamentoJoin.get(PagamentoBoleto_.data_Vencimento)
                                .as(java.sql.Date.class)),
                criteriaBuilder.equal(root.get(Pedido_.status), StatusPedido.AGUARDANDO)
        );


        TypedQuery<Object[]> typedQuery = entityManager.createQuery(criteriaQuery);

        List<Object[]> lista = typedQuery.getResultList();
        Assertions.assertFalse(lista.isEmpty());

        lista.forEach(arr -> System.out.println(
                arr[0]
                        + ", current_date: " + arr[1]
                        + ", current_time: " + arr[2]
                        + ", current_Timestamp: " + arr[3]
                        + "|"));
    }


    @Test
    public void aplicar_FuncaoNumero() {
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Object[]> criteriaQuery = criteriaBuilder.createQuery(Object[].class);
        Root<Pedido> root = criteriaQuery.from(Pedido.class);

        criteriaQuery.multiselect(
                root.get(Pedido_.id),
                criteriaBuilder.abs(criteriaBuilder.prod(root.get(Pedido_.id), -1)),
                criteriaBuilder.mod(root.get(Pedido_.id), 2),
                criteriaBuilder.sqrt(root.get(Pedido_.TOTAL))
        );

        criteriaQuery.where(criteriaBuilder.greaterThan(
                criteriaBuilder.sqrt(root.get(Pedido_.TOTAL)), 10.0));

        TypedQuery<Object[]> typedQuery = entityManager.createQuery(criteriaQuery);

        List<Object[]> lista = typedQuery.getResultList();
        Assertions.assertFalse(lista.isEmpty());

        lista.forEach(arr -> System.out.println(
                arr[0]
                        + ", abs: " + arr[1]
                        + ", mod: " + arr[2]
                        + ", sqrt: " + arr[3]));
    }

    @Test
    public void aplicar_Funcao_Colecao() {
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Object[]> criteriaQuery = criteriaBuilder.createQuery(Object[].class);
        Root<Pedido> root = criteriaQuery.from(Pedido.class);

        criteriaQuery.multiselect(
                root.get(Pedido_.id),
                criteriaBuilder.size(root.get(Pedido_.ITENS))
        );

        criteriaQuery.where(criteriaBuilder.greaterThan(
                criteriaBuilder.size(root.get(Pedido_.ITENS)), 1));

        TypedQuery<Object[]> typedQuery = entityManager.createQuery(criteriaQuery);

        List<Object[]> lista = typedQuery.getResultList();
        Assertions.assertFalse(lista.isEmpty());

        lista.forEach(arr -> System.out.println(
                arr[0]
                        + ", size: " + arr[1]));
    }
}
