package co.edu.adelantos_proyecto;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;


import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;

import java.io.Serializable;

public class productos extends AppCompatActivity implements Serializable {

    private ImageView ic_volver;
    private TextView tituloProducto, descripcionProducto;
    private ImageView imagenProducto, btnEstrella;
    private Button agregarCarrito;
    private boolean estrellaSeleccionada = false;
    private SharedPreferences preferences;
    private String productoId;  // Agregamos un identificador único para el producto

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_productos);

        // Obtén referencias de las vistas
        ic_volver = findViewById(R.id.ic_volver);
        tituloProducto = findViewById(R.id.tituloProducto);
        descripcionProducto = findViewById(R.id.descripcionProducto);
        imagenProducto = findViewById(R.id.imagenProducto);
        agregarCarrito = findViewById(R.id.agregarCarrito);
        btnEstrella = findViewById(R.id.btnEstrella);

        this.ic_volver.setOnClickListener(this::main);

        // Obtén los datos enviados desde la actividad anterior
        Intent intent = getIntent();
        if (intent != null) {
            String title = intent.getStringExtra("movie_title");
            String price = intent.getStringExtra("movie_price");
            String image = intent.getStringExtra("movie_image");

            // Actualiza las vistas con los datos recibidos
            if (title != null) {
                tituloProducto.setText(title);
            }
            if (price != null) {
                descripcionProducto.setText(price);
            }
            if (image != null) {
                Glide.with(this).load(image).into(imagenProducto);
            }

            // Utilizamos el nombre del producto como identificador único
            productoId = title;
        }

        // Inicializar SharedPreferences con una clave única para cada producto
        preferences = getSharedPreferences("MyPrefs_" + productoId, MODE_PRIVATE);

        // Cargar el estado de la estrella desde SharedPreferences
        estrellaSeleccionada = preferences.getBoolean("estrellaSeleccionada", false);
        actualizarEstrella();

        // Configurar el clic en el botón de estrella
        btnEstrella.setOnClickListener(view -> {
            toggleEstrellaColor();
            if (estrellaSeleccionada) {
                addToFavorites();
            } else {
                removeFromFavorites();
            }
        });

        // Manejar clic en el botón de agregar al carrito
        agregarCarrito.setOnClickListener(view -> {
            // Aquí puedes agregar el código para agregar el producto al carrito
            Intent carritoIntent = new Intent(productos.this, CarritoActivity.class);
            startActivity(carritoIntent);
        });
    }

    // Método para cambiar el estado de la estrella
    private void toggleEstrellaColor() {
        estrellaSeleccionada = !estrellaSeleccionada;
        actualizarEstrella();

        // Guardar el estado de la estrella en SharedPreferences
        SharedPreferences.Editor editor = preferences.edit();
        editor.putBoolean("estrellaSeleccionada", estrellaSeleccionada);
        editor.apply();
    }

    // Método para actualizar la imagen de la estrella
    private void actualizarEstrella() {
        if (estrellaSeleccionada) {
            btnEstrella.setImageResource(R.drawable.icono_estrella_amarillo);
        } else {
            btnEstrella.setImageResource(R.drawable.icono_estrella);
        }
    }

    // Método para agregar a favoritos
    private void addToFavorites() {
        Toast.makeText(productos.this, "Agregado a favoritos", Toast.LENGTH_SHORT).show();
    }

    // Método para eliminar de favoritos
    private void removeFromFavorites() {
        Toast.makeText(productos.this, "Eliminado de favoritos", Toast.LENGTH_SHORT).show();
    }

    private void main(View view) {
        Intent intent = new Intent(productos.this, MainActivity.class);
        startActivity(intent);
    }
}
