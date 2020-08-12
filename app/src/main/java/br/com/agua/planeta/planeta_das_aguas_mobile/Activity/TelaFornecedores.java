package br.com.agua.planeta.planeta_das_aguas_mobile.Activity;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import br.com.agua.planeta.planeta_das_aguas_mobile.R;

public class TelaFornecedores extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tela_fornecedores);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }
}
