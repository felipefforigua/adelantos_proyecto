package co.edu.adelantos_proyecto;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class FavoritesActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_favorites);
        Intent intent = getIntent();

        if (intent != null) {
            int productoId = intent.getIntExtra("productoId", 0);
            String productoNombre = intent.getStringExtra("productoNombre");
            String productoPrecio = intent.getStringExtra("productoPrecio");

        }
    }



}
