package ecommerce.Listener;

import jakarta.persistence.PostLoad;

public class GenericoListener {

    @PostLoad
    public void logar(Object obj){
        System.out.println("Entidade " + obj.getClass().getSimpleName() + " foi carregada");
    }
}
