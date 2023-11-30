package co.edu.adelantos_proyecto;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private ImageView ivLogin;
    private ImageView ivFavorites;
    private ImageView ivcamiseta;
    private ImageView ivEstrella1, ivEstrella2, ivEstrella3, ivEstrella4;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ivLogin = findViewById(R.id.ivLogin);
        ivFavorites = findViewById(R.id.ivFavorites);
        ivcamiseta = findViewById(R.id.camiseta_blanca);
        this.ivLogin.setOnClickListener(this::login);
        this.ivFavorites.setOnClickListener(this::favorites);
        this.ivcamiseta.setOnClickListener(this::camsietas);

        ivEstrella1 = findViewById(R.id.ivEstrella1);
        ivEstrella2 = findViewById(R.id.ivEstrella2);
        ivEstrella3 = findViewById(R.id.ivEstrella3);
        ivEstrella4 = findViewById(R.id.ivEstrella4);

        configureEstrellaClickListener(ivEstrella1, 1, "Camisa 1", "$50.000");
        configureEstrellaClickListener(ivEstrella2, 2, "Camisa 2", "$75.000");
        configureEstrellaClickListener(ivEstrella3, 3, "Camisa 3", "$80.000");
        configureEstrellaClickListener(ivEstrella4, 4, "Camisa 4", "$40.000");
    }

    private void camsietas(View view) {
        Intent intent = new Intent(MainActivity.this, productos.class);
        startActivity(intent);
    }

    private void favorites(View view) {
        Intent intent = new Intent(MainActivity.this, FavoritesActivity.class);
        startActivity(intent);
    }

    private void login(View view) {
        Intent intent = new Intent(MainActivity.this, LoginActivity.class);
        startActivity(intent);
    }

    private void configureEstrellaClickListener(final ImageView estrellaImageView, final int productoId, final String productoNombre, final String productoPrecio) {
        estrellaImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                toggleEstrellaColor(estrellaImageView);
                if (isEstrellaAmarilla(estrellaImageView)) {
                    // Si la estrella está amarilla, agrega el producto a la lista de favoritos
                    addToFavorites(productoId, productoNombre, productoPrecio);
                } else {
                    // Si la estrella no está amarilla, elimina el producto de la lista de favoritos
                    removeFromFavorites(productoId);
                }
            }
        });
    }

// ...

    private void addToFavorites(int productoId, String productoNombre, String productoPrecio) {
        // Puedes almacenar estos detalles del producto en una lista o base de datos local
        // y usarlos para mostrar en la actividad de favoritos
        // Aquí solo se muestra un mensaje de ejemplo
        Toast.makeText(MainActivity.this, "Agregado a favoritos: " + productoNombre, Toast.LENGTH_SHORT).show();
    }

    private void removeFromFavorites(int productoId) {
        // Elimina el producto de la lista de favoritos
        // Aquí solo se muestra un mensaje de ejemplo
        Toast.makeText(MainActivity.this, "Eliminado de favoritos", Toast.LENGTH_SHORT).show();
    }

    private void toggleEstrellaColor(ImageView estrellaImageView) {
        boolean isAmarilla = isEstrellaAmarilla(estrellaImageView);
        if (isAmarilla) {
            estrellaImageView.setImageResource(R.drawable.icono_estrella);
        } else {
            estrellaImageView.setImageResource(R.drawable.icono_estrella_amarillo);
        }
        setEstrellaAmarilla(estrellaImageView, !isAmarilla);
    }

    private boolean isEstrellaAmarilla(ImageView estrellaImageView) {
        return estrellaImageView.getTag() != null && estrellaImageView.getTag().equals("amarilla");
    }

    private void setEstrellaAmarilla(ImageView estrellaImageView, boolean isAmarilla) {
        estrellaImageView.setTag(isAmarilla ? "amarilla" : null);
    }


}
