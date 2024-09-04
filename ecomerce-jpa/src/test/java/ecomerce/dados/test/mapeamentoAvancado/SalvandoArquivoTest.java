package ecomerce.dados.test.mapeamentoAvancado;

import ecomerce.dados.test.EntityManagerTest;
import ecommerce.model.NotaFiscal;
import ecommerce.model.Pedido;
import ecommerce.model.Produto;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.Date;

public class SalvandoArquivoTest extends EntityManagerTest {


    @Test
    public void salvar_Foto_Produto() {
        entityManager.getTransaction().begin();
        Produto produto = entityManager.find(Produto.class, 1);
        produto.setFoto(carregarFoto());
        entityManager.getTransaction().commit();

        entityManager.clear();

        Produto produtoVerificacao = entityManager.find(Produto.class, 1);
        Assertions.assertNotNull(produtoVerificacao.getFoto());
        Assertions.assertTrue(produtoVerificacao.getFoto().length > 0);
    }

    @Test
    public void salvar_Xml_Nota() {
        Pedido pedido = entityManager.find(Pedido.class, 1);

        NotaFiscal notaFiscal = new NotaFiscal();
        notaFiscal.setPedido(pedido);
        notaFiscal.setDataEmissao(new Date());
        notaFiscal.setXml(carregarNotaFiscal());

        entityManager.getTransaction().begin();
        entityManager.persist(notaFiscal);
        entityManager.getTransaction().commit();

        entityManager.clear();

        NotaFiscal notaFiscalVerificacao = entityManager.find(NotaFiscal.class, notaFiscal.getId());
        Assertions.assertNotNull(notaFiscalVerificacao.getXml());
        Assertions.assertTrue(notaFiscalVerificacao.getXml().length > 0);

        /*
        try {
            OutputStream out = new FileOutputStream(
                    Files.createFile(Paths.get(
                            System.getProperty("user.home") + "/xml.xml")).toFile());
            out.write(notaFiscalVerificacao.getXml());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        */
    }

    private static byte[] carregarNotaFiscal() {
        try {
            return SalvandoArquivoTest.class.getResourceAsStream(
                    "/nota-fiscal.xml").readAllBytes();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private static byte[] carregarFoto() {
        return carregarArquivo("/logo.svg");
    }

    private static byte[] carregarArquivo(String nome) {
        try {
            return SalvandoArquivoTest.class.getResourceAsStream(nome).readAllBytes();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
