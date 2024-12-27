package org.pneira.apiservlet.webapp.headers.models;

import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import jakarta.inject.Inject;
import org.pneira.apiservlet.webapp.headers.configs.CarroCompra;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.logging.Logger;

// conoexion poara cada usuario
@CarroCompra
public class Carro implements Serializable {

    private List<ItemCarro> listItems;

    /**  Carro es clase de SESSIONscoped y implementa serializable
     * en cambio Logger no se puede guardar en la session y no es serializable : no es compatible
     *  entonces con Transient injectamos Log pero no forma parte de la session del carrro, pero si podemos
     *  usar para imprimior, */
    @Inject
    private transient Logger log;

    /** ToDo :  tiene q tener un consutructor vacio buena tactica */
    public Carro() {

    }

    public List<ItemCarro> getListItems() {
        return listItems;
    }

    /** es como un Cosntructor, se recomienda en vez de usar el constructor */
    @PostConstruct
    public void inicializar(){
        this.listItems = new ArrayList<>();
        //System.out.println( " Inicializandoi el carro de compras");
        log.info( " Inicializandoi el carro de compras");
    }

    @PreDestroy
    public void destruir(){
       // System.out.println( " destruyendo el carro de Compras");
        log.info( " destruyendo el carro de compras");
    }
    public void addItemCarro(ItemCarro item){
        if ( listItems.contains( item)){
           Optional<ItemCarro> p = listItems.stream().filter(x -> x.equals(item)).findFirst();
           p.get().setCantidad( p.get().getCantidad() + 1 );
        }else{
            this.listItems.add(item);
        }

    }
    public int getTotal(){
        return listItems.stream().mapToInt(ItemCarro::getImporte).sum();
    }
}
