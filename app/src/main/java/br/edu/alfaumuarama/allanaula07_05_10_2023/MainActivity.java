package br.edu.alfaumuarama.allanaula07_05_10_2023;
import br.edu.alfaumuarama.allanaula07_05_10_2023.models.*;
import br.edu.alfaumuarama.allanaula07_05_10_2023.datasource.*;
import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListAdapter;
import android.widget.SimpleAdapter;

import java.util.ArrayList;
import java.util.HashMap;

public class MainActivity extends ListActivity {

    Button btnAdd;

    ArrayList<Aluno> listaAlunos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnAdd = findViewById(R.id.btnAdd);
        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, CadastroActivity.class));

            }
        });
    }

    protected void onResume(){
        super.onResume();
        atualizarLista();
    }
    private void atualizarLista(){
        TbAluno tbAluno = new TbAluno(MainActivity.this);
        listaAlunos = tbAluno.buscarTodos();

        ListAdapter adapter = new SimpleAdapter(MainActivity.this, //Local onde está a ListView(Nesta classe)
        listaToMap(listaAlunos), //Lista com os dados em formato HashMap
        R.layout.listview_modelo, //layout com o modelo de cada item (celula)
        new String[] {"nome", "ra"}, //as colunas com os dados
        new int[] {R.id.txtNome, R.id.txtRA}//cada campo que receberão os dados
        );
    }

    private ArrayList<HashMap<String,String>>listaToMap(ArrayList<Aluno>lista){
        ArrayList<HashMap<String,String>> listaRetorno = new ArrayList<>();

        for (Aluno aluno: lista){
            listaRetorno.add(aluno.toMap());
        }
        return listaRetorno;
    }
}