package br.com.agua.planeta.planeta_das_aguas_mobile.Helper;

import android.content.Context;
import android.content.SharedPreferences;

public class Preferencias {

    private final String CHAVE_IDENTIFICADOR = "identificarUsuarioLogado";
    private final String CHAVE_NOME = "nomeUsuarioLogado";
    private Context context;
    private SharedPreferences preferences;
    //Alterei o nome do arquivo pelo NOME DO PROJETO no firebase
    private String NOME_ARQUIVO = "planetaguas.preferencias";
    private int MODE = 0;
    private SharedPreferences.Editor editor;

    public Preferencias(Context context) {
        this.context = context;
        preferences = context.getSharedPreferences(NOME_ARQUIVO, MODE);

        editor = preferences.edit();
    }

    public void salvarUsuarioPreferencias(String idenficadorUsuario, String nomeUsuario) {
        editor.putString(CHAVE_IDENTIFICADOR, idenficadorUsuario);
        editor.putString(CHAVE_NOME, nomeUsuario);
        editor.commit();
    }

    public String getIdenficador() {

        return preferences.getString(CHAVE_IDENTIFICADOR, null);

    }

    public String getNome() {

        return preferences.getString(CHAVE_NOME, null);

    }

}
