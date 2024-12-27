package org.pneira.apiservlet.webapp.headers.repositories;

import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import jdk.jfr.Name;
import org.pneira.apiservlet.webapp.headers.configs.MysqlConn;
import org.pneira.apiservlet.webapp.headers.configs.RepositoryQ;
import org.pneira.apiservlet.webapp.headers.models.Categoria;
import org.pneira.apiservlet.webapp.headers.models.Producto;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;


@RepositoryQ //@ApplicationScoped
public class ProductoRepositoryJdbcImpl implements Repository<Producto>{
// el productoRepo es solo una para tola la aplicacion, Lo que si por request es la Conexion para C/usuario

    @Inject   /** aqui no va transient por que esta clase no se guarda en session y no implement serializable*/
    private Logger log;

    /** *********************************************/
    @Inject                     // hay 3 TIPOS de crear INYECCION : x Atributo eje: <- aqui ,  via Constructor (abajo),  y la ultima via SET
    @MysqlConn     //@Named("conn") /*  public ProductoRepositoryJdbcImpl(Connection conn) {  */
    private Connection conn;        /*    this.conn = conn;  }  */
    /** ************************************************/

    @PostConstruct
    public void inicializar(){
        //System.out.println(" Inicializando el beans " + this.getClass().getName());
        log.info( " Inicializando el beans " + this.getClass().getName() );
    }
    @PreDestroy
    public void destrui(){
        //System.out.println(" Destruyendo el beans " + this.getClass().getName());
        log.info( " Destruyendo el beans " + this.getClass().getName() );
    }


    @Override
    public List<Producto> listar() throws SQLException {
        List<Producto> listPro = new ArrayList<>();
        try(Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT p.* , c.nombre as categoria from productos as p"+
                    " inner join categorias as c ON p.idCategorias = c.id  ")){
            while ( rs.next()){
                Producto p = getProducto(rs);
                listPro.add( p );
            }
        }
        return listPro;
    }

    @Override
    public Producto porId(Long id) throws SQLException {
      Producto producto = null;
        try(PreparedStatement stmt = conn.prepareStatement("SELECT p.*, c.nombre as categoria from productos as p" +
                " inner join categorias as c on p.idCategorias = c.id WHERE p.id = ?")){
            stmt.setLong(1,id);
            try(ResultSet rs = stmt.executeQuery()){
                if (rs.next()){
                    producto = getProducto(rs);
                }
            }
        }
        return producto;
    }

    @Override
    public void guardar(Producto producto) throws SQLException {

        String sql;
        if ( producto.getId() > 0 ){ // producto.getId()!= null ||
            sql = "update productos set nombre =?, precio=?, sku=?, idCategorias=? where id=?";
        }else {
            sql = "insert into productos (nombre, precio, sku, idCategorias, fecha_registro) values (?,?,?,?,?) ";
        }
        try(PreparedStatement stmt = conn.prepareStatement(sql) ){
            stmt.setString(1, producto.getNombre());
            stmt.setInt(2, producto.getPrecio());
            stmt.setString(3, producto.getSku());
            stmt.setLong(4, producto.getCategoria().getId());
            if ( producto.getId() > 0 ){ // producto.getId()!= null ||
                stmt.setLong( 5, producto.getId());
            }else {
                stmt.setDate( 5, Date.valueOf(producto.getFechaRegistro()));
            }
            stmt.executeUpdate();

        }
    }

    @Override
    public void eliminar(Long id) throws SQLException {
        try(PreparedStatement stmt = conn.prepareStatement("Delete from productos where id =?") ){
            stmt.setLong(1,id);
            stmt.executeUpdate();
        }
    }

    private static Producto getProducto(ResultSet rs) throws SQLException {
        Producto p = new Producto(
                rs.getLong("id" ),
                rs.getString( "nombre"), new Categoria(rs.getLong("idCategorias"),
                rs.getString( "categoria")),
                rs.getInt("precio") ,
                rs.getString("sku"),
                rs.getDate("fecha_registro").toLocalDate());
        return p;
    }

}
