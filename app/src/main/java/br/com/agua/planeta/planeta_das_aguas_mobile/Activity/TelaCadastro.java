package br.com.agua.planeta.planeta_das_aguas_mobile.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException;
import com.google.firebase.auth.FirebaseAuthUserCollisionException;
import com.google.firebase.auth.FirebaseAuthWeakPasswordException;
import com.google.firebase.auth.FirebaseUser;

import java.util.Objects;

import br.com.agua.planeta.planeta_das_aguas_mobile.DAO.ConfiguracaoFirebase;
import br.com.agua.planeta.planeta_das_aguas_mobile.Entidades.Usuarios;
import br.com.agua.planeta.planeta_das_aguas_mobile.Helper.Base64Custom;
import br.com.agua.planeta.planeta_das_aguas_mobile.Helper.Preferencias;
import br.com.agua.planeta.planeta_das_aguas_mobile.R;

public class TelaCadastro extends AppCompatActivity {

    private EditText edtCadNome;
    private EditText edtCadEmail;
    private EditText edtCadSenha;
    private EditText edtCadConfirmarSenha;
    private EditText edtCadAniversario;
    private EditText edtCadCidade;
    private EditText edtCadCep;
    private EditText edtCadEndereco;
    private EditText edtCadTelefone;
    private EditText edtCadCpf;

    private RadioButton rbMasculino;
    private RadioButton rbFeminino;
    private Button btnGravar;

