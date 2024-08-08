package ecommerce.Listener;

import ecommerce.Services.NotaFiscalServices;
import ecommerce.model.Pedido;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;

public class GerarNotaFiscalListener {


    private NotaFiscalServices notaFiscalServices = new NotaFiscalServices();

    @PrePersist
    @PreUpdate
    public void gerar(Pedido pedido) {
        if (pedido.isPago() && pedido.getNotaFiscal() == null) {
            notaFiscalServices.gerar(pedido);
        }
    }
}
