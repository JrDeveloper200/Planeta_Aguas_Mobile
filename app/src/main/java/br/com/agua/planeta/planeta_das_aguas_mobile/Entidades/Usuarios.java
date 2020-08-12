package br.com.agua.planeta.planeta_das_aguas_mobile.Entidades;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.Exclude;

import java.util.HashMap;
import java.util.Map;

import br.com.agua.planeta.planeta_das_aguas_mobile.DAO.ConfiguracaoFirebase;

public class Usuarios {

    private String id;
    private String email;
    private String senha;
    private String nomecompleto;
    private String aniversario;
    private String sexo;
    private String cidade;
    private String cep;
    private String endereco;
    private String telefone;
    private String cpf;

    public Usuarios() {
    }

    public void salvar() {
        DatabaseReference referenciaFirebase = ConfiguracaoFirebase.getFirebase();
        //Aqui eu alterei Usuario para Cliente e o ValueOf para definir os dados pelo getCpf
        referenciaFirebase.child("cliente").child(String.valueOf(getCpf())).setValue(this);
    }

    @Exclude
    public Map<String, Object> toMap() {
        HashMap<String, Object> hashMapUsuario = new HashMap<>();
        hashMapUsuario.put("id", getId());
        hashMapUsuario.put("nome", getNomecompleto());
        hashMapUsuario.put("email", getEmail());
        hashMapUsuario.put("senha", getSenha());
        hashMapUsuario.put("aniversario", getAniversario());
        hashMapUsuario.put("sexo", getSexo());
        hashMapUsuario.put("cidade", getCidade());
        hashMapUsuario.put("cep", getCep());
        hashMapUsuario.put("endereco", getEndereco());
        hashMapUsuario.put("telefone", getTelefone());
        hashMapUsuario.put("cpf", getCpf());

        return hashMapUsuario;


    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public String getNomecompleto() {
        return nomecompleto;
    }

    public void setNomecompleto(String nomecompleto) {
        this.nomecompleto = nomecompleto;
    }

    public String getAniversario() {
        return aniversario;
    }

    public void setAniversario(String aniversario) {
        this.aniversario = aniversario;
    }

    public String getSexo() {
        return sexo;
    }

    public void setSexo(String sexo) {
        this.sexo = sexo;
    }

    public String getCidade() {
        return cidade;
    }

    public void setCidade(String cidade) {
        this.cidade = cidade;
    }

    public String getCep() {
        return cep;
    }

    public void setCep(String cep) {
        this.cep = cep;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }
}
