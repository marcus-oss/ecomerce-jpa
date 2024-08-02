package ecommerce.model;

import jakarta.persistence.*;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Table(name = "pedido")
public class Pedido {

    @EqualsAndHashCode.Include
    @Id

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne(optional = false)
    @JoinColumn(name = "cliente_id")
    private Cliente cliente;

    @Column(name = "data_pedido")
    private LocalDateTime dataPedido;

    @Column(name = "data_conclusao_pedido")
    private LocalDateTime dataConclusaoPedido;

    @OneToOne(mappedBy = "pedido")
    private NotaFiscal notaFiscal;

    private BigDecimal Total;

    @Enumerated(EnumType.STRING)
    private StatusPedido status;

    @OneToOne(mappedBy = "pedido")
    private PagamentoCartao pagamentoCartao;
    
    @Embedded
    private EnderecoEntregaPedido entregaPedido;

}
