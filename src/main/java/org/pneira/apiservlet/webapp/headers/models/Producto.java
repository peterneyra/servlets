package org.pneira.apiservlet.webapp.headers.models;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class Producto {

    private Long id;
    private String nombre;
    private Categoria categoria;
    private int precio;
    private String sku;
    private LocalDate fechaRegistro;

    public Producto() {
    }

    public Producto(Long id, String nombre,String tipo, int precio) {
        this.id = id;
        this.nombre = nombre;
        this.categoria = new Categoria(tipo);
        this.precio = precio;
    }

    public Producto(Long id, String nombre, Categoria categoria, int precio, String sku, LocalDate fechaRegistro) {
        this.id = id;
        this.nombre = nombre;
        this.categoria = categoria;
        this.precio = precio;
        this.sku = sku;
        this.fechaRegistro = fechaRegistro;
    }
    public Producto( String nombre, Categoria categoria, int precio, String sku, LocalDate fechaRegistro) {
        this.nombre = nombre;
        this.categoria = categoria;
        this.precio = precio;
        this.sku = sku;
        this.fechaRegistro = fechaRegistro;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getPrecio() {
        return precio;
    }

    public void setPrecio(int precio) {
        this.precio = precio;
    }

    public Categoria getCategoria() {
        return categoria;
    }

    public void setCategoria(Categoria categoria) {
        this.categoria = categoria;
    }

    public String getSku() {
        return sku;
    }

    public void setSku(String sku) {
        this.sku = sku;
    }

    public LocalDate getFechaRegistro() {
        return fechaRegistro;
    }

    public void setFechaRegistro(LocalDate fechaRegistro) {
        this.fechaRegistro = fechaRegistro;
    }
}
