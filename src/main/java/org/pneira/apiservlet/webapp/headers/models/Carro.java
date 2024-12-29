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


@CarroCompra
public class Carro implements Serializable {

    private List<ItemCarro> listItems;


    @Inject
    private transient Logger log;


    public Carro() {

    }

    public List<ItemCarro> getListItems() {
        return listItems;
    }


    @PostConstruct
    public void inicializar(){
        this.listItems = new ArrayList<>();

        log.info( " Inicializandoi el carro de compras");
    }

    @PreDestroy
    public void destruir(){

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
