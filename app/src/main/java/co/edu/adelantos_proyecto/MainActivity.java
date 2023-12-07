package co.edu.adelantos_proyecto;
        import android.annotation.SuppressLint;
        import android.content.Intent;

        import android.os.Bundle;
        import android.util.Log;
        import android.view.View;import java.util.List;
        import co.edu.adelantos_proyecto.Adapter.MovieAdapter;
        import co.edu.adelantos_proyecto.model.Movie;
        import co.edu.adelantos_proyecto.network.ApiClient;
        import co.edu.adelantos_proyecto.network.ApiMovie;
        import retrofit2.Call;
        import retrofit2.Callback;
        import retrofit2.Response;

        import android.widget.ImageButton;
        import android.widget.ImageView;
        import android.widget.Toast;
        import androidx.appcompat.app.AppCompatActivity;
        import androidx.recyclerview.widget.GridLayoutManager;
        import androidx.recyclerview.widget.RecyclerView;

public class MainActivity extends AppCompatActivity {

    private ImageView ivLogin;
    private ImageView ivFavorites, ivCarrito;
    private ImageView ivcamiseta;
    private ImageView ivEstrella1, ivEstrella2, ivEstrella3, ivEstrella4;


    private List<Movie> movies;
    private RecyclerView recyclerView;
    private MovieAdapter movieAdapter;

    private ImageView ivlogin;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ivLogin = findViewById(R.id.ivLogin);
        ivFavorites = findViewById(R.id.ivFavorites);
        ivCarrito = findViewById(R.id.ivCarrito);

        begin();
        showMovies();
        ivlogin.setOnClickListener(this::irlogin);
        recyclerView=findViewById(R.id.rv_movies);
        recyclerView.setLayoutManager(new GridLayoutManager(getApplicationContext(),2));

    }

    private void irlogin(View view) {
        Intent irlogin = new Intent(this,LoginActivity.class);
        startActivity(irlogin);
        finish();
    }

    private void begin() {
        this.ivlogin = findViewById(R.id.ivLogin);
    }

    private void carrito(View view) {
        Intent intent = new Intent(MainActivity.this, CarritoActivity.class);
        startActivity(intent);
    }

    private void login(View view) {
        Intent intent = new Intent(MainActivity.this, LoginActivity.class);
        startActivity(intent);
    }
    public void showMovies(){
        Call<List<co.edu.adelantos_proyecto.model.Movie>> call = ApiClient.getClient().create(ApiMovie.class).getMovies();

        call.enqueue(new Callback<List<Movie>>() {
            @Override
            public void onResponse(Call<List<Movie>> call, Response<List<Movie>> response) {
                if(response.isSuccessful()){
                    movies = response.body();
                    movieAdapter=new MovieAdapter(movies,getApplicationContext());
                    recyclerView.setAdapter(movieAdapter);
                }
            }
            @Override
            public void onFailure(Call<List<Movie>> call, Throwable t) {
                Log.e("API Error", "Error: " + t.getMessage());
                Toast.makeText(MainActivity.this, "ERROR EN CONEXION", Toast.LENGTH_SHORT).show();

            }
        });
    }
}
