package co.edu.adelantos_proyecto;

import static co.edu.adelantos_proyecto.api.ValuesApi.BASE_URL;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import co.edu.adelantos_proyecto.MainActivity;
import co.edu.adelantos_proyecto.api.ServiceLogin;
import co.edu.adelantos_proyecto.model.Credentials;
import co.edu.adelantos_proyecto.model.Loger;
import co.edu.adelantos_proyecto.model.ResponseCredentials;
import co.edu.adelantos_proyecto.remote.ClienteRetrofit;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class LoginActivity extends AppCompatActivity {

    private EditText etNombre, etApellido, etCorreo, etContrasena, etConfirmarContrasena;
    private Button btnLogin;
    private Retrofit retrofit;
    private EditText user;
    private EditText pass;
    private Button logear;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        linkInit();
        logear.setOnClickListener(this::processLogin);

        etNombre = findViewById(R.id.etNombre);
        etApellido = findViewById(R.id.etApellido);
        etCorreo = findViewById(R.id.etCorreo);
        etContrasena = findViewById(R.id.etContrasena);
        etConfirmarContrasena = findViewById(R.id.etConfimarContrasena);
        btnLogin = findViewById(R.id.btnLogin);

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name = etNombre.getText().toString().trim();
                String lastName = etApellido.getText().toString().trim();
                String email = etCorreo.getText().toString().trim();
                String password = etContrasena.getText().toString().trim();
                String verifyPassword = etConfirmarContrasena.getText().toString().trim();

                Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });
    }

    private void processLogin(View view) {
        if (!validEmail(user.getText().toString()) && pass.getText().length()<=3){
            alertView("Error en los datos");
        }else{
            String password = md5(pass.getText().toString());
            Loger loger = new Loger();
                loger.setUse_mail(user.getText().toString());
                loger.setUse_password(password);
                retrofit = ClienteRetrofit.getClient(BASE_URL);
                ServiceLogin serviceLogin = retrofit.create(ServiceLogin.class);
                Call<ResponseCredentials> call = serviceLogin.accessLogin(loger);
                call.enqueue(new Callback<ResponseCredentials>() {
                    @Override
                    public void onResponse(Call<ResponseCredentials> call, Response<ResponseCredentials> response) {
                        if (response.isSuccessful()){
                            ResponseCredentials body = response.body();
                            String mensaje = body.getMensaje();
                            ArrayList<Credentials> list = body.getCredentials();
                            Toast.makeText(LoginActivity.this,"Ingresando a la App"+mensaje, Toast.LENGTH_LONG).show();
                            if (mensaje.equals("OK") && !isNullOrEmpty(list)){
                                for(Credentials c:list){
                                    SharedPreferences shared = getSharedPreferences("credentials", Context.MODE_PRIVATE);
                                    SharedPreferences.Editor editor = shared.edit();
                                    editor.putString("password", c.getUse_password());
                                    editor.putString("mail", c.getUse_mail());
                                    editor.putString("id", c.getUse_id());
                                    editor.commit();
                                    goTo();

                                }
                            }else{
                                alertView("Usuario no existe o contraseña invalida");
                            }
                        }else{
                            alertView("Usuario no existe, intente de nuevo");
                        }
                    }

                    @Override
                    public void onFailure(Call<ResponseCredentials> call, Throwable t) {
                        Log.i("response",t.getMessage());
                        alertView("Error en servicio comuniquese con el desarrollador");
                    }
                });
        }
    }

    private void goTo(){
        try {
            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);
            finish();
        }catch (Exception e){
            System.err.println(e.getMessage());
        }
    }
    public boolean validEmail(String data){
        Pattern pattern =
                Pattern.compile("^[a-zA-Z0-9.!#$%&'+/=?^_`{|}~\\-]+@[a-zA-Z0-9-]+(?:\\.[a-zA-Z0-9-]+)$");
        Matcher mather = pattern.matcher(data);
        if (mather.find() == true) {
            System.out.println("El email ingresado es válido.");
            return true;
        } else {
            System.out.println("El email ingresado es inválido.");
        }
        return false;
    }

    public static boolean isNullOrEmpty(Object obj){
        if(obj==null)return true;
        if(obj instanceof String) return ((String)obj).trim().equals("") || ((String)obj).equalsIgnoreCase("NULL");
        if(obj instanceof Integer) return ((Integer)obj)==0;
        if(obj instanceof Long) return ((Long)obj).equals(new Long(0));
        if(obj instanceof Double) return ((Double)obj).equals(0.0);
        if(obj instanceof Collection) return (((Collection)obj).isEmpty());
        return false;
    }

    public static String md5(final String s){
        final String MD5 = "MD5";
        try{
            MessageDigest digest = MessageDigest.getInstance(MD5);
            digest.update(s.getBytes());
            byte messageDigest[] = digest.digest();

            StringBuilder hexString = new StringBuilder();
            for (byte aMessageDigest : messageDigest){
                String h = Integer.toHexString(0xFF & aMessageDigest);
                while (h.length() < 2)
                    h = "0" + h;
                hexString.append(h);
            }
            return hexString.toString();
        }catch (NoSuchAlgorithmException e){
            e.printStackTrace();
        }
        return "";
    }
    private void alertView(String mensaje){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Login");
        builder.setMessage(mensaje);
        builder.setPositiveButton("ACEPTAR",null);
        builder.create();
        builder.show();
    }
    private void linkInit(){
        this.user = findViewById(R.id.etCorreo);
        this.pass = findViewById(R.id.etContrasena);
        this.logear = findViewById(R.id.btnLogin);
    }
}
