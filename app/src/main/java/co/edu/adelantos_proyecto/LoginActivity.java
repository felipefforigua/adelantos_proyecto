package co.edu.adelantos_proyecto;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import java.util.regex.Pattern;

public class LoginActivity extends AppCompatActivity {

    private EditText etNombre, etApellido, etCorreo, etContrasena, etConfirmarContrasena;
    private Button btnLogin;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

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

                // Validación de contraseña
                if (!Pattern.matches("^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=])(?=\\S+$).{8,}$", password)) {
                    showAlert("La contraseña debe tener al menos 8 caracteres y contener al menos una letra minúscula, una letra mayúscula, un número y un carácter especial");
                    return;
                }

                // Verificación de contraseña
                if (!password.equals(verifyPassword)) {
                    showAlert("Las contraseñas no coinciden");
                    return;
                }

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
