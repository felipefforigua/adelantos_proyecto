package co.edu.adelantos_proyecto;

import static co.edu.adelantos_proyecto.api.ValuesApi.BASE_URL;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import java.util.ArrayList;
import java.util.List;

import co.edu.adelantos_proyecto.model.Product;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class productos extends AppCompatActivity {

    private ImageView icvolver;
<<<<<<< HEAD
    private Button agregarCarrito;


=======
    private List<Product> productList = new ArrayList<>();
>>>>>>> 64982d91e2b281dfd123a0e41861d0550d81573b

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
        startActivity(intent);  setContentView(R.layout.activity_productos);

    }
}

