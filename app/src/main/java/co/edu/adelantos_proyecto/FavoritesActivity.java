package co.edu.adelantos_proyecto;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class FavoritesActivity extends AppCompatActivity {

    private ImageView ic_volver;
    private ImageView casa;
    private ImageView ivCarrito;


    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_favorites);
        Intent intent = getIntent();
        ic_volver = findViewById(R.id.ic_volver);
        casa = findViewById(R.id.casa);
        ivCarrito = findViewById(R.id.ivCarrito);

        this.ic_volver.setOnClickListener(this::main);
        this.casa.setOnClickListener(this::main2);
        this.ivCarrito.setOnClickListener(this::carrito);


        if (intent != null) {
            int productoId = intent.getIntExtra("productoId", 0);
            String productoNombre = intent.getStringExtra("productoNombre");
            String productoPrecio = intent.getStringExtra("productoPrecio");

        }
    }



    private void main(View view) {
        Intent intent = new Intent(FavoritesActivity.this, MainActivity.class);
        startActivity(intent);
    }
    private void main2(View view) {
        Intent intent = new Intent(FavoritesActivity.this, MainActivity.class);
        startActivity(intent);
    }
    private void carrito(View view) {
        Intent intent = new Intent(FavoritesActivity.this, CarritoActivity.class);
        startActivity(intent);
    }

}
