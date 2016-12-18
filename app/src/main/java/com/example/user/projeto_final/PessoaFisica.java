package com.example.user.projeto_final;

//******************************************************
//Instituto Federal de São Paulo - Campus Sertãozinho
//Disciplina......: M4DADM
//Programação de Computadores e Dispositivos Móveis
//Aluno...........: Leonardo Fonseca Pontes
//******************************************************

public class PessoaFisica {

    //Declaração das variáveis da classe com.example.user.projeto_final.PessoaFisica.
    private String nome;
    private String email;
    private String cpf;
    private String idade;
    private String telefone;

    //Método construtor da classe com.example.user.projeto_final.PessoaFisica.
    PessoaFisica(String nome, String email, String cpf, String idade, String telefone){
        this.nome     = nome;
        this.email    = email;
        this.cpf      = cpf;
        this.idade    = idade;
        this.telefone = telefone;
    }

    //Método de Get e Set da variável nome
    public String getNome() {
        return nome;
    }
    public void setNome(String nome) {
        this.nome = nome;
    }
    //Método de Get e Set da variável email
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    //Método de Get e Set da variável cpf
    public String getCpf() {
        return cpf;
    }
    public void setCpf(String cpf) {
        this.cpf = cpf;
    }
    //Método de Get e Set da variável idade
    public String getIdade() {
        return idade;
    }
    public void setIdade(String idade) {
        this.idade = idade;
    }
    //Método de Get e Set da variável telefone
    public String getTelefone() {
        return telefone;
    }
    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }
}
