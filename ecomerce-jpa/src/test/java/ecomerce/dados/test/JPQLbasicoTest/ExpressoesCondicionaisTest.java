package ecomerce.dados.test.JPQLbasicoTest;

import ecomerce.dados.test.EntityManagerTest;
import ecommerce.model.Pedido;
import ecommerce.model.Produto;
import jakarta.persistence.TypedQuery;
import net.bytebuddy.asm.Advice;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

public class ExpressoesCondicionaisTest extends EntityManagerTest {

    @Test
    public void expressao_Like() {
        String jqpl = "select c from Cliente c where c.nome like concat  ('%',:nome, '%')";

        TypedQuery<Object[]> typedQuery = entityManager.createQuery(jqpl, Object[].class);
        typedQuery.setParameter("nome", "a");

        List<Object[]> likeList = typedQuery.getResultList();
        Assertions.assertFalse(likeList.isEmpty());


    }

    @Test
    public void expressao_IsNull() {
        String jqpl = "select p from Produto p where p.foto is null";

        TypedQuery<Object[]> typedQuery = entityManager.createQuery(jqpl, Object[].class);

        List<Object[]> likeList = typedQuery.getResultList();
        Assertions.assertFalse(likeList.isEmpty());


    }


    @Test
    public void expressao_IsEmpty() {
        String jqpl = "select  p from  Produto p where p.categorias is  not empty";

        TypedQuery<Object[]> typedQuery = entityManager.createQuery(jqpl, Object[].class);

        List<Object[]> likeList = typedQuery.getResultList();
        Assertions.assertFalse(likeList.isEmpty());


    }


    @Test
    public void expressao_Maior_Menor() {
        String jqpl = "select p from Produto p  where p.preco >= :precoInicial " +
                "and  p.preco <= :precoFinal";

        TypedQuery<Object[]> typedQuery = entityManager.createQuery(jqpl, Object[].class);
        typedQuery.setParameter("precoInicial", new BigDecimal(400));
        typedQuery.setParameter("precoFinal", new BigDecimal(1500));

        List<Object[]> likeList = typedQuery.getResultList();
        Assertions.assertFalse(likeList.isEmpty());


    }


    @Test
    public void exercio_Com_Datas() {
        String jpql = "select p from Pedido p where" +
                " p.data_Ultimo_Pedido > :data";

        TypedQuery<Pedido> typedQuery = entityManager.createQuery(jpql, Pedido.class);
        typedQuery.setParameter("data", LocalDateTime.now().minusDays(2));

        List<Pedido> lista = typedQuery.getResultList();
        Assertions.assertFalse(lista.isEmpty());
    }


    @Test
    public void expressao_Bettwen() {
        String jqpl = "select p from Pedido p  " +
                "where p.data_Ultimo_Pedido between :dataInicial and :dataFinal";

        TypedQuery<Pedido> typedQuery = entityManager.createQuery(jqpl, Pedido.class);
        typedQuery.setParameter("dataInicial", LocalDateTime.now().minusDays(2));
        typedQuery.setParameter("dataFinal", LocalDateTime.now());

        List<Pedido> betweenList = typedQuery.getResultList();
        Assertions.assertFalse(betweenList.isEmpty());


    }


    @Test
    public void expressao_Diferente() {
        String jqpl = "select  p from  Produto p where p.id <> 100";

        TypedQuery<Produto> typedQuery = entityManager.createQuery(jqpl, Produto.class);

        List<Produto> produtoList = typedQuery.getResultList();
        Assertions.assertFalse(produtoList.isEmpty());


    }
}
