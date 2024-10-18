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
@NamedEntityGraphs({
        @NamedEntityGraph(
                name = "Pedido.dadosEssenciais",
                attributeNodes = {
                        @NamedAttributeNode("dataConclusaoPedido"),
                        @NamedAttributeNode("status"),
                        @NamedAttributeNode("Total"),
                        @NamedAttributeNode(
                                value = "cliente",
                                subgraph = "cli"
                        )
                },
                subgraphs = {
                        @NamedSubgraph(name = "cli",
                                attributeNodes = {
                                        @NamedAttributeNode("nome"),
                                        @NamedAttributeNode("cpf")
                                }
                        )
                }
        )
})

@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Table(name = "pedido")
public class Pedido  extends EntidadeBaseInteger {

    @NotNull
    @ManyToOne(optional = false)
    @JoinColumn(name = "cliente_id", nullable = false, foreignKey = @ForeignKey(name = "fk_pedido_cliente"))
    private Cliente cliente;


    @PastOrPresent
    @Column(name = "data_pedido", updatable = false)
    private LocalDateTime data_Ultimo_Pedido;


    @PastOrPresent
    @Column(name = "data_conclusao_pedido", insertable = false)
    private LocalDateTime dataConclusaoPedido;

    @OneToOne(mappedBy = "pedido")
    private NotaFiscal notaFiscal;

    @NotNull
    @Column(nullable = false)
    private BigDecimal Total;


    @NotNull
    @Column(length = 30, nullable = false)
    @Enumerated(EnumType.STRING)
    private StatusPedido status;

    @Embedded
    private EnderecoEntregaPedido entregaPedido;


    @NotEmpty
    @OneToMany(mappedBy = "pedido")
    private List<ItemPedido> itens;


    @OneToOne(mappedBy = "pedido")
    private Pagamento pagamento;

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
