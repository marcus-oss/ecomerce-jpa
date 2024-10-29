package ecommerce.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@DiscriminatorColumn(name = "tipo_pagamento", discriminatorType = DiscriminatorType.STRING)
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@Table(name = "pagamento")
public abstract class Pagamento  {

    @Id
    private Integer id;

    @Version
    private Integer versao;


    
    @MapsId
    @NotNull
    @OneToOne(optional = false)
    @JoinColumn(name = "pedido_id",  nullable = false, foreignKey = @ForeignKey(name = "fk_pagamaento_pedido"))
    private Pedido pedido;

    
    @NotNull
    @Enumerated(EnumType.STRING)
    private StatusPagamento status;
}
