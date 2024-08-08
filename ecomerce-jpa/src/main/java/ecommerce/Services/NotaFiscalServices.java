package ecommerce.Services;

import ecommerce.model.Pedido;

public class NotaFiscalServices {

    public void gerar(Pedido pedido) {
        System.out.println("Gerando nota para o pedido " + pedido.getId() + " . ");
    }
}
