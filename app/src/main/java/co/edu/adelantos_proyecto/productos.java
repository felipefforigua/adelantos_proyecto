package co.edu.adelantos_proyecto;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;

import java.util.ArrayList;
import java.util.List;

import co.edu.adelantos_proyecto.api.ProductService;
import co.edu.adelantos_proyecto.model.Product;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class productos extends AppCompatActivity {

    private ImageView icvolver;
    private List<Product> productList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_productos);

        // Configurar Retrofit
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://tu-api.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        ProductService productService = retrofit.create(ProductService.class);

        // Hacer la solicitud al API
        Call<List<Product>> call = productService.getProducts();
        call.enqueue(new Callback<List<Product>>() {
            @Override
            public void onResponse(Call<List<Product>> call, Response<List<Product>> response) {
                if (response.isSuccessful() && response.body() != null) {
                    productList = response.body();
                    // Mostrar tarjetas
                    showProductCards();
                } else {
                    // Manejar error
                }
            }

            @Override
            public void onFailure(Call<List<Product>> call, Throwable t) {
                // Manejar error de conexión
            }
        });
    }

    private void showProductCards() {
        // Crear tarjetas para cada producto
        for (Product product : productList) {
            // Lógica para crear tarjetas (puedes usar RecyclerView, ListView, etc.)
            // Ejemplo: Crear una tarjeta usando CardView
            CardView cardView = new CardView(this);
            // Configurar el diseño de la tarjeta, agregar texto e imagen
            // ...

            // Agregar la tarjeta al diseño principal
            LinearLayout mainLayout = findViewById(R.id.mainLayout);
            mainLayout.addView(cardView);
        }
    }
}