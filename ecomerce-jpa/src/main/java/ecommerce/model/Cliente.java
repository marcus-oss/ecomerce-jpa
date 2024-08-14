package ecommerce.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
 @Table(name = "cliente")   
public class Cliente {

    @EqualsAndHashCode.Include
    @Id

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String nome;

    @Transient
    private String primeironome;
   
    @Enumerated(EnumType.STRING)
    private SexoCliente sexo;

  @PostLoad
    public void configurarPrimeiroNome() {

        if (nome != null && !nome.isBlank()) {
            int index = nome.indexOf(" ");
            if (index > -1) {
                primeironome = nome.substring(0, index);

            }
        }

}
