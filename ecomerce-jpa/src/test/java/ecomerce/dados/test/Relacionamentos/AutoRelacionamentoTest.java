package Relacionamentos;

import ecomerce.dados.test.EntityManagerTest;
import ecommerce.model.Categoria;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;


public class AutoRelacionamentoTest extends EntityManagerTest {

    @Test
    public void verificar_Relacionamento_Categoria() {
        Categoria categoriaPai = new Categoria();
        categoriaPai.setNome("Eletronicos");


        Categoria categoria = new Categoria();
        categoria.setNome("Celulares");
        categoria.setCategoriaPai(categoriaPai);


        entityManager.getTransaction().begin();
        entityManager.persist(categoriaPai);
        entityManager.persist(categoria);
        entityManager.getTransaction().commit();

        entityManager.clear();

        Categoria categoriaVerificacao = entityManager.find(Categoria.class, categoria.getId());
        Assertions.assertNotNull(categoriaVerificacao.getCategoriaPai());

        Categoria categoriaVerificacaoPai = entityManager.find(Categoria.class, categoriaPai.getId());
        Assertions.assertFalse(categoriaVerificacaoPai.getCategorias().isEmpty());
    }
}
