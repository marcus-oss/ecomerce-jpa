package ecommerce.model;

import jakarta.persistence.Embeddable;
import jakarta.persistence.JoinColumn;
import lombok.*;

import java.io.Serializable;

@Getter
@Setter
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@NoArgsConstructor
@AllArgsConstructor
@Embeddable
public class PagamentoCartaoId implements Serializable {


    @EqualsAndHashCode.Include
    @JoinColumn(name = "pedido_id")
    private Pedido pedidoId;


}
