package co.edu.adelantos_proyecto.Adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.List;

import co.edu.adelantos_proyecto.R;
import co.edu.adelantos_proyecto.model.Movie;
import co.edu.adelantos_proyecto.productos;

public class MovieAdapter extends RecyclerView.Adapter<MovieAdapter.ViewHolder> {

    private List<Movie> movies;
    private Context context;

    public MovieAdapter(List<Movie> movies, Context context) {
        this.movies = movies;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_movie, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Movie movie = movies.get(position);

        // Configurar las vistas con los datos del objeto Movie
        String precio = movie.getProducts_precio();
        String precioConDolar = "$" + precio;
        holder.tv_description.setText(precioConDolar);
        holder.tv_descriptionproduct.setText(movie.getProducts_descripcion());

        Glide.with(context).load(movie.getProducts_ruta_imagen()).into(holder.iv_portada);

        // Manejar clic en el elemento
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                abrirProductos(movie);
            }
        });

        // Manejar clic en la imagen
        holder.iv_portada.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                abrirProductos(movie);
            }
        });
    }

    private void abrirProductos(Movie movie) {
        Intent irproduct = new Intent(context, productos.class);
        irproduct.putExtra("movie_title", movie.getProducts_descripcion());
        irproduct.putExtra("movie_price", movie.getProducts_precio());
        irproduct.putExtra("movie_image", movie.getProducts_ruta_imagen());
        irproduct.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);

        context.startActivity(irproduct);
    }

    @Override
    public int getItemCount() {
        return movies.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private ImageView iv_portada;
        private TextView tv_description;
        private TextView tv_descriptionproduct;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            iv_portada = itemView.findViewById(R.id.iv_portada);
            tv_description = itemView.findViewById(R.id.tv_titulo);
            tv_descriptionproduct = itemView.findViewById(R.id.tv_description);
        }
    }
}
