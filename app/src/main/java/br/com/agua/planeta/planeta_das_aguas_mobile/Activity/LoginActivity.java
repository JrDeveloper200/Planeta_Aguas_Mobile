package br.com.agua.planeta.planeta_das_aguas_mobile.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

import br.com.agua.planeta.planeta_das_aguas_mobile.DAO.ConfiguracaoFirebase;
import br.com.agua.planeta.planeta_das_aguas_mobile.Entidades.Usuarios;
import br.com.agua.planeta.planeta_das_aguas_mobile.R;

public class LoginActivity extends AppCompatActivity {

    private EditText editEmail;
    private EditText editSenha;
    private TextView tvAbreCadastro;
    private Button btnLogar;
    private FirebaseAuth autenticacao;
    private Usuarios usuarios;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        editEmail = findViewById(R.id.editEmail);
        editSenha = findViewById(R.id.editSenha);
        tvAbreCadastro = findViewById(R.id.tvAbreCadastro);
        btnLogar = findViewById(R.id.btnLogar);

        btnLogar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!editEmail.getText().toString().equals("") && !editSenha.getText().toString().equals("")) {

                    usuarios = new Usuarios();
                    usuarios.setEmail(editEmail.getText().toString());
                    usuarios.setSenha(editSenha.getText().toString());

                    validarLogin();

                } else {
                    Toast.makeText(LoginActivity.this, "Preencha os campos de e-mail e senha", Toast.LENGTH_SHORT).show();
                }
            }
        });

        tvAbreCadastro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                abreCadastroUsuario();
            }
        });

    }

    private void validarLogin() {

        autenticacao = ConfiguracaoFirebase.getFirebaseAutenticacao();
        autenticacao.signInWithEmailAndPassword(usuarios.getEmail(), usuarios.getSenha()).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()) {

                    abrirTelaPerfil();
                    Toast.makeText(LoginActivity.this, "Login Efetuado com Sucesso!", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(LoginActivity.this, "Usuarios ou Senha Invalidos!", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    public void abrirTelaPerfil() {
        Intent intentAbrirTelaPerfil = new Intent(LoginActivity.this, TelaPerfil.class);
        startActivity(intentAbrirTelaPerfil);
    }

    public void abreCadastroUsuario() {
        Intent intent = new Intent(LoginActivity.this, TelaCadastro.class);
        startActivity(intent);
    }
}
