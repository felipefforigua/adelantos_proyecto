package co.edu.adelantos_proyecto;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

public class productos extends AppCompatActivity {

    private ImageView icvolver;
    private Button agregarCarrito;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_productos);

        icvolver = findViewById(R.id.ic_volver);
        this.icvolver.setOnClickListener(this::icvolver);

        Button agregarCarrito = findViewById(R.id.agregarCarrito);
        agregarCarrito.setOnClickListener(view -> {
            // Aquí puedes agregar el código para ir a la vista del carrito
            Intent intent = new Intent(this, CarritoActivity.class); // Reemplaza CarritoActivity.class con el nombre correcto de tu actividad del carrito
            startActivity(intent);
        });

    }

    private void icvolver(View view) {
        Intent intent = new Intent(productos.this, MainActivity.class);
        startActivity(intent);
    }
}