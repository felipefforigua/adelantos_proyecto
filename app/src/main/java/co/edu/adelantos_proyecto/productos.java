package co.edu.adelantos_proyecto;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;

public class productos extends AppCompatActivity {

    private ImageView icvolver;
    private TextView tituloProducto, descripcionProducto;
    private ImageView imagenProducto;
    private Button agregarCarrito;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_productos);

        // Obtén referencias de las vistas
        icvolver = findViewById(R.id.ic_volver);
        tituloProducto = findViewById(R.id.tituloProducto);
        descripcionProducto = findViewById(R.id.descripcionProducto);
        imagenProducto = findViewById(R.id.imagenProducto);
        agregarCarrito = findViewById(R.id.agregarCarrito);

        // Manejar clic en el botón de volver
        icvolver.setOnClickListener(view -> {
            Intent intent = new Intent(productos.this, MainActivity.class);
            startActivity(intent);
            finish(); // Cierra la actividad actual
        });

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
        }

        // Manejar clic en el botón de agregar al carrito
        agregarCarrito.setOnClickListener(view -> {
            // Aquí puedes agregar el código para agregar el producto al carrito
            Intent carritoIntent = new Intent(productos.this, CarritoActivity.class);
            startActivity(carritoIntent);
        });
    }

}
