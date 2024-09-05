package ecommerce.model;

import jakarta.persistence.Column;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@DiscriminatorValue("cartao")
//@Table(name = "pagamento_cartao")
public class PagamentoCartao extends Pagamento {


    @Column(name = "numero_cartao")
    private String numerocartao;
}
