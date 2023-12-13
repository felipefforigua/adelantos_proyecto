package co.edu.adelantos_proyecto;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;


public class LoginDialogFragment extends DialogFragment {

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        LayoutInflater inflater = requireActivity().getLayoutInflater();
        View view = inflater.inflate(R.layout.login_dialog_fragment, null);

        @SuppressLint({"MissingInflatedId", "LocalSuppress"}) Button btnLogin = view.findViewById(R.id.btnLogin);
        @SuppressLint({"MissingInflatedId", "LocalSuppress"}) Button btnRegistro = view.findViewById(R.id.btnRegister);

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Iniciar LoginActivity
                Intent intent = new Intent(getActivity(), LoginActivity.class);
                startActivity(intent);
                dismiss();
            }
        });

        btnRegistro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Iniciar RegisterActivity
                Intent intent = new Intent(getActivity(), RegisterActivity.class);
                startActivity(intent);
                dismiss();
            }
        });

        builder.setView(view);

        return builder.create();
    }
}
