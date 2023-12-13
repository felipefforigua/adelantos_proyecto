package co.edu.adelantos_proyecto;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

public class CarritoActivity extends AppCompatActivity {

    private ImageView ic_volver;
    private ImageView casa;
    private ImageView favoritos;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_carrito);
        Intent intent = getIntent();
        ic_volver = findViewById(R.id.ic_volver);
        casa = findViewById(R.id.casa);
        favoritos = findViewById(R.id.ivFavorites);

        this.ic_volver.setOnClickListener(this::main);
        this.casa.setOnClickListener(this::main2);
        this.favoritos.setOnClickListener(this::favoritos);

        // Recuperar datos del Intent
        String tituloProducto = intent.getStringExtra("tituloProducto");
        String descripcionProducto = intent.getStringExtra("descripcionProducto");
        String precioProducto = intent.getStringExtra("precioProducto");
        // Puedes recuperar más datos según sea necesario

        // Hacer lo que necesites con los datos del producto en el CarritoActivity
        // Por ejemplo, mostrar los datos en los TextView correspondientes

        // TextView tvTituloProducto = findViewById(R.id.tvTituloProducto);
        // tvTituloProducto.setText(tituloProducto);
        // ...
    }

    private void main(View view) {
        Intent intent = new Intent(CarritoActivity.this, MainActivity.class);
        startActivity(intent);
    }
    private void main2(View view) {
        Intent intent = new Intent(CarritoActivity.this, MainActivity.class);
        startActivity(intent);
    }
    private void favoritos(View view) {
        Intent intent = new Intent(CarritoActivity.this, FavoritesActivity.class);
        startActivity(intent);
    }
}
