package co.edu.adelantos_proyecto.model;

import org.w3c.dom.Text;

public class Movie {

    private Integer products_id;
    private Integer products_usuario;
    private String products_ruta_imagen;
    private String products_descripcion;
    private String products_tallas_disponibles;
    private String products_precio;

    public Integer getProducts_id() {
        return products_id;
    }

    public void setProducts_id(Integer products_id) {
        this.products_id = products_id;
    }

    public Integer getProducts_usuario() {
        return products_usuario;
    }

    public void setProducts_usuario(Integer products_usuario) {
        this.products_usuario = products_usuario;
    }

    public String getProducts_ruta_imagen() {
        return products_ruta_imagen;
    }

    public void setProducts_ruta_imagen(String products_ruta_imagen) {
        this.products_ruta_imagen = products_ruta_imagen;
    }

    public String getProducts_descripcion() {
        return products_descripcion;
    }

    public void setProducts_descripcion(String products_descripcion) {
        this.products_descripcion = products_descripcion;
    }

    public String getProducts_tallas_disponibles() {
        return products_tallas_disponibles;
    }

    public void setProducts_tallas_disponibles(String products_tallas_disponibles) {
        this.products_tallas_disponibles = products_tallas_disponibles;
    }

    public String getProducts_precio() {
        return products_precio;
    }

    public void setProducts_precio(String products_precio) {
        this.products_precio = products_precio;
    }
}
