package co.edu.adelantos_proyecto.api;
import co.edu.adelantos_proyecto.model.Loger;
import co.edu.adelantos_proyecto.model.ResponseCredentials;
import retrofit2.http.Body;
import retrofit2.http.POST;
import retrofit2.Call;

public interface ServiceLogin {
    @POST("login")
    public Call<ResponseCredentials> accesslogin(@Body Loger login);
}
