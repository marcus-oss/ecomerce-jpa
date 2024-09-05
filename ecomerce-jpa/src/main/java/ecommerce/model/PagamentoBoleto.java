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


    @Column(name = "codigo_barras")
    private String codigoBarras;
}

