package ecommerce.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Table(name = "pagamento_boleto")
public class PagamentoBoleto {

    @EqualsAndHashCode.Include
    @Id
    private Integer id;


    @Column(name = "pedido_id")
    private Integer pedidoId;

    private StatusPagamento status;


    @Column(name = "codigo_barras")
    private String codigoBarras;
}
