package ecommerce.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
 @NamedNativeQueries({
        @NamedNativeQuery(name = "produto_loja.listar",
                query = "select id, nome, data_criacao, data_ultima_atualizacao, preco, foto from produto_loja",
                resultClass = Produto.class),

        @NamedNativeQuery(name = "ecm_produto.listar", query = "select * from ecm_produto",
                resultSetMapping = "ecm_produto.Produto")
})
@SqlResultSetMappings({
        @SqlResultSetMapping(name = "produto_loja.Produto",
                entities = {@EntityResult(entityClass = Produto.class)}),
        @SqlResultSetMapping(name = "ecm_produto.Produto",
                entities = {@EntityResult(entityClass = Produto.class,
                        fields = {
                                @FieldResult(name = "id", column = "prd_id"),
                                @FieldResult(name = "nome", column = "prd_nome"),
                                @FieldResult(name = "preco", column = "prd_preco"),
                                @FieldResult(name = "foto", column = "prd_foto"),
                                @FieldResult(name = "dataCriacao", column = "prd_data_criacao"),
                                @FieldResult(name = "dataUltimaAtualizacao",
                                        column = "prd_data_ultima_atualizacao")
                        })}),
        @SqlResultSetMapping(name = "ecm_produto.ProdutoDTO", classes = {
                @ConstructorResult(targetClass = ProdutoDTO.class, columns = {
                        @ColumnResult(name = "prd_id", type = Integer.class),
                        @ColumnResult(name = "prd_nome", type = String.class)
                })


        })
})

@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@EntityListeners({GenericoListener.class})
@Entity  
@Table(name = "produto", uniqueConstraints = {@UniqueConstraint(name = "unq_nome", columnNames = {"nome"})},
        indexes = {@Index(name = "idx_nome", columnList = "nome")})
public class Produto {

    @EqualsAndHashCode.Include
    @Id

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @PastOrPresent
    @NotNull
    @Column(name = "data_criacao", updatable = false, nullable = false)
    private LocalDateTime dataCriacao;

    @PastOrPresent
    @Column(name = "data_ultima_atualizacao", insertable = false)
    private LocalDateTime dataUltimaAtualizacao;


    @NotBlank
    @Column(length = 100, nullable = false)
    private String nome;

    @Positive
    @Column(precision = 19, scale = 2)
    private BigDecimal preco;

    @Lob
    @Column(length = 1000)
    private byte[] foto;

    @ManyToMany
    @JoinTable(name = "produto_categoria", joinColumns = @JoinColumn(name = "produto_id"), inverseJoinColumns = @JoinColumn(name = "categoria_id", nullable = false,
            foreignKey = @ForeignKey(name = "fk_produto_categorias")))
    private List<Categoria> categorias;

    @OneToOne(mappedBy = "produto")
    private Estoque estoque;

    @ElementCollection
    @CollectionTable(name = "produto_tag", joinColumns = @JoinColumn(name = "produto_id", nullable = false, foreignKey = @ForeignKey(name = "fk_produto_tag")))
    @Column(name = "tag")
    private List<String> tags;

    @ElementCollection
    @CollectionTable(name = "produto_atributo", joinColumns = @JoinColumn(name = "produto_id", nullable = false,
            foreignKey = @ForeignKey(name = "fk_produto_atributo_atributo")))
    private List<Atributo> atributos;
}

