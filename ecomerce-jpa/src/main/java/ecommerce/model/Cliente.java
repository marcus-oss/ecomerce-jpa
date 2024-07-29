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
    private Integer id;

    @Enumerated(EnumType.STRING)
    private String nome;

}
