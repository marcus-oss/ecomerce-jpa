package ecommerce.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
//@Table(name = "pagamento_boleto")
@DiscriminatorValue("boleto")
public class PagamentoBoleto extends Pagamento {


    @Column(name = "codigo_barras",length = 100)
    private String codigoBarras;

    
    @Column(name = "data_vencimento")
    private LocalDate data_Vencimento;
}

