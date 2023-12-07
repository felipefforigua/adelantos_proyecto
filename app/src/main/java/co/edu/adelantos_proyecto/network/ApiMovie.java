package co.edu.adelantos_proyecto.network;

import java.util.List;

import co.edu.adelantos_proyecto.model.Movie;
import retrofit2.Call;
import retrofit2.http.GET;

public interface ApiMovie{
    @GET("api_12b/products")
    Call<List<Movie>> getMovies();
}
