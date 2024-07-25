package ecommerce.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Entity
@Getter
@Setter
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Table(name = "pagamento_cartao")
public class PagamentoCartao {

    @EqualsAndHashCode.Include
    @Id
    private Integer id;


    @Column(name = "pedido_id")
    private Integer pedidoId;

    private StatusPagamento status;


    private String numero;
}
