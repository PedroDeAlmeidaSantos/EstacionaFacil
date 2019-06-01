package br.senai.sp.estacionafacil;

import android.content.Intent;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageButton;
import android.widget.EditText;
import android.widget.Toast;

import br.senai.sp.estacionafacil.model.Mensalista;
import br.senai.sp.estacionafacil.task.AtualizarMensalista;
import br.senai.sp.estacionafacil.task.GravarMensalista;


public class CadastrarMensalistaActivity extends AppCompatActivity {

    private Toolbar toolbar;
    private ImageButton btnSalvar;
    private EditText txtNome;
    private EditText txtCpf;
    private EditText txtCelular;
    private EditText txtQuantidade;
    private TextInputLayout layoutTxtNome;
    private TextInputLayout layoutTxtCpf;
    private TextInputLayout layoutTxtCelular;
    private TextInputLayout layoutTxtQuantidade;
    Mensalista mensalista = new Mensalista();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastrar_mensalista);

        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        btnSalvar = findViewById(R.id.btn_add_mensalista);
        txtNome = findViewById(R.id.txt_nome);
        txtCelular = findViewById(R.id.txt_celular);
        txtQuantidade = findViewById(R.id.txt_quantidade);
        txtCpf = findViewById(R.id.txt_cpf);
        layoutTxtNome = findViewById(R.id.layout_txt_nome);
        layoutTxtCpf = findViewById(R.id.layout_txt_cpf);
        layoutTxtCelular = findViewById(R.id.layout_txt_celular);
        layoutTxtQuantidade = findViewById(R.id.layout_txt_quantidade);


        Intent intent = getIntent();

        if(intent.getSerializableExtra("mensalista") != null){
            mensalista = (Mensalista) intent.getSerializableExtra("mensalista");
            txtNome.setText(mensalista.getNome());
            txtCelular.setText(mensalista.getCelular());
            txtCpf.setText(mensalista.getCpf());
            txtQuantidade.setText(String.valueOf(mensalista.getQuantidadeVagas()));

        }


        btnSalvar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                mensalista.setNome(txtNome.getText().toString());
                mensalista.setCelular(txtCelular.getText().toString());
                mensalista.setCpf(txtCpf.getText().toString());
                mensalista.setQuantidadeVagas(Integer.parseInt(txtQuantidade.getText().toString()));

                if(mensalista.getId() == 0){

                        GravarMensalista gravarMensalista = new GravarMensalista(mensalista);
                        gravarMensalista.execute();
                        Toast.makeText(CadastrarMensalistaActivity.this, "Mensalista cadastrado com sucesso!", Toast.LENGTH_SHORT).show();
                        finish();

                }else{

                    AtualizarMensalista atualizarMensalista = new AtualizarMensalista(mensalista);
                    atualizarMensalista.execute();
                    Toast.makeText(CadastrarMensalistaActivity.this, "Mensalista atualizado com sucesso!", Toast.LENGTH_SHORT).show();
                    finish();

                }

            }
        });



    }

    private boolean validar(){
        boolean validado = true;
        if(txtNome.getText().toString().isEmpty()){
            layoutTxtNome.setErrorEnabled(true);
            layoutTxtNome.setError("Por favor digite um nome");
            validado = false;
        }else{
            layoutTxtNome.setErrorEnabled(false);
        }
        if(txtCpf.getText().toString().isEmpty()){
            layoutTxtCpf.setErrorEnabled(true);
            layoutTxtCpf.setError("Por favor digite um cpf");
            validado = false;
        }else{
            layoutTxtCpf.setErrorEnabled(false);
        }
        if(txtCelular.getText().toString().isEmpty()){
            layoutTxtCelular.setErrorEnabled(true);
            layoutTxtCelular.setError("Por favor digite um número");
            validado = false;
        }else{
            layoutTxtCelular.setErrorEnabled(false);
        }
        return validado;
    }

}
