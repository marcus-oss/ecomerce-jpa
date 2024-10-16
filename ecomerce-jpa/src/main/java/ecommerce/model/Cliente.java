package ecommerce.model;

import jakarta.persistence.*;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

@Entity
@Getter
@Setter
@NamedStoredProcedureQuery(name = "compraram_acima_media", procedureName = "compraram_acima_media",
        parameters = {

                @StoredProcedureParameter(name = "ano", type = Integer.class, mode = ParameterMode.IN)
        },
        resultClasses = Cliente.class
)
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@SecondaryTable(name = "cliente_detalhe", pkJoinColumns = @PrimaryKeyJoinColumn(name = "cliente_id"), foreignKey = @ForeignKey(name = "fk_cliente_detalhe_cliente"))
@Table(name = "cliente", uniqueConstraints = {@UniqueConstraint(name = "unq_cpf", columnNames = {"cpf"})},
        indexes = {@Index(name = "idx_nome", columnList = "nome")})
public class Cliente extends EntidadeBaseInteger {

   
    @Transient
    private String primeironome;

    @NotBlank
    @Pattern(regexp = "(^\\d{3}\\x2E\\d{3}\\x2E\\d{3}\\x2D\\d{2}$)")
    @Column(length = 14, nullable = false)
    private String cpf;

    @NotBlank
    @Column(length = 100, nullable = false)
    private String nome;

    @NotNull
    @Column(table = "cliente_detalhe")
    @Enumerated(EnumType.STRING)
    private SexoCliente sexo;

    @Column(name = "data_nascimento", table = "cliente_detalhe")
    private LocalDate dataNascimento;

    @ElementCollection
    @CollectionTable(name = "cliente_contato", joinColumns = @JoinColumn(name = "cliente_id"))
    @MapKeyColumn(name = "tipo")
    @Column(name = "descricao")
    private Map<String, String> contatos;


    @OneToMany(mappedBy = "cliente")
    private List<Pedido> pedidos;

    @PostLoad
    public void configurarPrimeiroNome() {

        if (nome != null && !nome.isBlank()) {
            int index = nome.indexOf(" ");
            if (index > -1) {
                primeironome = nome.substring(0, index);

            }
        }

    }
}

