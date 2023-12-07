package co.edu.adelantos_proyecto.model;

public class Product {

    private String products_id;
    private String products_usuario_id ;
    private String products_ruta_imagen;
    private String products_descripcion;
    private String products_tallas_disponibles;
    private String products_precio;


    public String getProducts_id() {
        return products_id;
    }

    public void setProducts_id(String products_id) {
        this.products_id = products_id;
    }

    public String getProducts_usuario_id() {
        return products_usuario_id;
    }

    public void setProducts_usuario_id(String products_usuario_id) {
        this.products_usuario_id = products_usuario_id;
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
