package ecomerce.dados.test.JPQLbasicoTest;

import ecomerce.dados.test.EntityManagerTest;
import ecommerce.dto.ProdutoDTO;
import ecommerce.model.Cliente;
import ecommerce.model.Pedido;
import jakarta.persistence.Query;
import jakarta.persistence.TypedQuery;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;

public class BasicoJPQLTest extends EntityManagerTest {


    @Test
    public void selecionar_Um_Atributo_Para_Retorno() {
        String jpql = "select p.nome from Produto  p ";

        TypedQuery<String> typedQuery = entityManager.createQuery(jpql, String.class);
        List<String> produtoList = typedQuery.getResultList();
        Assertions.assertTrue(String.class.equals(produtoList.get(0).getClass()));

        String jpqlCliente = "select p.cliente from Pedido p";
        TypedQuery<Cliente> clienteTypedQuery = entityManager.createQuery(jpqlCliente, Cliente.class);
        List<Cliente> clienteList = clienteTypedQuery.getResultList();
        Assertions.assertTrue(Cliente.class.equals(clienteList.get(0).getClass()));


    }

    @Test
    public void projetar_Resultado() {
        String jpql = "select id, nome from Produto p ";

        TypedQuery<Object[]> typedQuery = entityManager.createQuery(jpql, Object[].class);

        List<Object[]> lista = typedQuery.getResultList();

        Assertions.assertTrue(lista.get(0).length == 2);
    }

    @Test
    public void buscar_Por_Indentificador() {

        //entityManager.find(Pedido.class,1)

        TypedQuery<Pedido> pedidoTypedQuery = entityManager.
                createQuery("select p from Pedido p where p.id = 1", Pedido.class);

        Pedido pedido = pedidoTypedQuery.getSingleResult();

        Assertions.assertNotNull(pedido);
    }

    @Test
    public void mostrar_Diferenca_Queries() {
        String jpql = "select p from  Pedido p where p.id = 1";

        TypedQuery<Pedido> pedidoTypedQuery = entityManager.createQuery(jpql, Pedido.class);
        Pedido pedido1 = pedidoTypedQuery.getSingleResult();
        Assertions.assertNotNull(pedido1);


        Query query = entityManager.createQuery(jpql);
        Pedido pedido2 = (Pedido) query.getSingleResult();
        Assertions.assertNotNull(pedido2);
    }


    @Test
    public void projetar_No_DTO() {
        String jpql = "select new ecommerce.dto.ProdutoDTO(id, nome) from Produto p ";

        TypedQuery<ProdutoDTO> typedQuery = entityManager.createQuery(jpql, ProdutoDTO.class);
        List<ProdutoDTO> produtoDTOList = typedQuery.getResultList();

        Assertions.assertFalse(produtoDTOList.isEmpty());

    }
}
