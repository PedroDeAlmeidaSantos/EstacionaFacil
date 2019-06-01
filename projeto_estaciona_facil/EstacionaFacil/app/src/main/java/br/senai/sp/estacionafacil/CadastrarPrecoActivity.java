package br.senai.sp.estacionafacil;

import android.content.Intent;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import br.senai.sp.estacionafacil.model.Preco;
import br.senai.sp.estacionafacil.task.GravarPreco;


public class CadastrarPrecoActivity extends AppCompatActivity {

    private ImageButton botaoCadastrarPreco;
    private Toolbar toolbar;
    Preco preco = new Preco();
    private EditText txtPrimeiraHora;
    private EditText txtDemaisHoras;
    private EditText txtTolerancia;
    private TextInputLayout layoutTxtPrimeira;
    private TextInputLayout layoutTxtDemais;
    private TextInputLayout layoutTxtTolerancia;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastrar_preco);

        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        botaoCadastrarPreco = findViewById(R.id.btn_cadastrar_preco);
        txtDemaisHoras = findViewById(R.id.txt_demais_horas);
        txtPrimeiraHora = findViewById(R.id.txt_primeira_hora);
        txtTolerancia = findViewById(R.id.txt_tolerancia);


        Intent intent = getIntent();
        if(intent.getSerializableExtra("preco") != null){
            preco = (Preco) intent.getSerializableExtra("preco");
            txtPrimeiraHora.setText(String.valueOf(preco.getValorPrimeiraHora()));
            txtDemaisHoras.setText(String.valueOf(preco.getValorDemaisHoras()));
            txtTolerancia.setText(String.valueOf(preco.getTolerancia()));

        }

        botaoCadastrarPreco.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                preco.setValorDemaisHoras(Double.valueOf(txtDemaisHoras.getText().toString()));
                preco.setValorPrimeiraHora(Double.valueOf(txtPrimeiraHora.getText().toString()));
                preco.setTolerancia(Integer.parseInt(txtTolerancia.getText().toString()));

                    GravarPreco gravarPreco = new GravarPreco(preco);
                    gravarPreco.execute();
                    finish();
                    Toast.makeText(CadastrarPrecoActivity.this, "Preço cadastrado com sucesso!", Toast.LENGTH_SHORT).show();

            }
        });

    }

}
