package co.edu.adelantos_proyecto;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import co.edu.adelantos_proyecto.MainActivity;

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

                Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });
    }
}
