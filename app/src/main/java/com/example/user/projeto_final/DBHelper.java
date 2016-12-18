package com.example.user.projeto_final;

//******************************************************
//Instituto Federal de São Paulo - Campus Sertãozinho
//Disciplina......: M4DADM
//Programação de Computadores e Dispositivos Móveis
//Aluno...........: Leonardo Fonseca Pontes
//******************************************************

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteStatement;

import java.util.ArrayList;
import java.util.List;

public class DBHelper {
    //Declaração das variáveis principais do banco, versão e tabelas.
    private static final String DATABASE_NAME = "bancodedados.db";
    private static final int DATABASE_VERSION = 1;
    private static final String TABLE_NAME = "pessoafisica";

    //Declaração das variáveis do SQLiteDatabase
    private Context context;
    private SQLiteDatabase db;

    //Criação da variável de statement do SQLite
    private SQLiteStatement insertStnt;
    //Variável da string de insert no banco.
    private static final String INSERT = "insert into " + TABLE_NAME + " (nome, email, cpf, idade, telefone) values (?,?,?,?,?)";

    //Construtor DBHelper
    public DBHelper(Context context){
        this.context = context;
        OpenHelper openHelper = new OpenHelper(this.context);
        this.db = openHelper.getWritableDatabase();
        this.insertStnt = this.db.compileStatement(INSERT);
    }

    //Método de inserção no banco de dados
    public long Insert (String nome, String email, String cpf, String idade, String telefone){
        this.insertStnt.bindString(1, nome);    //bind da variável nome
        this.insertStnt.bindString(2, email);   //bind da variável email
        this.insertStnt.bindString(3, cpf);     //bind da variável cpf
        this.insertStnt.bindString(4, idade);   //bind da variável idade
        this.insertStnt.bindString(5, telefone);//bind da variável telefone
        return this.insertStnt.executeInsert(); //executa o insert
    }

    //Método de retorno de informações do banco de dados.
    public List<PessoaFisica> queryGetAll(){
        //Cria uma variável chamada lista do tipo ArrayList de objetos da classe PessoaFisica
        List<PessoaFisica> lista = new ArrayList<PessoaFisica>();

        try{
            //cria um cursor com as informações das colunas da tabela
            Cursor cursor = this.db.query(TABLE_NAME, new String[] {"nome", "cpf", "idade", "telefone", "email"}, null, null, null, null, null, null);
            //cria um registro da quantidade de elementos do cursor
            int nregistro = cursor.getCount();
            //se houver elementos no cursor
            if(nregistro!=0){
                cursor.moveToFirst(); //vá para o início do cursor
                do{
                    //faça:
                    //cria uma uma variável do tipo pessoa física com as informações do cursor
                    PessoaFisica pf = new PessoaFisica(cursor.getString(0), cursor.getString(1), cursor.getString(2),
                                                       cursor.getString(3), cursor.getString(4));
                    //Adiciona na lista
                    lista.add(pf);
                } while (cursor.moveToNext()); //enquanto houver um próximo registro
                if(cursor!= null && !cursor.isClosed()){ //se o cursor for nulo ou não estiver fechado
                    cursor.close(); //fecha o cursor
                    return lista; //retorna a lista de objetos da classe PessoaFisica
                }
            }else return null;

        } catch (Exception err){
            return null;
        }
        return lista; //////////
    }
    //Classe de criação do banco de dados.
    private static class OpenHelper extends SQLiteOpenHelper {
        OpenHelper(Context context){
            super (context, DATABASE_NAME, null, DATABASE_VERSION);
        }
        //Método de criação do banco
        public void onCreate (SQLiteDatabase db){
            //String de criação do banco
            String sql = "CREATE TABLE IF NOT EXISTS " + TABLE_NAME + " (id INTEGER PRIMARY KEY AUTOINCREMENT, nome TEXT, cpf TEXT, " +
                                                                        "idade TEXT, telefone TEXT, email TEXT);";
            db.execSQL(sql);//executa a string
        }
        //Método de drop da tabela do banco
        public void onUpgrade (SQLiteDatabase db, int oldversion, int newversion){
            db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME + " ;"); //String de drop da tabela do banco
            onCreate(db);//executa a string
        }
    }
}
