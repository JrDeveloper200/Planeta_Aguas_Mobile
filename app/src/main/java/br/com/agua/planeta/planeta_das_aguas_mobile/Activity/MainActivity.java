package br.com.agua.planeta.planeta_das_aguas_mobile.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import br.com.agua.planeta.planeta_das_aguas_mobile.R;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button botao_telasessao = findViewById(R.id.botao_telasessao);
        botao_telasessao.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, LoginActivity.class);
                startActivity(intent);
            }
        });

        Button botaofornecedores = findViewById(R.id.botao_fornecedores);
        botaofornecedores.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, TelaFornecedores.class);
                startActivity(intent);
            }
        });

        Button botaoquemsomos = findViewById(R.id.botao_quemsomos);
        botaoquemsomos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, TelaQuemSomos.class);
                startActivity(intent);
            }
        });

        Button botaolocalizacao = findViewById(R.id.botao_localizacao);
        botaolocalizacao.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, TelaLocalizacao.class);
                startActivity(intent);

            }
        });

    }

}
