package com.example.user.projeto_final;

//******************************************************
//Instituto Federal de São Paulo - Campus Sertãozinho
//Disciplina......: M4DADM
//Programação de Computadores e Dispositivos Móveis
//Aluno...........: Leonardo Fonseca Pontes
//******************************************************

//Importação das bibliotecas
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

//Classe principal da Activity_Inicial
public class Activity_Inicial extends AppCompatActivity {

    //Declaração das variáveis referentes aos componentes da Activity_Inicial
    Button btnTelaCadastro;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity__inicial);

        //Associação das variáveis aos componentes da Activity_Inicial
        btnTelaCadastro = (Button) findViewById(R.id.btnTelaCadastro);

        //Método OnClick do botão de Incluir Pessoa Física
        btnTelaCadastro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Chamada do método para alternar entre activities
                chamaTelaCadastro();
            }
        });
    }

    //Método que alterna entre a Activity_Inicial e a activity Main_Cadastro
    private void chamaTelaCadastro(){
        Intent intent = new Intent(); //instancia objeto da classe Intent
        intent.setClass(Activity_Inicial.this, Main_Cadastro.class); //Aplica método de alternância de Activities
        startActivity(intent); //Inicia a nova activity
        finish(); //Finaliza método
    }
}
