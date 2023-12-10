package co.edu.adelantos_proyecto;

<<<<<<< Updated upstream
import static co.edu.adelantos_proyecto.api.ValuesApi.BASE_URL;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
=======
import android.annotation.SuppressLint;
import android.app.AlertDialog;
>>>>>>> Stashed changes
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

<<<<<<< Updated upstream
import co.edu.adelantos_proyecto.api.ServiceLogin;
import co.edu.adelantos_proyecto.model.Credentials;
import co.edu.adelantos_proyecto.model.Loger;
import co.edu.adelantos_proyecto.model.ResponseCredentials;
import co.edu.adelantos_proyecto.remote.ClienteRetrofit;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
=======
import java.util.regex.Pattern;
>>>>>>> Stashed changes

public class LoginActivity extends AppCompatActivity {

    private Retrofit retrofit;

    private EditText etCorreo, etContrasena;
    private Button btnLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        linkInit();

<<<<<<< Updated upstream
        btnLogin.setOnClickListener(this::processLogin);
    }

    private void processLogin(View view) {
        if(!validEmail(etCorreo.getText().toString()) && etContrasena.getText().length()<=3){
            alertView("Error en los datos");
        }else {
            String password = md5(etContrasena.getText().toString());
            Loger loger = new Loger();
            loger.setUse_mail(etCorreo.getText().toString());
            loger.setUse_pss(password);
            retrofit = ClienteRetrofit.getClient(BASE_URL);
            ServiceLogin serviceLogin = retrofit.create(ServiceLogin.class);
            Call<ResponseCredentials> call = serviceLogin.accesslogin(loger);
            call.enqueue(new Callback<ResponseCredentials>() {
                @Override
                public void onResponse(Call<ResponseCredentials> call, Response<ResponseCredentials> response) {
                    if(response.isSuccessful()){
                        ResponseCredentials body = response.body();
                        String mensaje = body.getMensaje();
                        ArrayList<Credentials> list = body.getCredentials();
                        Toast.makeText(LoginActivity.this, "Ingresando a SICOSIS"+mensaje, Toast.LENGTH_SHORT).show();
                        if(mensaje.equals("OK") && !isNullOrEmpty(list)){
                            Toast.makeText(LoginActivity.this, "Datos exitosos", Toast.LENGTH_SHORT).show();
                            for(Credentials c:list){
                                SharedPreferences sharedPreferences = getSharedPreferences("credenciales", Context.MODE_PRIVATE);
                                SharedPreferences.Editor editor =  sharedPreferences.edit();
                                editor.putString("key", c.getUs_key());
                                editor.putString("idetificador", c.getUs_identifier());
                                editor.putString("id", c.getUse_id());
                                editor.commit();
                                goTo();
                            }
                        }else {
                            alertView("Usuario o contraseña invalida");
                        }
                    }else {
                        alertView("Usuario no existe! \n intente nuevamente");
                    }
                }

                @Override
                public void onFailure(Call<ResponseCredentials> call, Throwable t) {
                    Log.i("response",t.getMessage());
                    alertView("Error en Servicio \n Comuniquese con el desarrollador");
                }
            });
        }
    }

    private boolean isValidEmail(String email) {
        return Patterns.EMAIL_ADDRESS.matcher(email).matches();
    }

    private boolean isValidPassword(String password) {
        // Customize this regex pattern based on your password requirements
        String passwordPattern = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=!])(?=\\S+$).{6,}$";
        Pattern pattern = Pattern.compile(passwordPattern);
        return pattern.matcher(password).matches();
    }

    private void showErrorDialog(String message) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Error")
                .setMessage(message)
                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        // Do nothing or handle as needed
                    }
                })
                .show();
    }

    private void showSuccessDialog(String message) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Success")
                .setMessage(message)
                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        // Do nothing or handle as needed
                    }
                })
                .show();
    }

    private void goTo(){
        try{
            Intent intent = new Intent(this,MainActivity.class);
            startActivity(intent);
            finish();
        }catch (Exception e){
            System.err.println(e.getMessage());
        }
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

    public boolean validEmail(String data){
        Pattern pattern =
                Pattern.compile("^[a-zA-Z0-9.!#$%&'*+/=?^_`{|}~\\-]+@[a-zA-Z0-9-]+(?:\\.[a-zA-Z0-9-]+)*$");
        Matcher mather = pattern.matcher(data);
        if (mather.find() == true) {
            System.out.println("El email ingresado es válido.");
            return true;
        } else {
            System.out.println("El email ingresado es inválido.");
        }
        return false;
    }

    public static String md5(final String s) {
        final String MD5 = "MD5";
        try {
            // Create MD5 Hash
            MessageDigest digest = MessageDigest.getInstance(MD5);
            digest.update(s.getBytes());
            byte messageDigest[] = digest.digest();
            // Create Hex String
            StringBuilder hexString = new StringBuilder();
            for (byte aMessageDigest : messageDigest) {
                String h = Integer.toHexString(0xFF & aMessageDigest);
                while (h.length() < 2)
                    h = "0" + h;
                hexString.append(h);
            }
            return hexString.toString();
        } catch (NoSuchAlgorithmException e) {
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
        this.etCorreo = findViewById(R.id.etCorreo);
        this.etContrasena = findViewById(R.id.etContrasena);
        this.btnLogin = findViewById(R.id.btnLogin);

    }

}
=======
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

                // Validación de nombre y apellido
                if (!Pattern.matches("[a-zA-Z]+", name)) {
                    showAlert("Por favor, ingresa un nombre válido");
                    return;
                }

                if (!Pattern.matches("[a-zA-Z]+", lastName)) {
                    showAlert("Por favor, ingresa un apellido válido");
                    return;
                }

                // Validación de correo electrónico
                if (!Pattern.matches("[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+", email)) {
                    showAlert("Por favor, ingresa un correo electrónico válido");
                    return;
                }

                if (!password.equals(verifyPassword)) {
                    showAlert("Las contraseñas no coinciden");
                    return;
                }

                // Validación de contraseña
                if (!Pattern.matches("^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=])(?=\\S+$).{8,}$", password)) {
                    showAlert("La contraseña debe tener al menos 8 caracteres y contener al menos una letra minúscula, una letra mayúscula, un número y un carácter especial");
                    return;
                }

                // Verificación de contraseña


                Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });
    }

    private void showAlert(String message) {
        new AlertDialog.Builder(LoginActivity.this)
                .setTitle("Error")
                .setMessage(message)
                .setPositiveButton(android.R.string.ok, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                })
                .setIcon(android.R.drawable.ic_dialog_alert)
                .show();
    }
}
>>>>>>> Stashed changes
