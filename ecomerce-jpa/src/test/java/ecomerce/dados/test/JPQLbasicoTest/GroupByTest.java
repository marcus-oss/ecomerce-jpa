package ecomerce.dados.test.JPQLbasicoTest;

import ecomerce.dados.test.EntityManagerTest;
import jakarta.persistence.TypedQuery;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;

public class GroupByTest extends EntityManagerTest {


    @Test
    public void agrupar_Resultado() {
        //quantidade de produtos da categoria
        //select c.nome, count(p.id) from Categoria c join c.produtos p group by c.id

        String jqpl = "select c.nome, sum(ip.precoProduto) from ItemPedido ip join ip.produto pro join " +
                " pro.categorias c group by c.id";


        TypedQuery<Object[]> typedQuery = entityManager.createQuery(jqpl, Object[].class);
        List<Object[]> objectList = typedQuery.getResultList();

        Assertions.assertFalse(objectList.isEmpty());

        objectList.forEach(arr -> System.out.println(arr[0] + " , " + arr));
    }


    @Test
    public void exercio_group_by() {
        //tptaç de vendas por mes
        String jqpl = "select concat(year(p.dataConclusaoPedido), function('monthname', p.dataConclusaoPedido)), sum(p.Total)  " +
                " from Pedido p " +
                " group by concat(year(p.dataConclusaoPedido), " +
                " function('monthname', p.dataConclusaoPedido)), year(p.dataConclusaoPedido), month(p.dataConclusaoPedido) ";

        //vendas por dia e por categoria
//        String jpql = "select " +
//                " concat(year(p.dataConclusaoPedido), month(p.dataConclusaoPedido), day(p.dataConclusaoPedido)), " +
//                " concat(c.nome, ': ', sum(ip.precoProduto)) " +
//                " from ItemPedido ip join ip.pedido p join ip.produto pro join pro.categorias c " +
//                " group by concat(year(p.dataConclusaoPedido), month(p.dataConclusaoPedido), day(p.dataConclusaoPedido)), c.id " +
//                " order by concat(year(p.dataConclusaoPedido), month(p.dataConclusaoPedido), day(p.dataConclusaoPedido)), c.nome ";

        TypedQuery<Object[]> typedQuery = entityManager.createQuery(jqpl, Object[].class);
        List<Object[]> objectList = typedQuery.getResultList();

        Assertions.assertFalse(objectList.isEmpty());

        objectList.forEach(arr -> System.out.println(arr[0] + " , " + arr));
    }

}