    private Usuarios usuarios;
    private FirebaseAuth autenticacao;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tela_cadastro);

        edtCadNome = findViewById(R.id.edtCadNome);
        edtCadEmail = findViewById(R.id.edtCadEmail);
        edtCadSenha = findViewById(R.id.edtCadSenha);
        edtCadConfirmarSenha = findViewById(R.id.edtCadConfirmarSenha);
        edtCadAniversario = findViewById(R.id.edtCadAniversario);
        edtCadCidade = findViewById(R.id.edtCadCidade);
        edtCadCep = findViewById(R.id.edtCadCep);
        edtCadEndereco = findViewById(R.id.edtCadEndereco);
        edtCadTelefone = findViewById(R.id.edtCadTelefone);
        edtCadCpf = findViewById(R.id.edtCadCpf);
        rbFeminino = findViewById(R.id.rbFeminino);
        rbMasculino = findViewById(R.id.rbMasculino);
        btnGravar = findViewById(R.id.btnGravar);

        btnGravar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (edtCadSenha.getText().toString().equals(edtCadConfirmarSenha.getText().toString())) {

                    usuarios = new Usuarios();
                    usuarios.setNomecompleto(edtCadNome.getText().toString());
                    usuarios.setEmail(edtCadEmail.getText().toString());
                    usuarios.setSenha(edtCadSenha.getText().toString());
                    usuarios.setAniversario(edtCadAniversario.getText().toString());
                    usuarios.setCidade(edtCadCidade.getText().toString());
                    usuarios.setCep(edtCadCep.getText().toString());
                    usuarios.setEndereco(edtCadEndereco.getText().toString());
                    usuarios.setTelefone(edtCadTelefone.getText().toString());
                    usuarios.setCpf(edtCadCpf.getText().toString());


                    if (edtCadNome.length() < 1 || edtCadNome == null) {
                        Toast.makeText(TelaCadastro.this, "Nome obrigatorio", Toast.LENGTH_LONG).show();
                        return;
                    }

                    if (edtCadEmail != null && edtCadEmail.length() < 1) {
                        Toast.makeText(TelaCadastro.this, "Preencha o E-mail", Toast.LENGTH_LONG).show();
                        return;
                    }

                    if (edtCadSenha == null || edtCadSenha.length() < 8) {
                        Toast.makeText(TelaCadastro.this, "Preencha a Senha com no minímo 8 caracteres entre letras e numeros!", Toast.LENGTH_LONG).show();
                        return;
                    }

                    if (edtCadConfirmarSenha == null || edtCadConfirmarSenha.length() < 8) {
                        Toast.makeText(TelaCadastro.this, "Confirme a Senha", Toast.LENGTH_LONG).show();
                        return;
                    }

                    if (edtCadAniversario == null || edtCadAniversario.length() < 1) {
                        Toast.makeText(TelaCadastro.this, "Coloque a Data de Nascimento", Toast.LENGTH_LONG).show();
                        return;
                    }

                    if (edtCadCidade == null || edtCadCidade.length() < 1) {
                        Toast.makeText(TelaCadastro.this, "Preencha a Cidade", Toast.LENGTH_LONG).show();
                        return;
                    }

                    if (edtCadCep == null || edtCadCep.length() < 8) {
                        Toast.makeText(TelaCadastro.this, "Preencha o CEP corretamente, sem pontos ou traços!", Toast.LENGTH_LONG).show();
                        return;
                    }

                    if (edtCadEndereco == null || edtCadEndereco.length() < 1) {
                        Toast.makeText(TelaCadastro.this, "Preencha o Endereço", Toast.LENGTH_LONG).show();
                        return;
                    }

                    if (edtCadTelefone == null || edtCadTelefone.length() < 8) {
                        Toast.makeText(TelaCadastro.this, "Preencha o Telefone", Toast.LENGTH_LONG).show();
                        return;
                    }

                    if (edtCadCpf == null || edtCadCpf.length() < 11) {
                        Toast.makeText(TelaCadastro.this, "Preencha o CPF", Toast.LENGTH_LONG).show();
                        return;
                    }

                    if (!rbFeminino.isChecked()) {
                        usuarios.setSexo("Masculino");
                        if (!rbMasculino.isChecked()) {
                            usuarios.setSexo("Feminino");
                            Toast.makeText(TelaCadastro.this, "Marque uma das Opções", Toast.LENGTH_LONG).show();
                            return;
                        }
                    }
                    cadastrarUsuario();
                }

            }
        });
    }


    private void cadastrarUsuario() {

        autenticacao = ConfiguracaoFirebase.getFirebaseAutenticacao();
        autenticacao.createUserWithEmailAndPassword(
                usuarios.getEmail(),
                usuarios.getSenha()
        ).addOnCompleteListener(TelaCadastro.this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()) {
                    Toast.makeText(TelaCadastro.this, "Usuário Cadastrado Com Sucesso!", Toast.LENGTH_LONG).show();

                    String idenficadorUsuario = Base64Custom.codificarBase64(usuarios.getEmail());
                    FirebaseUser usuarioFirebase = task.getResult().getUser();
                    usuarios.setId(idenficadorUsuario);
                    usuarios.salvar();

                    Preferencias preferencias = new Preferencias(TelaCadastro.this);
                    preferencias.salvarUsuarioPreferencias(idenficadorUsuario, usuarios.getNomecompleto());

                    abrirLoginUsuario();
                } else {
                    String erroExcecao = "";

                    try {

                        throw Objects.requireNonNull(task.getException());

                    } catch (FirebaseAuthWeakPasswordException e) {

                        erroExcecao = "Digite uma Senha mais forte, contendo no minímo 8 caracteres entre letras e números!";
                    } catch (FirebaseAuthInvalidCredentialsException e) {

                        erroExcecao = "O E-mail digitado não é valido, digite outro E-mail!";
                    } catch (FirebaseAuthUserCollisionException e) {

                        erroExcecao = "O E-mail digitado já está cadastrado no sistema!";
                    } catch (Exception e) {

                        erroExcecao = "Erro ao efetuar o cadastro";
                        e.printStackTrace();
                    }

                    Toast.makeText(TelaCadastro.this, "Erro:" + erroExcecao, Toast.LENGTH_LONG).show();

                }
            }
        });

    }

    public void abrirLoginUsuario() {
        Intent intent = new Intent(TelaCadastro.this, LoginActivity.class);
        startActivity(intent);
        finish();
    }
}
