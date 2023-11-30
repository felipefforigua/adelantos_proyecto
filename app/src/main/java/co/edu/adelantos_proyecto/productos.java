package co.edu.adelantos_proyecto;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

public class productos extends AppCompatActivity {

    private ImageView icvolver;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_productos);

        icvolver = findViewById(R.id.ic_volver);
        this.icvolver.setOnClickListener(this::icvolver);
    }

    private void icvolver(View view) {
        Intent intent = new Intent(productos.this, MainActivity.class);
        startActivity(intent);
    }
}