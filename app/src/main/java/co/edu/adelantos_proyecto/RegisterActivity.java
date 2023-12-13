package co.edu.adelantos_proyecto;

import android.annotation.SuppressLint;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import java.util.regex.Pattern;

public class RegisterActivity extends AppCompatActivity {

    private EditText etNombre, etApellido, etCorreo, etContrasena, etConfirmarContrasena;
    private CheckBox cbCondiciones;
    private Button btnLogin;
    private ImageView ic_volver;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        Intent intent = getIntent();ic_volver = findViewById(R.id.ivVolver);
        this.ic_volver.setOnClickListener(this::main);

        etNombre = findViewById(R.id.etNombre);
        etApellido = findViewById(R.id.etApellido);
        etCorreo = findViewById(R.id.etCorreo);
        etContrasena = findViewById(R.id.etContrasena);
        etConfirmarContrasena = findViewById(R.id.etConfimarContrasena);
        cbCondiciones = findViewById(R.id.cbCondiciones);
        btnLogin = findViewById(R.id.btnLogin);

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (validateLogin()) {
                    // Simulate a successful login, replace this with your authentication logic
                    showSuccessDialog("¡Inicio de sesión exitoso!");

                    Intent intent = new Intent(RegisterActivity.this, MainActivity.class);
                    startActivity(intent);
                }
            }
        });
    }

    private boolean validateLogin() {
        String nombre = etNombre.getText().toString().trim();
        String apellido = etApellido.getText().toString().trim();
        String email = etCorreo.getText().toString().trim();
        String password = etContrasena.getText().toString().trim();
        String confirmarPassword = etConfirmarContrasena.getText().toString().trim();

        if (nombre.isEmpty() || apellido.isEmpty() || email.isEmpty() || password.isEmpty() || confirmarPassword.isEmpty()) {
            showErrorDialog("Por favor, rellene todos los campos");
            return false;
        }

        if (!isValidName(nombre)) {
            showErrorDialog("Nombre no válido");
            return false;
        }

        if (!isValidName(apellido)) {
            showErrorDialog("Apellido no válido");
            return false;
        }

        if (!isValidEmail(email)) {
            showErrorDialog("Dirección de correo electrónico no válida");
            return false;
        }

        if (!isValidPassword(password)) {
            showErrorDialog("Formato de contraseña no válido");
            return false;
        }

        if (!password.equals(confirmarPassword)) {
            showErrorDialog("Las contraseñas no coinciden");
            return false;
        }

        if (!cbCondiciones.isChecked()) {
            showErrorDialog("Por favor, acepte los términos y condiciones");
            return false;
        }

        return true;
    }

    private boolean isValidName(String name) {
        // Allow only letters and spaces in the name
        String namePattern = "^[a-zA-Z]+( [a-zA-Z]+)*$";
        return Pattern.matches(namePattern, name);
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
    private void main(View view) {
        Intent intent = new Intent(RegisterActivity.this, MainActivity.class);
        startActivity(intent);
    }
}
