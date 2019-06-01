package br.senai.sp.estacionafacil;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import org.json.JSONException;
import org.json.JSONStringer;

import br.senai.sp.estacionafacil.model.Veiculo;

public class Saida extends AppCompatActivity {

    private TextView txtPlaca;
    private TextView txtModelo;
    private TextView txtEntrada;
    private TextView txtSaida;
    private TextView txtValor;
    Veiculo veiculo = new Veiculo();
    private Toolbar toolbar;
    private Button botaoConfirmarSaida;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_saida);

        txtPlaca = findViewById(R.id.txt_placa_saida);
        txtModelo = findViewById(R.id.txt_modelo_saida);
        txtEntrada = findViewById(R.id.horario_entrada_saida);
        txtSaida = findViewById(R.id.horario_saida_saida);
        txtValor = findViewById(R.id.txt_total);

        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        botaoConfirmarSaida = findViewById(R.id.btn_confirmar);
        botaoConfirmarSaida.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Saida.this, MainActivity.class);
                startActivity(intent);

            }
        });


        Intent intent = getIntent();
        if(intent.getSerializableExtra("veiculo") != null) {

            veiculo = (Veiculo) intent.getSerializableExtra("veiculo");
            txtPlaca.setText(veiculo.getPlaca());
            txtModelo.setText(veiculo.getModelo());
            txtEntrada.setText(veiculo.getHorarioEntrada());
            txtSaida.setText(veiculo.getHorarioSaida());
            txtValor.setText(String.valueOf(veiculo.getValorPago()));



        }

    }
}
