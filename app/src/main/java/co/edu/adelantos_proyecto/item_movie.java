package co.edu.adelantos_proyecto;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class item_movie extends AppCompatActivity {

    private ImageView iv_portada;

    ;

    @SuppressLint("WrongViewCast")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.item_movie);


        // Inicializar iv_portada después de inflar el diseño
        iv_portada = findViewById(R.id.iv_portada);


        // Configurar el OnClickListener directamente
        iv_portada.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(item_movie.this, "Clic en iv_portada", Toast.LENGTH_SHORT).show();
                Intent irproduct = new Intent(item_movie.this, productos.class);
                startActivity(irproduct);
                finish();
            }
        });
    }

    // Este método se llama desde onCreate para inicializar las vistas
    @SuppressLint("WrongViewCast")
    private void begin() {
        iv_portada = findViewById(R.id.iv_portada);


        // Configurar el OnClickListener en el método begin
        iv_portada.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(item_movie.this, "Clic en iv_portada", Toast.LENGTH_SHORT).show();
                Intent irproduct = new Intent(item_movie.this, productos.class);
                startActivity(irproduct);
                finish();
            }
        });

    }

    private void ir_portada(View view) {
        Toast.makeText(this, "Clic en iv_portada", Toast.LENGTH_SHORT).show();
        Intent irproduct = new Intent(this, productos.class);
        startActivity(irproduct);
        finish();
    }


}
