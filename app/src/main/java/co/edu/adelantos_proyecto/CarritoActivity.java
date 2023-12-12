package co.edu.adelantos_proyecto;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

public class CarritoActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_carrito);

        // Recuperar datos del Intent
        Intent intent = getIntent();
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
}
