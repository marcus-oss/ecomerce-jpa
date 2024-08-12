package ecommerce.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.Date;

@Entity
@Getter
@Setter
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Table(name = "nota_fiscal")
public class NotaFiscal {

    @EqualsAndHashCode.Include
    @Column(name = "pedido_id")
    @Id
    private Integer id;


    @MapsId
    @OneToOne(optional = false)
    @JoinColumn(name = "pedido_id")
//    @JoinTable(name = "pedido_nota_fiscal", joinColumns = @JoinColumn(name = "nota_fiscal_id", unique = true),
//            inverseJoinColumns = @JoinColumn(name = "pedido_id",  unique = true))
   private Pedido pedido;

    private String xml;

    @Column(name = "data_emissao")
    private Date dataEmissao;


}
