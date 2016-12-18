package com.example.user.projeto_final;

//******************************************************
//Instituto Federal de São Paulo - Campus Sertãozinho
//Disciplina......: M4DADM
//Programação de Computadores e Dispositivos Móveis
//Aluno...........: Leonardo Fonseca Pontes
//******************************************************

//Imports das bibliotecas
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.util.List;

//Classe Principal
public class Main_Cadastro extends AppCompatActivity {

    //Declaração das variáveis referentes aos componentes do layout
    EditText edtNome, edtCPF, edtIdade, edtTelefone, edtEmail;
    Button btnInserir, btnVoltar, btnListar;
    private DBHelper dh;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main__cadastro);

        //Associação das variáveis aos componentes de entrada de texto da activity Main_Cadastro
        edtNome     = (EditText) findViewById(R.id.edtNome);
        edtCPF      = (EditText) findViewById(R.id.edtCPF);
        edtIdade    = (EditText) findViewById(R.id.edtIdade);
        edtTelefone = (EditText) findViewById(R.id.edtTelefone);
        edtEmail    = (EditText) findViewById(R.id.edtEmail);
        //Associação das variáveis aos componentes de botão da activity Main_Cadastro
        btnInserir = (Button) findViewById(R.id.btnInserir);
        btnVoltar  = (Button) findViewById(R.id.btnVoltar);
        btnListar  = (Button) findViewById(R.id.btnListar);

        //Método OnClick do botão Incluir.
        btnInserir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (edtNome.getText().length() > 0 && edtCPF.getText().length() > 0 && edtIdade.getText().length() > 0 && edtTelefone.getText().length() > 0 && edtEmail.getText().length() > 0){
                    //Método de inserção da classe DBHelper
                    dh.Insert(edtNome.getText().toString(),
                              edtEmail.getText().toString(),
                              edtCPF.getText().toString(),
                              edtIdade.getText().toString(),
                              edtTelefone.getText().toString());
                    //Mensagem de alerta para o sucesso da inclusão no banco.
                    AlertDialog.Builder caixaAlerta = new AlertDialog.Builder(Main_Cadastro.this); //Cria a caixa de mensagens.
                    caixaAlerta.setTitle("Sucesso!"); //Titulo da caixa de alerta
                    caixaAlerta.setMessage("Cadastro Realizado!"); //Informa a mensagem a ser apresentada.
                    caixaAlerta.show(); //Mostra a mensagem.
                    //Limpar os campos preenchidos
                    edtNome.setText("");
                    edtCPF.setText("");
                    edtIdade.setText("");
                    edtTelefone.setText("");
                    edtEmail.setText("");
                }else {
                    AlertDialog.Builder caixaAlerta = new AlertDialog.Builder(Main_Cadastro.this); //Cria a caixa de mensagens.
                    caixaAlerta.setTitle("Atenção!");//Titulo da caixa de alerta
                    caixaAlerta.setMessage("Todos os campos devem ser preenchidos!"); //Informa a mensagem a ser apresentada.
                    caixaAlerta.show(); //Mostra a mensagem.
                }
            }
        });
        //Método OnClick do botão Voltar
        btnVoltar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Chamada de método que alterna entre as activities
                voltarInicio();
            }
        });
        //Método OnClick do botão Listar
        btnListar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                List<PessoaFisica> pf = dh.queryGetAll();
                if (pf == null){
                    AlertDialog.Builder caixaAlerta = new AlertDialog.Builder(Main_Cadastro.this); //Cria a caixa de mensagens.
                    caixaAlerta.setTitle("Atenção!");//Titulo da caixa de alerta
                    caixaAlerta.setMessage("Não há registros cadastrados!"); //Informa a mensagem a ser apresentada.
                    caixaAlerta.show(); //Mostra a mensagem.
                    return;
                }
                for (int i = 0; i < pf.size(); i++){
                    PessoaFisica pessoa = (PessoaFisica) pf.get(i);
                    AlertDialog.Builder caixaAlerta = new AlertDialog.Builder(Main_Cadastro.this); //Cria a caixa de mensagens.
                    caixaAlerta.setTitle("Registro" + i); //Titulo da mensagem.
                    caixaAlerta.setMessage("Nome: " + pessoa.getNome() + //Mostra o nome
                                           "\nCPF: " + pessoa.getCpf() + //Mostra o CPF
                                           "\nIdade: " + pessoa.getIdade() + //Mostra a Idade
                                           "\nTelefone: " + pessoa.getTelefone() + //Mostra o Telefone
                                           "\nEmail: " + pessoa.getEmail()); //Mostra o email
                    caixaAlerta.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.dismiss(); //Cofirmação do usuário para ir para o próximo registro.
                        }
                    }); //Mostra a mensagem.
                    caixaAlerta.show();
                }

            }
        });
    }
    //Método que alterna entre a activity Main_Cadastro e a Activity_Inicial
    private void voltarInicio(){
        Intent intent = new Intent();//instancia objeto da classe Intent
        intent.setClass(Main_Cadastro.this, Activity_Inicial.class);//Aplica método de alternância de Activities
        startActivity(intent);//Inicia a nova activity
        finish();//Finaliza método
    }
}
