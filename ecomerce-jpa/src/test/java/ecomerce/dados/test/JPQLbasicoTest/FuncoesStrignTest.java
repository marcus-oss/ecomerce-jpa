package ecomerce.dados.test.JPQLbasicoTest;

import ecomerce.dados.test.EntityManagerTest;
import ecommerce.model.Pedido;
import jakarta.persistence.TypedQuery;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;

public class FuncoesStrignTest extends EntityManagerTest {

    @Test
    public void aplicar_Funcao_String() {


        String jqpl = "select c.nome, length (c.nome) from Categoria c  where length (c.nome) > 10";

        TypedQuery<Object[]> typedQuery = entityManager.createQuery(jqpl, Object[].class);
        List<Object[]> objectList = typedQuery.getResultList();

        Assertions.assertFalse(objectList.isEmpty());

        objectList.forEach(arr -> System.out.println(arr[0] + " - " + arr[1]));
    }

    @Test
    public void aplicar_Funcao_Data() {
        //    TimeZone.setDefault(TimeZone.getTimeZone("UTC"));
        //year(p.dataConclusaoPedido),month(p.data_Ultimo_Pedido)," + " day(p.data_Ultimo_Pedido)


        String jqpl = "select hour(p.dataConclusaoPedido), minute(p.dataConclusaoPedido), second(p.dataConclusaoPedido) " +
                " from Pedido p ";

        TypedQuery<Object[]> typedQuery = entityManager.createQuery(jqpl, Object[].class);
        List<Object[]> objectList = typedQuery.getResultList();

        Assertions.assertFalse(objectList.isEmpty());

        objectList.forEach(arr -> System.out.println(arr[0] + " - " + arr[1]));
    }

    @Test
    public void aplicar_Funcao_Numeros() {


        String jqpl = "select abs(p.Total), mod(p.id, 2), sqrt(p.Total) from Pedido p " +
                " where abs(p.Total) > 100";

        TypedQuery<Object[]> typedQuery = entityManager.createQuery(jqpl, Object[].class);
        List<Object[]> objectList = typedQuery.getResultList();

        Assertions.assertFalse(objectList.isEmpty());

        objectList.forEach(arr -> System.out.println(arr[0] + " - " + arr[1]));
    }


    @Test
    public void aplicar_Funcao_Colecao() {


        String jqpl = "select size(p.itens) from Pedido p where size(p.itens) > 0";

        TypedQuery<Object[]> typedQuery = entityManager.createQuery(jqpl, Object[].class);
        List<Object[]> objectList = typedQuery.getResultList();

        Assertions.assertFalse(objectList.isEmpty());

        objectList.forEach(size -> System.out.println(size));
    }

    @Test
    public void aplicar_Funcoes_Nativas() {

        String jqpl = "select  function('dayname', p.dataConclusaoPedido)  from Pedido p where function('acima_media_faturamento', p.Total) = 1";

        TypedQuery<String> typedQuery = entityManager.createQuery(jqpl, String.class);
        List<String> objectList = typedQuery.getResultList();

        Assertions.assertFalse(objectList.isEmpty());

        objectList.forEach(obj -> System.out.println(obj));
    }

    @Test
    public void aplicar_Funcoes_Agregacao() {

        String jqpl = "select sum(p.dataConclusaoPedido) from Pedido p ";

        TypedQuery<Number> typedQuery = entityManager.createQuery(jqpl, Number.class);
        List<Number> objectList = typedQuery.getResultList();

        Assertions.assertFalse(objectList.isEmpty());

        objectList.forEach(obj -> System.out.println(obj));
    }


}
