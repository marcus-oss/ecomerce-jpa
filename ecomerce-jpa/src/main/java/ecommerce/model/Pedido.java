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
@EntityListeners({GerarNotaFiscalListener.class, GenericoListener.class})
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Table(name = "pedido")
public class Pedido {

    @EqualsAndHashCode.Include
    @Id

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne(optional = false)
    @JoinColumn(name = "cliente_id", nullable = false, foreignKey = @ForeignKey(name = "fk_pedido_cliente"))
    private Cliente cliente;

    // @Column(name = "data_pedido")
    // private LocalDateTime dataPedido;

    @Column(name = "data_pedido",updatable = false)
    private LocalDateTime data_Ultimo_Pedido;

    
    @Column(name = "data_conclusao_pedido",insertable = false)
    private LocalDateTime dataConclusaoPedido;

    @OneToOne(mappedBy = "pedido")
    private NotaFiscal notaFiscal;

    private BigDecimal Total;

    @Enumerated(EnumType.STRING)
    private StatusPedido status;

    @OneToOne(mappedBy = "pedido")
    private Pagamento pagamento;
    
    @Embedded
    private EnderecoEntregaPedido entregaPedido;

      @PrePersist
    public void aoPersistir() {
        dataConclusaoPedido = LocalDateTime.now();
        calcularTotal();
    }

    @PreUpdate
    public void aoAtualizar() {
        data_Ultimo_Pedido = LocalDateTime.now();
        calcularTotal();
    }


  public void calcularTotal() {
        if (itens != null) {
            Total = itens.stream().map(i -> new BigDecimal(i.getQuantidade()).multiply(i.getPrecoProduto()))
                    .reduce(BigDecimal.ZERO, BigDecimal::add);
        } else {
            Total = BigDecimal.ZERO;
        }
    }

    public boolean isPago() {
        return StatusPedido.PAGO.equals(status);
    }

}
